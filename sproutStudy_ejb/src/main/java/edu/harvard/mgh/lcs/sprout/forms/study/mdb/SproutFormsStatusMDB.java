package edu.harvard.mgh.lcs.sprout.forms.study.mdb;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.FormInstanceTO;
import edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.IdentityTO;
import edu.harvard.mgh.lcs.sprout.forms.core.exceptions.InvalidPublicationKeyException;
import edu.harvard.mgh.lcs.sprout.forms.core.to.LockTO;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.SproutFormsService;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.StudyService;
import edu.harvard.mgh.lcs.sprout.forms.study.beanws.Result;
import edu.harvard.mgh.lcs.sprout.forms.study.to.CohortTO;
import edu.harvard.mgh.lcs.sprout.forms.study.util.DateUtils;
import edu.harvard.mgh.lcs.sprout.forms.utils.StringUtils;
import edu.harvard.mgh.lcs.sprout.study.model.study.FormAttrEntity;
import edu.harvard.mgh.lcs.sprout.study.websocketsinterface.SproutStudyFormStateInterface;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
                @ActivationConfigProperty(propertyName="destination", propertyValue="topic/sproutFormStatusTopic"),
                @ActivationConfigProperty(propertyName="clientID", propertyValue = "SproutStudyFormsStatusMDB")
        })
public class SproutFormsStatusMDB implements MessageListener {

    @SuppressWarnings("EjbEnvironmentInspection")
    @EJB
    private SproutStudyFormStateInterface sproutStudyFormState;

    @EJB
    private SproutFormsService sproutFormsService;

    @EJB
    private StudyService studyService;

    @Override
    public void onMessage(Message message) {

        System.out.println("SproutFormsStatusMDB.onMessage");

        TextMessage textMessage = (TextMessage) message;
        try {
            String content = textMessage.getText();
            System.out.println("content = " + content);

            ObjectMapper mapper = new ObjectMapper();
            FormInstanceTO formInstanceTO = mapper.readValue(content, FormInstanceTO.class);

            System.out.println("formInstanceTO.getInstanceId() = " + formInstanceTO.getInstanceId());

            formInstanceTO = sproutFormsService.getFormInstance(formInstanceTO.getInstanceId());

            String publicationKey = formInstanceTO.getPublicationKey();

            if (StringUtils.isFull(publicationKey)) {
                Set<CohortTO> cohorts = studyService.getCohortsFromPublicationKey(publicationKey);

                String identitySchema = null;

                if (cohorts != null) {
                    for (CohortTO cohortTO : cohorts) {
                        String destination = null;
                        if (edu.harvard.mgh.lcs.sprout.forms.utils.StringUtils.isFull(publicationKey)) {
                            Map<String, String> formDestinationMap = new HashMap<String, String>();

                            if (formDestinationMap.containsKey(publicationKey)) {
                                destination = formDestinationMap.get(publicationKey);
                                formInstanceTO.setDestination(destination);
                            } else {
                                Set<FormAttrEntity> formAttrEntities = studyService.getFormAttributesFromPublicationKey(publicationKey);
                                if (formAttrEntities != null && formAttrEntities.size() > 0) {
                                    for (FormAttrEntity formAttrEntity : formAttrEntities) {
                                        if (formAttrEntity.getFormAttr().getCode().equalsIgnoreCase("DESTINATION")) {
                                            destination = formAttrEntity.getValue();
                                            formDestinationMap.put(publicationKey, destination);
                                            formInstanceTO.setDestination(destination);
                                            break;
                                        }
                                    }
                                }
                            }
                        }

                        if (formInstanceTO.getIdentities() != null && formInstanceTO.getIdentities().size() > 0) {
                            StringBuilder subjectIds = new StringBuilder();

                            for (IdentityTO identityTO : formInstanceTO.getIdentities()) {
                                if (identityTO.getScheme() != null && identityTO.getScheme().equalsIgnoreCase(cohortTO.getCohortSubjectSchema())) {
                                    if (subjectIds.length() > 0) subjectIds.append("|");
                                    subjectIds.append(identityTO.getId());
                                    break;
                                }
                            }
                            if (subjectIds.length() > 0) {
                                List<Result> results = studyService.getRemoteCohortSubjectsByList(cohortTO, subjectIds.toString());
                                if (results != null && results.size() > 0) {
                                    for (Result result : results) {
                                        if (result != null && !edu.harvard.mgh.lcs.sprout.forms.study.util.StringUtils.isEmpty(result.getId())) {
                                            formInstanceTO.setIdentityFirstName(result.getFirstName());
                                            formInstanceTO.setIdentityLastName(result.getLastName());
                                            formInstanceTO.setIdentityFullName(result.getFullName());
                                            formInstanceTO.setIdentityGender(result.getGender());
                                            formInstanceTO.setIdentityDob(DateUtils.getXMLGregorianCalendarFromDate(result.getBirthDate()));
                                            formInstanceTO.setIdentityPrimarySchema(cohortTO.getCohortSubjectSchema());
                                            formInstanceTO.setIdentityPrimaryId(result.getId());
                                            break;
                                        }

                                    }
                                } else {
                                    formInstanceTO.setIdentityFirstName("Unknown");
                                    formInstanceTO.setIdentityLastName("Unknown");
                                    formInstanceTO.setIdentityFullName("Unknown");
                                    formInstanceTO.setIdentityPrimarySchema(cohortTO.getCohortSubjectSchema());
                                    formInstanceTO.setIdentityPrimaryId(subjectIds.toString());
                                }
                            }
                        }
                    }
                    sproutStudyFormState.broadcast(cohorts, formInstanceTO);
                }


            } else {
                throw new InvalidPublicationKeyException(null);
            }

//            SproutStudyFormState.broadcaster.broadcast(lockTO);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
