package edu.harvard.mgh.lcs.sprout.forms.study.bean;

import edu.harvard.mgh.lcs.sprout.forms.core.ejb.bean.FormsWebServiceImplService;
import edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.*;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.FormSubmissionService;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.SproutFormsService;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.SproutStudyConstantService;
import edu.harvard.mgh.lcs.sprout.forms.study.util.StringUtils;
import edu.harvard.mgh.lcs.sprout.study.model.formSubmission.AssertionEntity;
import edu.harvard.mgh.lcs.sprout.study.model.formSubmission.SubmissionEntity;
import edu.harvard.mgh.lcs.sprout.study.model.study.CohortEntity;
import edu.harvard.mgh.lcs.sprout.study.model.study.CohortFormEntity;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

@Stateless
@Remote(SproutFormsService.class)
@TransactionManagement
public class SproutFormsServiceImpl implements SproutFormsService {

    @PersistenceContext(unitName = "sproutFormSubmissions_PU")
    private EntityManager entityManager;

    @EJB
    private FormSubmissionService formSubmissionService;

    private FormsWebService formsWebService = null;

    @PostConstruct
    private void init() {

//        BindingProvider port = null;
//        //Set timeout until a connection is established
//        ((BindingProvider)port).getRequestContext().put("javax.xml.ws.client.connectionTimeout", "12000");
//
//        //Set timeout until the response is received
//        ((BindingProvider) port).getRequestContext().put("javax.xml.ws.client.receiveTimeout", "6000");


        FormsWebServiceImplService formsWebServiceImplService = new FormsWebServiceImplService();
//        Client client = ClientProxy.getClient(formsWebServiceImplService);


        formsWebService = formsWebServiceImplService.getFormsAPIWSPort();
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
    public FormDeliveryStatus deliverToInbox(String mrn, String publicationKey, String provider, String expirationDateString) {
        try {
            if (formsWebService == null) init();

            if (formsWebService != null) {
                String orgAuthKey = System.getProperty("edu.harvard.mgh.lcs.ihealthspace.module.forms.sprout.authToken");

                if (!StringUtils.isEmpty(orgAuthKey)) {
                    List<IdentityTO> identityTOList = new ArrayList<IdentityTO>();
                    IdentityTO identityTO = new IdentityTO();
                    identityTO.setScheme("mgh");
                    identityTO.setId(mrn);
                    identityTOList.add(identityTO);

                    String deliveryKey = String.format("%s@appid.fo.partners.org", StringUtils.getGuidClean());

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
    public List<FormInstanceTO> getSproutInbox(String identityId, Set<String> publicationKeys) {

        if (publicationKeys != null && publicationKeys.size() > 0) {

            List<String> publicationKeysList = new ArrayList<String>();
            for (String publicationKey : publicationKeys) publicationKeysList.add(publicationKey);

            if (formsWebService == null) init();

            if (formsWebService != null) {

                String orgAuthKey = System.getProperty("edu.harvard.mgh.lcs.ihealthspace.module.forms.sprout.authToken");

                if (!StringUtils.isEmpty(orgAuthKey)) {

                    List<IdentityTO> identities = new ArrayList<IdentityTO>();
                    IdentityTO identityMrn = new IdentityTO();
                    identityMrn.setScheme("mgh");
                    identityMrn.setId(identityId);
                    identities.add(identityMrn);

                    FormDeliveryTO formDeliveryTO = formsWebService.getFormsByIdentityAndPublications(orgAuthKey, identities, publicationKeysList, true);

                    if (formDeliveryTO != null) {
                        List<FormInstanceTO> forms = new ArrayList<FormInstanceTO>();

                        List<FormInstanceTO> formInstanceTOList = formDeliveryTO.getFormInstances();
                        if (formInstanceTOList != null && formInstanceTOList.size() > 0) {
                            for (FormInstanceTO formInstanceTO : formInstanceTOList) {
                                if (!StringUtils.isEmpty(formInstanceTO.getDeliveryKey())) {
                                    SproutStudyConstantService.SubmissionStatus submissionStatus = formSubmissionService.getSubmissionStatus(formInstanceTO.getPublicationKey(), formInstanceTO.getInstanceId(), formInstanceTO.getDeliveryKey());
                                    if (submissionStatus != null) {
                                        formInstanceTO.setAdminStatus(submissionStatus.toString());
                                    }
                                }
                                forms.add(formInstanceTO);
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
    public String applyForNonce(String user, String instanceId) {
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
                    return formsWebService.applyForNonce(orgAuthKey, identities, instanceId);
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
