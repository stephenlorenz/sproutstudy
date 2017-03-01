package edu.harvard.mgh.lcs.sprout.forms.study.bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.harvard.mgh.lcs.sprout.forms.core.ejb.bean.FormsWebServiceImplService;
import edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.*;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.AuditService;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.SproutFormsService;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.SproutStudyConstantService;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.StudyService;
import edu.harvard.mgh.lcs.sprout.forms.study.beanws.Result;
import edu.harvard.mgh.lcs.sprout.forms.study.to.BooleanTO;
import edu.harvard.mgh.lcs.sprout.forms.study.to.CohortTO;
import edu.harvard.mgh.lcs.sprout.forms.study.to.SproutStudyPayloadTO;
import edu.harvard.mgh.lcs.sprout.forms.study.util.DateUtils;
import edu.harvard.mgh.lcs.sprout.forms.study.util.StringUtils;
import edu.harvard.mgh.lcs.sprout.study.model.formSubmission.AssertionEntity;
import edu.harvard.mgh.lcs.sprout.study.model.formSubmission.SubmissionEntity;
import edu.harvard.mgh.lcs.sprout.study.model.study.CohortAttrEntity;
import edu.harvard.mgh.lcs.sprout.study.model.study.CohortEntity;
import edu.harvard.mgh.lcs.sprout.study.model.study.FormAttrEntity;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

@Stateless
@Remote(SproutFormsService.class)
@TransactionManagement
public class SproutFormsServiceImpl implements SproutFormsService, SproutStudyConstantService {

    @PersistenceContext(unitName = "sproutStudy_PU")
    private EntityManager entityManager;

    @EJB
    private StudyService studyService;

    @EJB
    private AuditService auditService;

    private FormsWebService formsWebService = null;

    @PostConstruct
    private void init() {
        FormsWebServiceImplService formsWebServiceImplService = null;

        if (StringUtils.isFull(System.getProperty("edu.harvard.mgh.lcs.sprout.forms.service.url"))) {
            try {
                URL url = new URL(System.getProperty("edu.harvard.mgh.lcs.sprout.forms.service.url"));
                formsWebServiceImplService = new FormsWebServiceImplService(url);
            } catch (MalformedURLException e) {}
        }

        if (formsWebServiceImplService == null) formsWebServiceImplService = new FormsWebServiceImplService();

        formsWebService = formsWebServiceImplService.getFormsAPIWSPort();
    }

