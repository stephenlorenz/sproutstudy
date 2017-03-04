package edu.harvard.mgh.lcs.sprout.forms.study.mdb;

import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.AuditService;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.SproutFormsService;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.SproutStudyConstantService;
import edu.harvard.mgh.lcs.sprout.forms.study.to.StudyInboxTO;
import edu.harvard.mgh.lcs.sprout.forms.study.util.StringUtils;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@MessageDriven(
        activationConfig = { @ActivationConfigProperty(
                propertyName = "destinationType", propertyValue = "javax.jms.Queue"
        ), @ActivationConfigProperty(propertyName="destination", propertyValue="queue/sproutStudyEmailQueue") })
public class StudyEmailMDB implements MessageListener {

    private Configuration configuration = null;
    private Template emailFormBodyTemplate = null;

    @EJB
    private AuditService auditService;

    @EJB
    private SproutFormsService sproutFormsService;


    @Override
    public void onMessage(Message message) {

        ObjectMessage objectMessage = (ObjectMessage) message;
        try {
            StudyInboxTO studyInboxTO = (StudyInboxTO) objectMessage.getObject();
            if (studyInboxTO != null) {
                int auditId = auditService.log(studyInboxTO.getSender().getUsername(), SproutStudyConstantService.AuditType.PUBLISH_FORM_EMAIL_QUEUE, SproutStudyConstantService.AuditVerbosity.INFO, "Sending Form Email", studyInboxTO.getCohortTO(), studyInboxTO.getCohortTO().getCohortSubjectSchema(), studyInboxTO.getSubjectId(), String.format("Sending Form Email for instance, %s, from %s (%s) to %s (%s).", studyInboxTO.getInstanceId(), studyInboxTO.getSender().getFullName(), studyInboxTO.getSender().getUsername(), studyInboxTO.getRecipient().getFullName(), studyInboxTO.getRecipient().getUsername()));

                Properties props = new Properties();
                props.put("mail.smtp.host", System.getProperty("mail.smtp.host.partners", "phsmgout.partners.org"));

                Session session = Session.getDefaultInstance(props, null);

                MimeMessage mimeMessage = new MimeMessage(session);
                mimeMessage.setFrom(new InternetAddress(studyInboxTO.getSender().getEmail()));
                mimeMessage.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(studyInboxTO.getRecipient().getEmail()));
                mimeMessage.setSubject(studyInboxTO.getSubject());
                mimeMessage.setContent(constructMessageBody(studyInboxTO), "text/html; charset=utf-8");

                Transport.send(mimeMessage);

                auditService.debug(auditId, "Email sent successfully.");
            } else {
                auditService.error("Sending Form Email failed; StudyInboxTO object is null.");
                throw new JMSException("The StudyInboxTO object IS NULL.");
            }
        } catch (MessagingException e) {
            auditService.error("Sending Form Email failed.", e);
//            throw new RuntimeException(e);
        } catch (JMSException e) {
            auditService.error("Sending Form Email failed.", e);
        }

    }

    private String constructMessageBody(StudyInboxTO studyInboxTO) {
        if (studyInboxTO != null && studyInboxTO.getRecipient() != null && StringUtils.isFull(studyInboxTO.getRecipient().getUsername(), studyInboxTO.getInstanceId())) {
            if (emailFormBodyTemplate == null) init();
            if (emailFormBodyTemplate != null) {
                String nonce = sproutFormsService.applyForNonce(studyInboxTO.getRecipient().getUsername(), studyInboxTO.getInstanceId(), studyInboxTO.getSubjectName(), studyInboxTO.getSubjectId(), null, null, null);
                if (nonce != null) {
                    Map<String, Object> emailFormBodyModel = new HashMap<String, Object>();

                    try {
                        emailFormBodyModel.put("recipientFullName", studyInboxTO.getRecipient().getFullName());
                        emailFormBodyModel.put("recipientFirstName", studyInboxTO.getRecipient().getFirstName());
                        emailFormBodyModel.put("senderFullName", studyInboxTO.getSender().getFullName());
                        emailFormBodyModel.put("messageText", studyInboxTO.getBody().replaceAll("\\\n", "<br/>"));
                        emailFormBodyModel.put("pickupUrl", constructPickupUrl(studyInboxTO.getInstanceId(), nonce));
                        emailFormBodyModel.put("sproutStudyUrl", constructSproutStudyUrl());

                        Writer pageWriter = new StringWriter();
                        emailFormBodyTemplate.process(emailFormBodyModel, pageWriter);
                        pageWriter.flush();
                        return pageWriter.toString();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        return null;
    }

    private String constructPickupUrl(String instanceId, String nonce) {
        if (StringUtils.isFull(instanceId, nonce)) {
            return String.format("%s/prompt/?instanceId=%s&nonce=%s&debug=true&showThanks=false&trigger=study", System.getProperty("server.root.url", "https://scl30.partners.org:8443"), instanceId, nonce);
        }
        return null;
    }

    private String constructSproutStudyUrl() {
        return String.format("%s/sproutstudy", System.getProperty("server.root.url", "https://scl30.partners.org:8443"));
    }

    private void init() {
        configuration = new Configuration();
        configuration.setNumberFormat("0.######");
        configuration.setClassForTemplateLoading(this.getClass(), "/edu/harvard/mgh/lcs/sprout/forms/study/templates");
        configuration.setObjectWrapper(new DefaultObjectWrapper());
        try {
            emailFormBodyTemplate = configuration.getTemplate("emailFormBody.ftl");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
