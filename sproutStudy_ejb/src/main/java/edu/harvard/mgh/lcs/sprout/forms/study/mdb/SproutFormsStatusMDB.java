package edu.harvard.mgh.lcs.sprout.forms.study.mdb;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import edu.harvard.mgh.lcs.sprout.study.model.study.PollEventEntity;
import edu.harvard.mgh.lcs.sprout.study.websocketsinterface.SproutStudyFormStateInterface;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;
import java.util.logging.Logger;

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
                @ActivationConfigProperty(propertyName="destination", propertyValue="topic/sproutFormStatusTopic"),
                @ActivationConfigProperty(propertyName = "consumerWindowSize", propertyValue = "0"),
                @ActivationConfigProperty(propertyName="clientID", propertyValue = "SproutStudyFormsStatusMDB")
        })
public class SproutFormsStatusMDB implements MessageListener {

//    @SuppressWarnings("EjbEnvironmentInspection")
//    @EJB
//    private SproutStudyFormStateInterface sproutStudyFormState;

    @PersistenceContext(unitName = "sproutStudy_PU")
    private EntityManager entityManager;

    @EJB
    private SproutFormsService sproutFormsService;

    @EJB
    private StudyService studyService;

    private static final Logger LOGGER = Logger.getLogger(SproutFormsStatusMDB.class.getName());

    @Override
    public void onMessage(Message message) {

        LOGGER.fine("SproutFormsStatusMDB.onMessage");

        TextMessage textMessage = (TextMessage) message;
        try {
            String content = textMessage.getText();
            LOGGER.fine("content = " + content);

            ObjectMapper mapper = new ObjectMapper();
            FormInstanceTO formInstanceTO = mapper.readValue(content, FormInstanceTO.class);

            LOGGER.fine("formInstanceTO.getInstanceId() = " + formInstanceTO.getInstanceId());

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
                                        } else if (formAttrEntity.getFormAttr().getCode().equalsIgnoreCase("UNEDITABLE")) {
                                            formInstanceTO.setUneditable(formAttrEntity.getValue() != null && formAttrEntity.getValue().equalsIgnoreCase("true"));
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
                                            formInstanceTO.setIdentityLanguage(result.getLanguage());
                                            break;
                                        }

                                    }
                                } else {
                                    formInstanceTO.setIdentityFirstName("Unknown");
                                    formInstanceTO.setIdentityLastName("Unknown");
                                    formInstanceTO.setIdentityFullName("Unknown");
                                    formInstanceTO.setIdentityPrimarySchema(cohortTO.getCohortSubjectSchema());
                                    formInstanceTO.setIdentityPrimaryId(subjectIds.toString());
                                    formInstanceTO.setIdentityLanguage("English");
                                }
                            }
                        }
                    }


                    generatePollEvent(cohorts, formInstanceTO);

//                    sproutStudyFormState.broadcast(cohorts, formInstanceTO);
                }


            } else {
                throw new InvalidPublicationKeyException(null);
            }

//            SproutStudyFormState.broadcaster.broadcast(lockTO);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void generatePollEvent(Set<CohortTO> cohorts, FormInstanceTO formInstanceTO) {
        if (cohorts != null && cohorts.size() > 0 && formInstanceTO != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            String formInstanceJSON = null;
            try {
                formInstanceJSON = objectMapper.writeValueAsString(formInstanceTO);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            if (formInstanceJSON != null) {
                for (CohortTO cohort : cohorts) {
                    PollEventEntity pollEventEntity = new PollEventEntity();
                    pollEventEntity.setCohort(cohort.getCohortKey());
                    pollEventEntity.setData(formInstanceJSON);
                    pollEventEntity.setActivityDate(new Date());
                    entityManager.persist(pollEventEntity);
                }
            }
        }
//        deleteExpiredPollEvents();
    }

//    private void deleteExpiredPollEvents() {
//        Query query = entityManager.createNativeQuery("DELETE FROM dbo.poll_event WHERE activity_date < DATEADD(MINUTE,-5,getdate())");
//        query.executeUpdate();
//    }

    //    @Override
    public void onMessageWebSockets(Message message) {

        LOGGER.fine("SproutFormsStatusMDB.onMessage");

        TextMessage textMessage = (TextMessage) message;
        try {
            String content = textMessage.getText();
            LOGGER.fine("content = " + content);

            ObjectMapper mapper = new ObjectMapper();
            FormInstanceTO formInstanceTO = mapper.readValue(content, FormInstanceTO.class);

            LOGGER.fine("formInstanceTO.getInstanceId() = " + formInstanceTO.getInstanceId());

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
                                        } else if (formAttrEntity.getFormAttr().getCode().equalsIgnoreCase("UNEDITABLE")) {
                                            formInstanceTO.setUneditable(formAttrEntity.getValue() != null && formAttrEntity.getValue().equalsIgnoreCase("true"));
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
                                            formInstanceTO.setIdentityLanguage(result.getLanguage());
                                            break;
                                        }

                                    }
                                } else {
                                    formInstanceTO.setIdentityFirstName("Unknown");
                                    formInstanceTO.setIdentityLastName("Unknown");
                                    formInstanceTO.setIdentityFullName("Unknown");
                                    formInstanceTO.setIdentityPrimarySchema(cohortTO.getCohortSubjectSchema());
                                    formInstanceTO.setIdentityPrimaryId(subjectIds.toString());
                                    formInstanceTO.setIdentityLanguage("English");
                                }
                            }
                        }
                    }
//                    sproutStudyFormState.broadcast(cohorts, formInstanceTO);
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