    @Override
    public String getPublicationKeyFromInstanceId(String instanceId) {
        if (formsWebService == null) init();

        if (StringUtils.isFull(instanceId) && formsWebService != null) {
            String orgAuthKey = System.getProperty("edu.harvard.mgh.lcs.ihealthspace.module.forms.sprout.authToken");

            if (!StringUtils.isEmpty(orgAuthKey)) {
                PublicationInfoTO publicationInfoTO = null;
                try {
                    publicationInfoTO = formsWebService.getPublicationKeyByInstanceId(orgAuthKey, instanceId);
                    if (publicationInfoTO != null) return publicationInfoTO.getPublicationKey();
                } catch (AuthorizationException_Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public List<FormInstanceTO> getDeliveredForms(String identitySchema, String identityId, String deliveryKey) {
        if (formsWebService == null) init();

        if (formsWebService != null) {
            String orgAuthKey = System.getProperty("edu.harvard.mgh.lcs.ihealthspace.module.forms.sprout.authToken");

            if (!StringUtils.isEmpty(orgAuthKey)) {

                List<IdentityTO> identities = new ArrayList<IdentityTO>();
                IdentityTO identityMrn = new IdentityTO();
                identityMrn.setScheme(identitySchema);
                identityMrn.setId(identityId);
                identities.add(identityMrn);

                FormDeliveryTO formDeliveryTO = formsWebService.getFormsByDeliveryKey(orgAuthKey, identities, deliveryKey);
                if (formDeliveryTO != null) return formDeliveryTO.getFormInstances();
            }
        }
        return null;
    }

    @Override
    public FormDeliveryStatus deliverToInbox(String schema, String id, String publicationKey, String provider, String expirationDateString) {
        try {
            if (formsWebService == null) init();

            if (formsWebService != null) {
                String orgAuthKey = System.getProperty("edu.harvard.mgh.lcs.ihealthspace.module.forms.sprout.authToken");

                if (!StringUtils.isEmpty(orgAuthKey)) {
                    List<IdentityTO> identityTOList = new ArrayList<IdentityTO>();
                    IdentityTO identityTO = new IdentityTO();
                    identityTO.setScheme(schema);
                    identityTO.setId(id);
                    identityTOList.add(identityTO);

                    String deliveryKey = String.format("%s@appid.sproutstudy.partners.org", StringUtils.getGuidClean());

                    ParametersTO parametersTO = new ParametersTO();
                    NameValue nameValue = new NameValue();
                    nameValue.setName("pun");
                    nameValue.setValue(provider);
                    parametersTO.getParameter().add(nameValue);

                    Long expirationDateLong = null;

                    if (!StringUtils.isEmpty(expirationDateString)) {
                        Date expirationDate = StringUtils.parseDate(expirationDateString, true);
                        if (expirationDate != null) expirationDateLong = expirationDate.getTime();
                    }

                    return formsWebService.getFormURLLite(orgAuthKey, identityTOList, parametersTO, "ihealthspace", "en", expirationDateLong, publicationKey, deliveryKey);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PublicationTO getPublicationDetails(String key) {
        if (!StringUtils.isEmpty(key)) {
            try {
                if (formsWebService == null) init();

                if (formsWebService != null) {
                    String orgAuthKey = System.getProperty("edu.harvard.mgh.lcs.ihealthspace.module.forms.sprout.authToken");

                    if (!StringUtils.isEmpty(orgAuthKey)) {
                        return formsWebService.getPublicationDetails(orgAuthKey, key);
                    }
                }
            } catch (AuthorizationException_Exception e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public String getForm(String identitySchema, String identityId, String instanceId) {
        if (formsWebService == null) init();

        if (formsWebService != null) {
            String orgAuthKey = System.getProperty("edu.harvard.mgh.lcs.ihealthspace.module.forms.sprout.authToken");

            if (!StringUtils.isEmpty(orgAuthKey)) {

                List<IdentityTO> identities = new ArrayList<IdentityTO>();
                IdentityTO identityMrn = new IdentityTO();
                identityMrn.setScheme(identitySchema);
                identityMrn.setId(identityId);
                identities.add(identityMrn);

                try {
                    return formsWebService.getForm(orgAuthKey, identities, instanceId);
                } catch (InvalidNameException_Exception e) {
                    e.printStackTrace();
                    return String.format("Exception: %s", e.getMessage());
                } catch (AuthorizationException_Exception e) {
                    e.printStackTrace();
                    return String.format("Exception: %s", e.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                    return String.format("Exception: %s", e.getMessage());
                }
            }
        }
        return "Exception: Authorization Exception.";
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<FormInstanceTO> getSproutInbox(String username, CohortTO cohortTO, String[] identityArray, Set<String> publicationKeys) {

        if (publicationKeys != null && publicationKeys.size() > 0) {

            List<String> publicationKeysList = new ArrayList<String>();
            for (String publicationKey : publicationKeys) publicationKeysList.add(publicationKey);

            if (formsWebService == null) init();

            if (formsWebService != null) {

                String orgAuthKey = System.getProperty("edu.harvard.mgh.lcs.ihealthspace.module.forms.sprout.authToken");

                if (!StringUtils.isEmpty(orgAuthKey)) {

                    List<IdentityTO> identities = parseIdentityArray(identityArray);

                    String cohortPrimaryIdentitySchema = getCohortPrimaryIdentitySchema(cohortTO);
                    String cohortPrimaryIdentityId = null;

                    if (cohortPrimaryIdentitySchema != null) {
                        for (IdentityTO identity : identities) {
                            if (identity.getScheme().equalsIgnoreCase(cohortPrimaryIdentitySchema)) {
                                cohortPrimaryIdentityId = identity.getId();
                            }
                        }
                    }

                    auditService.log(username, AuditType.GET_INBOX, SproutStudyConstantService.AuditVerbosity.INFO, "Retrieving subject inbox", cohortTO, cohortPrimaryIdentitySchema, cohortPrimaryIdentityId, String.format("Retrieving subject, %s, inbox.", getIdentityArrayAsString(identityArray)));

                    if (identities != null && identities.size() > 0) {
                        FormDeliveryTO formDeliveryTO = formsWebService.getFormsByIdentityAndPublications(orgAuthKey, identities, publicationKeysList, true);

                        if (formDeliveryTO != null) {
                            List<FormInstanceTO> forms = new ArrayList<FormInstanceTO>();

                            List<FormInstanceTO> formInstanceTOList = formDeliveryTO.getFormInstances();
                            if (formInstanceTOList != null && formInstanceTOList.size() > 0) {

                                Map<String, String> formDestinationMap = new HashMap<String, String>();

                                for (FormInstanceTO formInstanceTO : formInstanceTOList) {
                                    String publicationKey = formInstanceTO.getPublicationKey();

                                    String destination = null;
                                    if (edu.harvard.mgh.lcs.sprout.forms.utils.StringUtils.isFull(publicationKey)) {
                                        if (formDestinationMap.containsKey(publicationKey)) {
                                            destination = formDestinationMap.get(publicationKey);
                                            formInstanceTO.setDestination(destination);
                                        } else {
                                            List<FormAttributeTO> formAttributeTOList = new ArrayList<FormAttributeTO>();
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

                                    formInstanceTO.setDestination(destination);

//                                    if (!StringUtils.isEmpty(formInstanceTO.getDeliveryKey())) {
//                                        SproutStudyConstantService.SubmissionStatus submissionStatus = formSubmissionService.getSubmissionStatus(formInstanceTO.getPublicationKey(), formInstanceTO.getInstanceId(), formInstanceTO.getDeliveryKey());
//                                        if (submissionStatus != null) {
//                                            formInstanceTO.setAdminStatus(submissionStatus.toString());
//                                        }
//                                    }
                                    if (!formInstanceTO.getInboxStatus().value().equalsIgnoreCase("REVOKED")) forms.add(formInstanceTO);
                                }
                            }
                            return forms;
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
    public BooleanTO deleteForm(String instanceId) {

        auditService.log(AuditType.REVOKE_FORM, AuditVerbosity.INFO, "Revoking sprout form instance", String.format("Revoking form instance, %s, from sprout inbox.", instanceId));

        if (formsWebService == null) init();

        if (formsWebService != null) {

            String orgAuthKey = System.getProperty("edu.harvard.mgh.lcs.ihealthspace.module.forms.sprout.authToken");

            if (!StringUtils.isEmpty(orgAuthKey)) {
                FormDeliveryStatus formDeliveryStatus = formsWebService.revokeFormDeliveryLite(orgAuthKey, instanceId);
                if (formDeliveryStatus != null && formDeliveryStatus.getStatus().value().equalsIgnoreCase("REVOKED")) {
                    return new BooleanTO(true);
                }
            }
        }
        return new BooleanTO(false);
    }

    @Override
    public FormInstanceTO getFormInstance(String instanceId) {

        if (formsWebService == null) init();

        if (formsWebService != null) {

            String orgAuthKey = System.getProperty("edu.harvard.mgh.lcs.ihealthspace.module.forms.sprout.authToken");

            if (!StringUtils.isEmpty(orgAuthKey)) {
                return formsWebService.getFormInstance(orgAuthKey, instanceId);
            }
        }
        return null;
    }

    @Override
    public BooleanTO unlock(String instanceId) {

        if (formsWebService == null) init();

        if (formsWebService != null) {

            String orgAuthKey = System.getProperty("edu.harvard.mgh.lcs.ihealthspace.module.forms.sprout.authToken");

            if (!StringUtils.isEmpty(orgAuthKey)) {
                return new BooleanTO(formsWebService.unlock(orgAuthKey, instanceId).getValue().equalsIgnoreCase("true"));
            }
        }
        return null;
    }

    @Override
    public List<FormInstanceTO> getMutableForms(String username, CohortTO cohortTO, Set<String> publicationKeys) {

        if (publicationKeys != null && publicationKeys.size() > 0 && cohortTO != null) {

            List<String> publicationKeysList = new ArrayList<String>();
            for (String publicationKey : publicationKeys) publicationKeysList.add(publicationKey);

            if (formsWebService == null) init();

            if (formsWebService != null) {

                String orgAuthKey = System.getProperty("edu.harvard.mgh.lcs.ihealthspace.module.forms.sprout.authToken");

                if (!StringUtils.isEmpty(orgAuthKey)) {

//                    auditService.log(username, AuditType.GET_INBOX, SproutStudyConstantService.AuditVerbosity.INFO, "Retrieving subject inbox", cohortTO, cohortPrimaryIdentitySchema, cohortPrimaryIdentityId, String.format("Retrieving subject, %s, inbox.", getIdentityArrayAsString(identityArray)));

                    FormDeliveryTO formDeliveryTO = formsWebService.getMutableFormsByPublications(orgAuthKey, publicationKeysList);

                    if (formDeliveryTO != null) {
                        List<FormInstanceTO> forms = new ArrayList<FormInstanceTO>();

                        List<FormInstanceTO> formInstanceTOList = formDeliveryTO.getFormInstances();
                        if (formInstanceTOList != null && formInstanceTOList.size() > 0) {
                            Map<String, String> formDestinationMap = new HashMap<String, String>();
                            for (FormInstanceTO formInstanceTO : formInstanceTOList) {
//                                if (!StringUtils.isEmpty(formInstanceTO.getDeliveryKey())) {
//                                    SproutStudyConstantService.SubmissionStatus submissionStatus = formSubmissionService.getSubmissionStatus(formInstanceTO.getPublicationKey(), formInstanceTO.getInstanceId(), formInstanceTO.getDeliveryKey());
//                                    if (submissionStatus != null) {
//                                        formInstanceTO.setAdminStatus(submissionStatus.toString());
//                                    }
//                                }
                                String publicationKey = formInstanceTO.getPublicationKey();

                                String destination = null;
                                Set<FormAttrEntity> formAttrEntities = studyService.getFormAttributesFromPublicationKey(publicationKey);

                                if (edu.harvard.mgh.lcs.sprout.forms.utils.StringUtils.isFull(publicationKey)) {
                                    if (formDestinationMap.containsKey(publicationKey)) {
                                        destination = formDestinationMap.get(publicationKey);
                                        formInstanceTO.setDestination(destination);
                                    } else {
                                        if (formAttrEntities != null && formAttrEntities.size() > 0) {
                                            for (FormAttrEntity formAttrEntity : formAttrEntities) {
                                                if (formAttrEntity.getFormAttr().getCode().equalsIgnoreCase("DESTINATION")) {
                                                    destination = formAttrEntity.getValue();
                                                    formDestinationMap.put(publicationKey, destination);
                                                    formInstanceTO.setDestination(destination);
                                                }
                                            }
                                        }
                                    }
                                }

                                if (formAttrEntities != null && formAttrEntities.size() > 0) {
                                    for (FormAttrEntity formAttrEntity : formAttrEntities) {
                                        if (formAttrEntity.getFormAttr().getCode().equalsIgnoreCase("UNEDITABLE")) {
                                            formInstanceTO.setUneditable(formAttrEntity.getValue() != null && formAttrEntity.getValue().equalsIgnoreCase("true"));
                                        }
                                    }
                                }


                                formInstanceTO.setDestination(destination);

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
                                            boolean unknown = false;
                                            for (Result result : results) {
                                                if (result != null && StringUtils.isFull(result.getId())) {
                                                    if (StringUtils.isFull(result.getFirstName())) {
                                                        if (result.getFirstName().equalsIgnoreCase("Unknown")) {
                                                            unknown = true;
                                                        }
                                                    } else {
                                                        unknown = true;
                                                    }


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
                                            if (!unknown) forms.add(formInstanceTO);
                                        }
                                    }
                                }
                            }
                        }
                        return forms;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public List<NameValue> getActiveSproutInboxStatuses() {
        return formsWebService.getActiveInboxStatues();
    }

    @Override
    public List<NameValue> getActiveSproutInboxLocations(List<String> publicationKeys) {
        return formsWebService.getActiveInboxLocations(publicationKeys);
    }

    @Override
    public String getMostRecentInstanceId(String schema, String id, String publicationKey) {
        try {
            if (formsWebService == null) init();

            if (formsWebService != null) {
                String orgAuthKey = System.getProperty("edu.harvard.mgh.lcs.ihealthspace.module.forms.sprout.authToken");

                if (!StringUtils.isEmpty(orgAuthKey)) {
                    List<IdentityTO> identityTOList = new ArrayList<IdentityTO>();
                    IdentityTO identityTO = new IdentityTO();
                    identityTO.setScheme(schema);
                    identityTO.setId(id);
                    identityTOList.add(identityTO);

                    FormDeliveryStatus formDeliveryStatus = formsWebService.getMostRecentInstanceId(orgAuthKey, identityTOList, publicationKey);
                    if (formDeliveryStatus != null && StringUtils.isFull(formDeliveryStatus.getInstanceId())) {
                        return formDeliveryStatus.getInstanceId();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getAllFormsPageCount(String username, CohortTO cohortTO, Set<String> publicationKeys, int rows, String status, String location, String targetDate, String assignment) {
        if (publicationKeys != null && publicationKeys.size() > 0 && cohortTO != null) {

            List<String> publicationKeysList = new ArrayList<String>();
            for (String publicationKey : publicationKeys) publicationKeysList.add(publicationKey);

            if (formsWebService == null) init();

            if (formsWebService != null) {

                String orgAuthKey = System.getProperty("edu.harvard.mgh.lcs.ihealthspace.module.forms.sprout.authToken");

                if (!StringUtils.isEmpty(orgAuthKey)) {

                    String schema = getCohortPrimaryIdentitySchema(cohortTO);

//                    auditService.log(username, AuditType.GET_INBOX, SproutStudyConstantService.AuditVerbosity.INFO, "Retrieving subject inbox", cohortTO, cohortPrimaryIdentitySchema, cohortPrimaryIdentityId, String.format("Retrieving subject, %s, inbox.", getIdentityArrayAsString(identityArray)));

                    List<IdentityTO> identities= null;

                    if (StringUtils.isFull(assignment) && !assignment.equalsIgnoreCase("null")) {
                        identities = new ArrayList<IdentityTO>();
                        IdentityTO identityMrn = new IdentityTO();
                        identityMrn.setScheme("partnerscn");
                        identityMrn.setId(assignment);
                        identities.add(identityMrn);
                    }

                    return formsWebService.getPageCountByPublications(orgAuthKey, publicationKeysList, rows, status, location, schema, targetDate, identities);
                }
            }
        }
        return 1;
    }

    @Override
    public FormListMetadataTO getAllFormsMetadata(String username, CohortTO cohortTO, Set<String> publicationKeys, int rows, String status, String location, String targetDate, String assignment) {
        if (publicationKeys != null && publicationKeys.size() > 0 && cohortTO != null) {

            List<String> publicationKeysList = new ArrayList<String>();
            for (String publicationKey : publicationKeys) publicationKeysList.add(publicationKey);

            if (formsWebService == null) init();

            if (formsWebService != null) {

                String orgAuthKey = System.getProperty("edu.harvard.mgh.lcs.ihealthspace.module.forms.sprout.authToken");

                if (!StringUtils.isEmpty(orgAuthKey)) {

                    String schema = getCohortPrimaryIdentitySchema(cohortTO);

//                    auditService.log(username, AuditType.GET_INBOX, SproutStudyConstantService.AuditVerbosity.INFO, "Retrieving subject inbox", cohortTO, cohortPrimaryIdentitySchema, cohortPrimaryIdentityId, String.format("Retrieving subject, %s, inbox.", getIdentityArrayAsString(identityArray)));

                    List<IdentityTO> identities= null;

                    if (StringUtils.isFull(assignment) && !assignment.equalsIgnoreCase("null")) {
                        identities = new ArrayList<IdentityTO>();
                        IdentityTO identityMrn = new IdentityTO();
                        identityMrn.setScheme("partnerscn");
                        identityMrn.setId(assignment);
                        identities.add(identityMrn);
                    }

                    return formsWebService.getFormMetadataByPublications(orgAuthKey, publicationKeysList, rows, status, location, schema, targetDate, identities);
                }
            }
        }
        return null;
    }

    @Override
    public List<NameValue> getAssignments(Set<String> publicationKeys, String status, String targetDate) {
        if (publicationKeys != null && publicationKeys.size() > 0) {

            List<String> publicationKeysList = new ArrayList<String>();
            for (String publicationKey : publicationKeys) publicationKeysList.add(publicationKey);

            if (formsWebService == null) init();

            if (formsWebService != null) {
                return formsWebService.getAssignments(publicationKeysList, status, targetDate);
            }
        }
        return null;
    }

    @Override
    public List<FormInstanceTO> getAllForms(String username, CohortTO cohortTO, Set<String> publicationKeys, int page, int rows, String orderBy, String orderDirection, String status, String location, String targetDate, String assignment) {

        if (publicationKeys != null && publicationKeys.size() > 0 && cohortTO != null) {

            List<String> publicationKeysList = new ArrayList<String>();
            for (String publicationKey : publicationKeys) publicationKeysList.add(publicationKey);

            if (formsWebService == null) init();

            if (formsWebService != null) {

                String orgAuthKey = System.getProperty("edu.harvard.mgh.lcs.ihealthspace.module.forms.sprout.authToken");

                if (!StringUtils.isEmpty(orgAuthKey)) {

//                    auditService.log(username, AuditType.GET_INBOX, SproutStudyConstantService.AuditVerbosity.INFO, "Retrieving subject inbox", cohortTO, cohortPrimaryIdentitySchema, cohortPrimaryIdentityId, String.format("Retrieving subject, %s, inbox.", getIdentityArrayAsString(identityArray)));

                    String schema = getCohortPrimaryIdentitySchema(cohortTO);

                    List<IdentityTO> identities= null;

                    if (StringUtils.isFull(assignment) && !assignment.equalsIgnoreCase("null")) {
                        identities = new ArrayList<IdentityTO>();
                        IdentityTO identityMrn = new IdentityTO();
                        identityMrn.setScheme("partnerscn");
                        identityMrn.setId(assignment);
                        identities.add(identityMrn);
                    }

                    FormDeliveryTO formDeliveryTO = formsWebService.getFormsByPublications(orgAuthKey, publicationKeysList, page, rows, orderBy, orderDirection, status, location, schema, targetDate, identities);

                    int hasSubjectIds = 0;

                    if (formDeliveryTO != null) {
                        List<FormInstanceTO> forms = new ArrayList<FormInstanceTO>();

                        List<FormInstanceTO> formInstanceTOList = formDeliveryTO.getFormInstances();
                        if (formInstanceTOList != null && formInstanceTOList.size() > 0) {
                            Map<String, String> formDestinationMap = new HashMap<String, String>();
                            Map<String, Result> formListIdentities = new HashMap<String, Result>();



                            StringBuilder subjectIds = new StringBuilder();

                            for (FormInstanceTO formInstanceTO : formInstanceTOList) {
                                for (IdentityTO identityTO : formInstanceTO.getIdentities()) {
                                    if (identityTO.getScheme() != null && identityTO.getScheme().equalsIgnoreCase(cohortTO.getCohortSubjectSchema())) {
                                        if (subjectIds.length() > 0) subjectIds.append("|");
                                        subjectIds.append(identityTO.getId());
                                        break;
                                    }
                                }
                            }

                            if (subjectIds.length() > 0) {
                                List<Result> results = studyService.getRemoteCohortSubjectsByList(cohortTO, subjectIds.toString());
                                if (results != null && results.size() > 0) {
                                    hasSubjectIds++;
                                    for (Result result : results) {
                                        if (result != null && !StringUtils.isEmpty(result.getId())) {
                                            formListIdentities.put(result.getId(), result);
//
//                                            formInstanceTO.setIdentityFirstName(result.getFirstName());
//                                            formInstanceTO.setIdentityLastName(result.getLastName());
//                                            formInstanceTO.setIdentityFullName(result.getFullName());
//                                            formInstanceTO.setIdentityGender(result.getGender());
//                                            formInstanceTO.setIdentityDob(DateUtils.getXMLGregorianCalendarFromDate(result.getBirthDate()));
//                                            formInstanceTO.setIdentityPrimarySchema(cohortTO.getCohortSubjectSchema());
//                                            formInstanceTO.setIdentityPrimaryId(result.getId());
                                        }

                                    }
                                }
                            }



                            for (FormInstanceTO formInstanceTO : formInstanceTOList) {
                                String publicationKey = formInstanceTO.getPublicationKey();

                                Set<FormAttrEntity> formAttrEntities = studyService.getFormAttributesFromPublicationKey(publicationKey);
                                String destination = null;
                                if (edu.harvard.mgh.lcs.sprout.forms.utils.StringUtils.isFull(publicationKey)) {
                                    if (formDestinationMap.containsKey(publicationKey)) {
                                        destination = formDestinationMap.get(publicationKey);
                                        formInstanceTO.setDestination(destination);
                                    } else {
                                        if (formAttrEntities != null && formAttrEntities.size() > 0) {
                                            for (FormAttrEntity formAttrEntity : formAttrEntities) {
                                                if (formAttrEntity.getFormAttr().getCode().equalsIgnoreCase("DESTINATION")) {
                                                    destination = formAttrEntity.getValue();
                                                    formDestinationMap.put(publicationKey, destination);
                                                    formInstanceTO.setDestination(destination);
                                                }
                                            }
                                        }
                                    }
                                }

                                if (formAttrEntities != null && formAttrEntities.size() > 0) {
                                    for (FormAttrEntity formAttrEntity : formAttrEntities) {
                                        if (formAttrEntity.getFormAttr().getCode().equalsIgnoreCase("UNEDITABLE")) {
                                            formInstanceTO.setUneditable(formAttrEntity.getValue() != null && formAttrEntity.getValue().equalsIgnoreCase("true"));
                                        }
                                    }
                                }


                                if (formInstanceTO.getIdentities() != null && formInstanceTO.getIdentities().size() > 0) {
//                                    StringBuilder subjectIds = new StringBuilder();

                                    boolean unknown = false;

                                    for (IdentityTO identityTO : formInstanceTO.getIdentities()) {
                                        if (identityTO.getScheme() != null && identityTO.getScheme().equalsIgnoreCase(cohortTO.getCohortSubjectSchema())) {
                                            Result result = formListIdentities.get((identityTO.getId()));
                                            if (result != null) {
                                                formInstanceTO.setIdentityFirstName(result.getFirstName());
                                                formInstanceTO.setIdentityLastName(result.getLastName());
                                                formInstanceTO.setIdentityFullName(result.getFullName());
                                                formInstanceTO.setIdentityGender(result.getGender());
                                                formInstanceTO.setIdentityDob(DateUtils.getXMLGregorianCalendarFromDate(result.getBirthDate()));
                                                formInstanceTO.setIdentityPrimarySchema(cohortTO.getCohortSubjectSchema());
                                                formInstanceTO.setIdentityPrimaryId(result.getId());
                                            } else {
                                                unknown = false;
                                                if (StringUtils.isEmpty(formInstanceTO.getIdentityFirstName())) formInstanceTO.setIdentityFirstName("Unknown");
                                                if (StringUtils.isEmpty(formInstanceTO.getIdentityLastName())) formInstanceTO.setIdentityLastName("Unknown");
                                                if (StringUtils.isEmpty(formInstanceTO.getIdentityFullName())) formInstanceTO.setIdentityFullName("Unknown");
                                                formInstanceTO.setIdentityPrimarySchema(cohortTO.getCohortSubjectSchema());
//                                                formInstanceTO.setIdentityPrimaryId(subjectIds.toString());
                                                if (StringUtils.isEmpty(formInstanceTO.getIdentityPrimaryId())) formInstanceTO.setIdentityPrimaryId("---");
                                            }
//                                            if (subjectIds.length() > 0) subjectIds.append("|");
//                                            subjectIds.append(identityTO.getId());
//                                            break;
                                        }
//                                    }
//                                    if (subjectIds.length() > 0) {
//                                        List<Result> results = studyService.getRemoteCohortSubjectsByList(cohortTO, subjectIds.toString());
//                                        if (results != null && results.size() > 0) {
//                                            hasSubjectIds++;
//                                            for (Result result : results) {
//                                                if (result != null && !StringUtils.isEmpty(result.getId())) {
//                                                    formInstanceTO.setIdentityFirstName(result.getFirstName());
//                                                    formInstanceTO.setIdentityLastName(result.getLastName());
//                                                    formInstanceTO.setIdentityFullName(result.getFullName());
//                                                    formInstanceTO.setIdentityGender(result.getGender());
//                                                    formInstanceTO.setIdentityDob(DateUtils.getXMLGregorianCalendarFromDate(result.getBirthDate()));
//                                                    formInstanceTO.setIdentityPrimarySchema(cohortTO.getCohortSubjectSchema());
//                                                    formInstanceTO.setIdentityPrimaryId(result.getId());
//                                                    break;
//                                                }
//
//                                            }
//                                        } else {
//                                            formInstanceTO.setIdentityFirstName("Unknown");
//                                            formInstanceTO.setIdentityLastName("Unknown");
//                                            formInstanceTO.setIdentityFullName("Unknown");
//                                            formInstanceTO.setIdentityPrimarySchema(cohortTO.getCohortSubjectSchema());
//                                            formInstanceTO.setIdentityPrimaryId(subjectIds.toString());
//                                        }
                                    }
                                    forms.add(formInstanceTO);
                                }
                            }
                        }
                        return forms;
                    }
                }
            }
        }
        return null;
    }

    private String getCohortPrimaryIdentitySchema(CohortTO cohortTO) {
        if (cohortTO != null) {
            try {
                CohortEntity cohortEntity = entityManager.find(CohortEntity.class, cohortTO.getId());
                if (cohortEntity != null) {
                    for (CohortAttrEntity cohortAttrEntity : cohortEntity.getCohortAttributes()) {
                        if (cohortAttrEntity.getCohortAttr().getCode().equalsIgnoreCase(IDENTITY_SCHEMA_PRIMARY)) {
                            return cohortAttrEntity.getValue();
                        }
                    }
                }
            } catch (NoResultException e) {
            }

        }
        return null;
    }

    private String getIdentityArrayAsString(String[] identityArray) {
        StringBuilder stringBuilder = new StringBuilder();
        if (identityArray != null && identityArray.length > 0) {
            List<IdentityTO> identities = parseIdentityArray(identityArray);
            for (IdentityTO identity : identities) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append(String.format("%s@%s", identity.getId(), identity.getScheme()));
            }
        }


        return null;
    }

    private List<IdentityTO> parseIdentityArray(String[] identityArray) {
        List<IdentityTO> identities = new ArrayList<IdentityTO>();

        if (identityArray != null && identityArray.length > 0) {
            for (String identity : identityArray) {
                if (identity.indexOf("@") > -1) {
                    String[] identityParts = identity.split("@");
                    if (identityParts.length == 2) {
                        IdentityTO identityTO = new IdentityTO();
                        identityTO.setId(identityParts[0]);
                        identityTO.setScheme(identityParts[1]);
                        identities.add(identityTO);
                    }
                }
            }
            return identities;
        }
        return null;
    }


    @Override
    public String applyForNonce(String user, String instanceId, String subjectName, String subjectId) {
        if (formsWebService == null) init();

        if (formsWebService != null) {
            if (!StringUtils.isEmpty(user) && !StringUtils.isEmpty(instanceId)) {
                String orgAuthKey = System.getProperty("edu.harvard.mgh.lcs.ihealthspace.module.forms.sprout.authToken");
                if (!StringUtils.isEmpty(orgAuthKey)) {
                    List<IdentityTO> identities = new ArrayList<IdentityTO>();
                    IdentityTO identityMrn = new IdentityTO();
                    identityMrn.setScheme("partnerscn");
                    identityMrn.setId(user);
                    identities.add(identityMrn);

                    SproutStudyPayloadTO sproutStudyPayloadTO = new SproutStudyPayloadTO(subjectId, subjectName);

                    ObjectMapper objectMapper = new ObjectMapper();
                    String sproutStudyPayloadTOJSON = null;
                    try {
                        sproutStudyPayloadTOJSON = objectMapper.writeValueAsString(sproutStudyPayloadTO);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }

                    return formsWebService.applyForNonce(orgAuthKey, identities, subjectName, subjectId, instanceId, null, sproutStudyPayloadTOJSON);
                }
            }
        }
        return null;
    }

    @Override
    public List<FormInstanceTO> getForms(String identitySchema, String identityId, String practiceId) {

        Set<String> ihsGroupCdes = new HashSet<String>();
        Set<String> practiceIds = new HashSet<String>();
        practiceIds.add(practiceId);

//        List<PracticeTO> practiceTOList = clockworkService.getPracticesByIdxIds(practiceIds);
//        if (practiceTOList != null) for (PracticeTO practiceTO : practiceTOList) ihsGroupCdes.add(practiceTO.getCode());

//        Set<String> publicationKeys = clockworkService.getPublicationKeysByGroupCodes(ihsGroupCdes);

//        Set<String> publicationKeys = clockworkService.getPublicationKeysByPracticeNames(getPracticeNamesByIdxIds(practiceIds));
//        if (publicationKeys != null && publicationKeys.size() > 0) {
//
//            List<String> publicationKeysList = new ArrayList<String>();
//            for (String publicationKey : publicationKeys) publicationKeysList.add(publicationKey);
//
//            if (formsWebService == null) init();
//
//            if (formsWebService != null) {
//
//                String orgAuthKey = System.getProperty("edu.harvard.mgh.lcs.ihealthspace.module.forms.sprout.authToken");
//
//                if (!StringUtils.isEmpty(orgAuthKey)) {
//
//                    List<IdentityTO> identities = new ArrayList<IdentityTO>();
//                    IdentityTO identityMrn = new IdentityTO();
//                    identityMrn.setScheme(identitySchema);
//                    identityMrn.setId(identityId);
//                    identities.add(identityMrn);
//
//                    FormDeliveryTO formDeliveryTO = formsWebService.getFormsByIdentityAndPublications(orgAuthKey, identities, publicationKeysList, false);
//
//                    if (formDeliveryTO != null) {
//                        List<FormInstanceTO> forms = new ArrayList<FormInstanceTO>();
//
//                        List<FormInstanceTO> formInstanceTOList = formDeliveryTO.getFormInstances();
//                        if (formInstanceTOList != null && formInstanceTOList.size() > 0) {
//                            for (FormInstanceTO formInstanceTO : formInstanceTOList) {
//                                if (!StringUtils.isEmpty(formInstanceTO.getDeliveryKey())) {
//                                    SubmissionStatus submissionStatus = formSubmissionService.getSubmissionStatus(formInstanceTO.getPublicationKey(), formInstanceTO.getInstanceId(), formInstanceTO.getDeliveryKey());
//                                    if (submissionStatus != null) {
//                                        formInstanceTO.setAdminStatus(submissionStatus.toString());
//                                    }
//                                }
//                                forms.add(formInstanceTO);
//                            }
//                        }
//                        return forms;
//                    }
//                }
//            }
//        }
        return null;
    }

    @Override
    public boolean syncFormSubmission(String instanceId) {

        if (!StringUtils.isEmpty(instanceId)) {
            if (formsWebService == null) init();

            if (formsWebService != null) {

                String orgAuthKey = System.getProperty("edu.harvard.mgh.lcs.ihealthspace.module.forms.sprout.authToken");

                if (!StringUtils.isEmpty(orgAuthKey)) {
//                    try {
////                        String formSubmissionModel = formsWebService.syncSproutIdentities(orgAuthKey, instanceId, identities, assertions);
//                    } catch (AuthorizationException_Exception e) {
//                        e.printStackTrace();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
                }
            }
        }
        return false;

    }

    @Override
    public boolean syncPatientIdentifiersAndAssertions(String instanceId, String[] verifiedIdentifiers, String[] matchedIdentifiers, String[] matchedAssertions) {

        System.out.println("8885: SproutFormsServiceImpl.syncPatientIdentifiersAndAssertions.instanceId: " + instanceId);


        if (!StringUtils.isEmpty(instanceId) && verifiedIdentifiers != null && verifiedIdentifiers.length > 0) {
            if (formsWebService == null) init();

            SubmissionEntity submissionEntity = findSubmissionByInstanceId(instanceId);

            if (formsWebService != null && submissionEntity != null) {

                String orgAuthKey = System.getProperty("edu.harvard.mgh.lcs.ihealthspace.module.forms.sprout.authToken");

                if (!StringUtils.isEmpty(orgAuthKey)) {

                    List<IdentityTO> identities = new ArrayList<IdentityTO>();

                    for (String verifiedIdentifier : verifiedIdentifiers) {
                        IdentityTO identityTO = parseIdentifier(verifiedIdentifier);
                        if (identityTO != null) identities.add(identityTO);
                    }
                    if (matchedIdentifiers != null && matchedIdentifiers.length > 0) {
                        for (String matchedIdentifier : matchedIdentifiers) {
                            IdentityTO identityTO = parseIdentifier(matchedIdentifier);
                            if (identityTO != null) identities.add(identityTO);
                        }
                    }

                    Set<AssertionEntity> assertionEntities = submissionEntity.getAssertions();
                    Map<String, AssertionEntity> assertionEntityMap = new HashMap<String, AssertionEntity>();
                    if (assertionEntities != null && assertionEntities.size() > 0) {
                        for (AssertionEntity assertionEntity : assertionEntities) {
                            assertionEntityMap.put(assertionEntity.getVariableName().toLowerCase(), assertionEntity);
                        }
                    }

                    List<AssertionTO> assertions = new ArrayList<AssertionTO>();
                    if (matchedAssertions != null && matchedAssertions.length > 0) {
                        for (String matchedAssertion : matchedAssertions) {
                            AssertionTO assertionTO = parseAssertion(matchedAssertion);
                            if (assertionTO != null) {
                                if (StringUtils.isEmpty(assertionTO.getValue())) {
                                    AssertionEntity assertionEntity = assertionEntityMap.get(assertionTO.getVariableName().toLowerCase());

                                    if (assertionEntity != null && assertionEntity.isIdentifier()) {
                                        for (String matchedIdentifier : matchedIdentifiers) {
                                            IdentityTO identityTO = parseIdentifier(matchedIdentifier);
                                            if (identityTO != null && identityTO.getScheme() != null && identityTO.getScheme().equalsIgnoreCase("mgh")) {
                                                assertionTO.setValue(identityTO.getId());
                                                break;
                                            }
                                        }
                                    }
                                }
                                assertions.add(assertionTO);
                            }
                        }
                    }

                    try {
                        if (formsWebService.syncSproutIdentities(orgAuthKey, instanceId, identities, assertions)) {
                            String model = formsWebService.getFormSubmissionModel(orgAuthKey, instanceId);
//                            System.out.println("model = " + model);
                            try {
                                if (submissionEntity != null) {
                                    submissionEntity.setSubmissionData(model);
                                    submissionEntity.setActivityDate(new Date());
                                    entityManager.merge(submissionEntity);
                                    return true;
                                }
                            } catch (NoResultException e) {
                            }
                        }
                    } catch (AuthorizationException_Exception e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;

    }

    private AssertionTO parseAssertion(String assertion) {
        if (assertion.indexOf("@") >= 0) {
            String assertionName = assertion.substring(assertion.lastIndexOf("@") + 1);
            String assertionValue = assertion.substring(0, assertion.lastIndexOf("@"));

            AssertionTO assertionTO = new AssertionTO();
            assertionTO.setFieldCode(convertVariableNameToFieldCode(assertionName));
            assertionTO.setVariableName(assertionName);
            assertionTO.setValue(assertionValue);
            return assertionTO;
        }
        return null;
    }

    private String convertVariableNameToFieldCode(String variableName) {
        if (!StringUtils.isEmpty(variableName)) {
            if (variableName.indexOf("_") > 0) {
                return variableName.substring(0, variableName.lastIndexOf("_"));
            }
        }
        return null;
    }


    private IdentityTO parseIdentifier(String identifier) {
        if (identifier.indexOf("@") > 1) {
            String identityId = identifier.substring(0, identifier.lastIndexOf("@"));
            String identitySchema = identifier.substring(identifier.lastIndexOf("@") + 1);

            IdentityTO identityTO = new IdentityTO();
            identityTO.setScheme(identitySchema);
            identityTO.setId(identityId);
            return identityTO;
        }
        return null;
    }

    private Set<String> getPracticeNamesByIhsGroupIds(Set<String> ihsGroupIds) {
        Set<String> idxPracticeIdSet = new HashSet<String>();
//        List<PracticeTO> practiceTOList = clockworkService.getPracticesByIhsGroupIds(ihsGroupIds);
//        if (practiceTOList != null && practiceTOList.size() > 0) {
//            for (PracticeTO practiceTO : practiceTOList) idxPracticeIdSet.add(practiceTO.getName());
//        }
        return idxPracticeIdSet;
    }

    private Set<String> getPracticeNamesByIdxIds(Set<String> idxPracticeIds) {
        Set<String> idxPracticeIdSet = new HashSet<String>();
//        List<PracticeTO> practiceTOList = clockworkService.getPracticesByIdxIds(idxPracticeIds);
//        if (practiceTOList != null && practiceTOList.size() > 0) {
//            for (PracticeTO practiceTO : practiceTOList) idxPracticeIdSet.add(practiceTO.getName());
//        }
        return idxPracticeIdSet;
    }

    public SubmissionEntity findSubmissionByInstanceId(String instanceId) {
        try {
            Query query = entityManager.createNamedQuery(SubmissionEntity.FIND_BY_INSTANCE_ID);
            query.setParameter("instanceId", instanceId);
            return (SubmissionEntity) query.getSingleResult();
        } catch (NoResultException e) {
        }
        return null;
    }

}
