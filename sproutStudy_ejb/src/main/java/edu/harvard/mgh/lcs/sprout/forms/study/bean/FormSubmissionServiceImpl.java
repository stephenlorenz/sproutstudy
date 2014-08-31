package edu.harvard.mgh.lcs.sprout.forms.study.bean;

import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.FormSubmissionService;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.SproutStudyConstantService;
import edu.harvard.mgh.lcs.sprout.forms.study.to.*;
import edu.harvard.mgh.lcs.sprout.forms.study.util.StringUtils;
import edu.harvard.mgh.lcs.sprout.study.model.formSubmission.*;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.milyn.Smooks;
import org.milyn.container.ExecutionContext;
import org.milyn.payload.JavaResult;
import org.xml.sax.SAXException;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.jms.JMSException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.*;

@Stateless
@Remote(FormSubmissionService.class)
@TransactionManagement
public class FormSubmissionServiceImpl implements FormSubmissionService, SproutStudyConstantService {

    @PersistenceContext(unitName = "sproutFormSubmissions_PU")
    private EntityManager entityManager;

    @PersistenceContext(unitName = "sproutAudit_PU")
    private EntityManager entityManagerSproutAudit;

    private Map<SubmissionStatus, VSubmissionStatusEntity> statusMap = new HashMap<SubmissionStatus, VSubmissionStatusEntity>();

    private Set<String> verifiableSchemas = null;

    @Override
    public String postFormSubmission(String orgAuthKey, String trackingCode, String submissionModel) {
        if (!StringUtils.isEmpty(submissionModel)) {
            return logFormSubmissionEvent(orgAuthKey, trackingCode, submissionModel, SubmissionStatus.NEW, null);
        }
        return "ERR";
    }

    @Override
    public String logFormSubmissionEvent(String orgAuthKey, String trackingCode, String submissionModel, SubmissionStatus status, String message) {

        SubmissionEntity submissionEntity = processSubmissionMetadata(trackingCode, submissionModel);

        if (submissionEntity != null) {
            if (status != null) {
                LogEntity logEntity = new LogEntity();
                logEntity.setSubmission(submissionEntity);
                logEntity.setStatus(statusMap.get(status));
                logEntity.setMessage(message);
                entityManager.persist(logEntity);
            }
            return "ACK";
        }
        return "ERR: Invalid Form Submission";
    }

    @SuppressWarnings("unchecked")
    private SubmissionEntity processSubmissionMetadata(String trackingCode, String submissionModel) {
        Smooks smooks = null;

        if (!StringUtils.isEmpty(submissionModel)) {
            try {
                smooks = new Smooks("smooks-submission-config.xml");

                ExecutionContext executionContext = smooks.createExecutionContext();
                JavaResult javaResult = new JavaResult();

                // Filter the input message to extract, using the execution context...
                smooks.filterSource(executionContext, new StreamSource(StringUtils.stringToInputStream(submissionModel)), javaResult);

                List<IdentityTO> identities = (List<IdentityTO>) javaResult.getBean("identities");
                List<AssertionTO> assertions = (List<AssertionTO>) javaResult.getBean("assertions");
                List<ParameterTO> parameters = (List<ParameterTO>) javaResult.getBean("parameters");
                SubmissionTO submission = (SubmissionTO) javaResult.getBean("submission");

                SubmissionEntity submissionEntity = findSubmissionByInstanceId(submission.getInstanceId());
                if (submissionEntity != null) {
                    clearSubmissionData(submissionEntity);
                    entityManager.refresh(submissionEntity);
                } else {
                    submissionEntity = new SubmissionEntity();
                }


                submissionEntity.setFormTitle(submission.getFormTitle());
                submissionEntity.setFormDescription(submission.getFormDescription());
                submissionEntity.setInstanceId(submission.getInstanceId());
                submissionEntity.setSubmissionData(submissionModel);
                submissionEntity.setPublicationKey(submission.getPublicationKey());
                submissionEntity.setDeliveryKey(submission.getDeliveryKey());
                submissionEntity.setSubmissionDate(submission.getTimestamp());
                submissionEntity.setStatus(statusMap.get(SubmissionStatus.NEW));
                submissionEntity.setTrackingCode(trackingCode);
                submissionEntity = entityManager.merge(submissionEntity);

                if (identities != null && identities.size() > 0) {
                    for (IdentityTO identityTO : identities) {
                        IdentifierEntity identifierEntity = new IdentifierEntity();
                        identifierEntity.setSubmission(submissionEntity);
                        identifierEntity.setSchema(identityTO.getSchema());
                        identifierEntity.setIdentifier(identityTO.getIdentifier());
                        entityManager.persist(identifierEntity);
                    }
                }
                if (assertions != null && assertions.size() > 0) {
                    int positionNo = 0;
                    for (AssertionTO assertionTO : assertions) {
                        AssertionEntity assertionEntity = new AssertionEntity();
                        assertionEntity.setSubmission(submissionEntity);
                        assertionEntity.setName(assertionTO.getName());
                        assertionEntity.setValue(assertionTO.getValue());
                        assertionEntity.setIdentifier(assertionTO.isIdentity());
                        assertionEntity.setLabel(assertionTO.getLabel());
                        assertionEntity.setVariableName(assertionTO.getVariableName());
                        assertionEntity.setSearchField(assertionTO.getSearchField());
                        assertionEntity.setPositionNo(positionNo++);
                        entityManager.persist(assertionEntity);
                    }
                }
                if (parameters != null && parameters.size() > 0) {
                    for (ParameterTO parameterTO : parameters) {
                        ParameterEntity parameterEntity = new ParameterEntity();
                        parameterEntity.setSubmission(submissionEntity);
                        parameterEntity.setName(parameterTO.getName());
                        parameterEntity.setValue(parameterTO.getValue());
                        entityManager.persist(parameterEntity);
                    }
                }

                return submissionEntity;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } finally {
                if (smooks != null) smooks.close();
            }
        } else {
            try {
                Query query = entityManager.createNamedQuery(SubmissionEntity.FIND_BY_TRACKING_CODE);
                query.setParameter("trackingCode", trackingCode);
                SubmissionEntity submissionEntity = (SubmissionEntity) query.getSingleResult();
                if (submissionEntity != null) return submissionEntity;
            } catch (NoResultException e) {
            }
        }

        return null;
    }

    private void clearSubmissionData(SubmissionEntity submissionEntity) {
        Set<AssertionEntity> assertions = submissionEntity.getAssertions();
        if (assertions != null) {
            for (AssertionEntity assertion : assertions) entityManager.remove(assertion);
        }
        Set<IdentifierEntity> identifiers = submissionEntity.getIdentifiers();
        if (identifiers != null) {
            for (IdentifierEntity identifier : identifiers) entityManager.remove(identifier);
        }
        Set<ParameterEntity> parameters = submissionEntity.getParameters();
        if (parameters != null) {
            for (ParameterEntity parameter : parameters) entityManager.remove(parameter);
        }
    }

    @Override
    public SubmissionEntity findSubmissionByInstanceId(String instanceId) {
        try {
            Query query = entityManager.createNamedQuery(SubmissionEntity.FIND_BY_INSTANCE_ID);
            query.setParameter("instanceId", instanceId);
            return (SubmissionEntity) query.getSingleResult();
        } catch (NoResultException e) {
        }
        return null;
    }

    private SubmissionEntity findSubmissionByKeys(String instanceId, String deliveryKey, String publicationKey) {
        try {
            Query query = entityManager.createNamedQuery(SubmissionEntity.FIND_BY_KEYS);
            query.setParameter("instanceId", instanceId);
            query.setParameter("deliveryKey", deliveryKey);
            query.setParameter("publicationKey", publicationKey);
            return (SubmissionEntity) query.getSingleResult();
        } catch (NoResultException e) {
        }
        return null;
    }

    //	private Submission unmarshallSubmissionModel(String submission) {
    //		try {
    //			JAXBContext context = JAXBContext.newInstance(Submission.class);
    //			Unmarshaller unmarshaller = context.createUnmarshaller();
    //			return (Submission) unmarshaller.unmarshal(new StringReader(submission));
    //		} catch (Exception e) {
    //			e.printStackTrace();
    //		}
    //		return null;
    //	}

    @SuppressWarnings("unchecked")
    @PostConstruct
    private void init() {

        Query query = entityManager.createNamedQuery(VSubmissionStatusEntity.ALL);
        List<VSubmissionStatusEntity> statuses = query.getResultList();

        if (statuses != null && statuses.size() > 0) {
            for (VSubmissionStatusEntity vSubmissionStatusEntity : statuses) {
                statusMap.put(SubmissionStatus.valueOf(vSubmissionStatusEntity.getCode().toUpperCase()), vSubmissionStatusEntity);
            }
        }

        if (System.getProperty("edu.harvard.mgh.lcs.sprout.forms.study.verifiableSchemas") != null) {
            verifiableSchemas = new HashSet<String>();
            String[] verifiableSchemasRawArray = System.getProperty("edu.harvard.mgh.lcs.sprout.forms.study.verifiableSchemas").split(",");
            if (verifiableSchemasRawArray.length > 0) {
                for (String verifiableSchema : verifiableSchemasRawArray)
                    verifiableSchemas.add(verifiableSchema.trim());
            }
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public SubmissionStatus getSubmissionStatus(String publicationKey, String instanceId, String deliveryKey) {
        if (publicationKey != null && instanceId != null && deliveryKey != null) {
            SubmissionEntity submissionEntity = findSubmissionByKeys(instanceId, deliveryKey, publicationKey);
            if (submissionEntity != null) {
                return SubmissionStatus.valueOf(submissionEntity.getStatus().getCode().toUpperCase());
            }
        }
        return SubmissionStatus.NONE;
    }

    @Override
    public SubmissionStatus pushToEMR(String instanceId) {
        if (instanceId != null) {
            SubmissionEntity submissionEntity = findSubmissionByInstanceId(instanceId);

            if (submissionEntity != null) {
                if (postToEsb(submissionEntity)) {
                    submissionEntity.setStatus(statusMap.get(SubmissionStatus.PUSHED));
                    submissionEntity = entityManager.merge(submissionEntity);
                    return SubmissionStatus.valueOf(submissionEntity.getStatus().getCode().toUpperCase());
                }
            }
        }
        return SubmissionStatus.ERROR;
    }

    private boolean postToEsb(SubmissionEntity submissionEntity) {

        try {
            if (submissionEntity != null) {

                if (System.getProperty("org.sproutforms.esb.url") != null) {
                    HttpClient client = new HttpClient();
                    //				client.getState().setCredentials(
                    //						new AuthScope(formsAuthHost, formsAuthPort, formsAuthRealm),
                    //						new UsernamePasswordCredentials(orgAuthKey, psk)
                    //						);

                    String postUrl = String.format(System.getProperty("org.sproutforms.esb.url") != null ? System.getProperty("org.sproutforms.esb.url") : "", submissionEntity.getInstanceId(), submissionEntity.getPublicationKey(), SproutStudyConstantService.SENDER_FRONT_OFFICE);

                    PostMethod postMethod = new PostMethod(postUrl);
                    postMethod.setRequestEntity(new StringRequestEntity(submissionEntity.getSubmissionData(), "text/xml", "UTF-8"));

                    try {
                        int returnCode = client.executeMethod(postMethod);

                        if (returnCode == HttpStatus.SC_ACCEPTED) {
                            log(submissionEntity, "Form Submission Success", "Post Accepted: " + returnCode);
                            return true;
                        } else if (returnCode == HttpStatus.SC_OK) {
                            log(submissionEntity, "Form Submission Success", "Post Accepted: " + returnCode);
                            return true;
                        } else if (returnCode == HttpStatus.SC_NOT_IMPLEMENTED) {
                            postMethod.getResponseBodyAsString();
                            log(submissionEntity, "Form Submission Failed", "The Post method is not implemented by this URI: " + postUrl + ": " + returnCode);
                            throw new JMSException("The Post method is not implemented by this URI: " + postUrl);
                        } else {
                            log(submissionEntity, "Form Submission Failed", "The Post was not accepted to " + postUrl + ".  Return code: " + returnCode);
                            throw new JMSException("The Post was not accepted to: " + postUrl + ".  Return code: " + returnCode);
                        }
                    } catch (Exception e) {
                        log(submissionEntity, "Form Submission Failed", "The Post method is not implemented by this URI: " + postUrl, e);
                        throw new JMSException(e.getMessage());
                    } finally {
                        postMethod.releaseConnection();
                    }
                } else {
                    log(submissionEntity, "Form Submission Failed", "The system property \"org.sproutforms.esb.url\" has not been set.  Please configure this setting to point a URL that accepts a POST method.");
                    throw new JMSException("The system property \"org.sproutforms.esb.url\" has not been set.  Please configure this setting to point a URL that accepts a POST method.");
                }

            } else {
                log(submissionEntity, "Form Submission Failed", "The submission object IS NULL.");
                throw new JMSException("The submission object IS NULL.");
            }
        } catch (JMSException e) {
            log(submissionEntity, "Form Submission Failed", "Form Submission POST failed: " + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return false;
    }

    private void log(SubmissionEntity submissionEntity, String message, String detail) {
        log(submissionEntity, message, detail, null);
    }

    private void log(SubmissionEntity submissionEntity, String message, String detail, Exception exception) {
        LogEntity logEntity = new LogEntity();
        logEntity.setSubmission(submissionEntity);
        logEntity.setStatus(statusMap.get(SubmissionStatus.PUSHED));
        logEntity.setMessage(message + (!StringUtils.isEmpty(detail) ? ": " + detail : "") + (exception != null ? ": " + exception.getMessage() : ""));
        entityManager.persist(logEntity);
    }

    @Override
    public SubmissionStatus returnToSender(String instanceId) {
        if (instanceId != null) {
            SubmissionEntity submissionEntity = findSubmissionByInstanceId(instanceId);
            if (submissionEntity != null) {
                submissionEntity.setStatus(statusMap.get(SubmissionStatus.REVOKED));
                submissionEntity = entityManager.merge(submissionEntity);
                return SubmissionStatus.valueOf(submissionEntity.getStatus().getCode().toUpperCase());
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<FormSubmissionTO> getFormSubmissions(Set<String> publicationKeys, boolean mutableInd, String principal) {

        List<FormSubmissionTO> formSubmissionTOs = new ArrayList<FormSubmissionTO>();

        if (publicationKeys != null && publicationKeys.size() > 0) {
            Set<Boolean> mutableSet = new HashSet<Boolean>();
            mutableSet.add(new Boolean(true));
            if (!mutableInd) {
                mutableSet.add(new Boolean(false));
            }

            Query query = entityManager.createNamedQuery(SubmissionEntity.FIND_BY_PUBLICATION_KEYS);
            query.setParameter("publicationKeys", publicationKeys);
            query.setParameter("mutableInd", mutableSet);

            List<SubmissionEntity> submissionEntities = query.getResultList();

            if (submissionEntities != null && submissionEntities.size() > 0) {
                for (SubmissionEntity submissionEntity : submissionEntities) {
                    FormSubmissionTO formSubmissionTO = assembleFormSubmissionTO(submissionEntity, principal);
                    formSubmissionTOs.add(formSubmissionTO);
                }
                return formSubmissionTOs;
            }
        }

        return null;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public FormSubmissionTO getFormSubmission(String instanceId, String principal) {
        SubmissionEntity submissionEntity = findSubmissionByInstanceId(instanceId);
        if (submissionEntity != null) return assembleFormSubmissionTO(submissionEntity, principal);
        return null;
    }

    private FormSubmissionTO assembleFormSubmissionTO(SubmissionEntity submissionEntity, String principal) {
        FormSubmissionTO formSubmissionTO = new FormSubmissionTO();
        formSubmissionTO.setPublicationKey(submissionEntity.getPublicationKey());
        formSubmissionTO.setInstanceId(submissionEntity.getInstanceId());
        formSubmissionTO.setDeliveryKey(submissionEntity.getDeliveryKey());
        formSubmissionTO.setStatus(submissionEntity.getStatus().getCode());
        formSubmissionTO.setFormTitle(submissionEntity.getFormTitle());
        formSubmissionTO.setFormDescription(submissionEntity.getFormDescription());
        formSubmissionTO.setSubmissionDate(submissionEntity.getSubmissionDate());

        List<SubmissionAssertionTO> assertions = new ArrayList<SubmissionAssertionTO>();
        if (submissionEntity.getAssertions() != null && submissionEntity.getAssertions().size() > 0) {
            for (AssertionEntity assertionEntity : submissionEntity.getAssertions()) {
                SubmissionAssertionTO submissionAssertionTO = new SubmissionAssertionTO();
                submissionAssertionTO.setName(StringUtils.decodifyString(assertionEntity.getName()));
                submissionAssertionTO.setValue(assertionEntity.getValue());
                submissionAssertionTO.setIdentifierInd(assertionEntity.isIdentifier());
                submissionAssertionTO.setLabel(assertionEntity.getLabel());
                submissionAssertionTO.setVariableName(assertionEntity.getVariableName());
                submissionAssertionTO.setFieldCode(convertVariableNameToFieldCode(assertionEntity.getVariableName()));
                submissionAssertionTO.setSearchField(assertionEntity.getSearchField());

                assertions.add(submissionAssertionTO);
            }
        }
        formSubmissionTO.setAssertions(assertions);

        Set<SubmissionIdentifierTO> identifiers = new HashSet<SubmissionIdentifierTO>();
        if (submissionEntity.getIdentifiers() != null && submissionEntity.getIdentifiers().size() > 0) {
            for (IdentifierEntity identifierEntity : submissionEntity.getIdentifiers()) {
                SubmissionIdentifierTO submissionIdentifierTO = new SubmissionIdentifierTO();
                submissionIdentifierTO.setSchema(identifierEntity.getSchema().toUpperCase());
                submissionIdentifierTO.setIdentifier(identifierEntity.getIdentifier());

                if (isSchemaVerifiable(identifierEntity.getSchema())) formSubmissionTO.setVerified(true);

                identifiers.add(submissionIdentifierTO);
            }
        }
        formSubmissionTO.setIdentifiers(identifiers);

        Set<SubmissionParameterTO> parameters = new HashSet<SubmissionParameterTO>();
        if (submissionEntity.getParameters() != null && submissionEntity.getParameters().size() > 0) {
            for (ParameterEntity parameterEntity : submissionEntity.getParameters()) {
                SubmissionParameterTO submissionParameterTO = new SubmissionParameterTO();
                submissionParameterTO.setName(parameterEntity.getName());
                submissionParameterTO.setValue(parameterEntity.getValue());
                parameters.add(submissionParameterTO);
            }
        }
        formSubmissionTO.setParameters(parameters);

        if (!StringUtils.isEmpty(formSubmissionTO.getDeliveryKey())) {
//            String idxAppointmentId = getIdxAppointmentId(formSubmissionTO.getDeliveryKey());
//            if (idxAppointmentId != null) {
//                AppointmentTO appointmentTO = scheduleService.getAppointmentInfo(idxAppointmentId, principal);
//                if (appointmentTO != null) {
//                    formSubmissionTO.setAppointment(appointmentTO);
//                }
//            }
        }
        return formSubmissionTO;
    }

    private String getIdxAppointmentId(String deliveryKey) {
        if (!StringUtils.isEmpty(deliveryKey) && deliveryKey.indexOf("@appid.idx.partners.org") > 0) {
            return deliveryKey.substring(0, deliveryKey.indexOf("@appid.idx.partners.org"));
        }
        return deliveryKey;
    }

    private String convertVariableNameToFieldCode(String variableName) {
        if (!StringUtils.isEmpty(variableName)) {
            if (variableName.indexOf("_") > 0) {
                return variableName.substring(0, variableName.lastIndexOf("_"));
            }
        }
        return null;
    }

    private String convertFieldCodeToSubmissionFieldCode(String fieldCode) {
        if (!StringUtils.isEmpty(fieldCode)) {
            if (fieldCode.indexOf("_") > 0) {
                return fieldCode.substring(fieldCode.indexOf("_"));
            }
        }
        return null;
    }

    private boolean isSchemaVerifiable(String schema) {
        if (verifiableSchemas != null && verifiableSchemas.size() > 0) {
            for (String verifiedSchema : verifiableSchemas) {
                if (verifiedSchema.equalsIgnoreCase(schema)) return true;
            }
        }
        return false;
    }

    @Override
    public BooleanTO appendProviderAsParameter(String instanceId, String partnersUsername, String principal) {
        if (!StringUtils.isEmpty(instanceId)) {
            SubmissionEntity submissionEntity = findSubmissionByInstanceId(instanceId);
            if (submissionEntity != null) {

                String submissionModel = submissionEntity.getSubmissionData();

                if (!StringUtils.isEmpty(submissionModel) && !StringUtils.isEmpty(partnersUsername)) {
                    try {
                        TransformerFactory factory = TransformerFactory.newInstance();
                        InputStream stream = getClass().getClassLoader().getResourceAsStream("/edu/harvard/mgh/lcs/sprout/forms/study/stylesheets/addParameter.xsl");
                        Transformer transformer = factory.newTransformer(new javax.xml.transform.stream.StreamSource(stream));
                        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

                        try {
                            transformer.setParameter("name", "pun");
                            transformer.setParameter("value", partnersUsername);
                            StreamSource ss = new StreamSource(new StringReader(submissionModel));
                            StringWriter writer = new StringWriter();
                            transformer.transform(ss, new javax.xml.transform.stream.StreamResult(writer));
                            submissionModel = writer.toString();
                        } catch (TransformerConfigurationException e) {
                            e.printStackTrace();
                        } catch (TransformerException e) {
                            e.printStackTrace();
                        }
                    } catch (TransformerConfigurationException e1) {
                        e1.printStackTrace();
                        return new BooleanTO(false);
                    }
                    submissionEntity.setSubmissionData(submissionModel);
                    submissionEntity.setActivityDate(new Date());
                    entityManager.merge(submissionEntity);
                    System.out.println("submissionModel = " + submissionModel);
                    return new BooleanTO(true);
                }
            }
        }
        return new BooleanTO(false);
    }

    @Override
    public BooleanTO syncFormSubmission(String instanceId, String[] verifiedIdentifiers, String[] matchedIdentifiers, String[] matchedAssertions, String principal) {
        if (!StringUtils.isEmpty(instanceId)) {
            SubmissionEntity submissionEntity = findSubmissionByInstanceId(instanceId);
            if (submissionEntity != null) {

                if (matchedAssertions != null && matchedAssertions.length > 0) {
                    Set<AssertionEntity> assertionEntities = submissionEntity.getAssertions();
                    Map<String, AssertionEntity> assertionEntityMap = new HashMap<String, AssertionEntity>();
                    if (assertionEntities != null && assertionEntities.size() > 0) {
                        for (AssertionEntity assertionEntity : assertionEntities) {
                            assertionEntityMap.put(assertionEntity.getVariableName().toLowerCase(), assertionEntity);
                        }
                    }

                    for (String matchedAssertion : matchedAssertions) {
                        String variableName = extractAssertionVariableName(matchedAssertion);
                        String value = extractAssertionValue(matchedAssertion);

                        if (!StringUtils.isEmpty(variableName)) {
                            AssertionEntity assertionEntity = assertionEntityMap.get(variableName.toLowerCase());
                            if (assertionEntity != null) {
                                assertionEntity.setActivityDate(new Date());
                                if (StringUtils.isEmpty(value) && assertionEntity.isIdentifier()) {
                                    for (String matchedIdentity : matchedIdentifiers) {
                                        String identifierIdentifier = extractIdentifierId(matchedIdentity);
                                        String identifierSchema = extractIdentifierSchema(matchedIdentity);
                                        if (identifierSchema.equalsIgnoreCase("mgh")) {
                                            value = identifierIdentifier;
                                            break;
                                        }
                                    }
                                }
                                assertionEntity.setValue(value == null ? "" : value);
                                entityManager.merge(assertionEntity);
                            }
                        }
                    }
                }

                if (matchedIdentifiers != null && matchedIdentifiers.length > 0) {
                    Set<IdentifierEntity> identifierEntities = submissionEntity.getIdentifiers();

                    Set<String> usedIdentifiers = new HashSet<String>();

                    for (String matchedIdentity : matchedIdentifiers) {
                        if (!usedIdentifiers.contains(matchedIdentity)) {
                            boolean foundMatch = false;
                            String identifierIdentifier = extractIdentifierId(matchedIdentity);
                            String identifierSchema = extractIdentifierSchema(matchedIdentity);

                            if (!StringUtils.isEmpty(identifierIdentifier) && !StringUtils.isEmpty(identifierSchema)) {
                                for (IdentifierEntity identifierEntity : identifierEntities) {
                                    if (identifierSchema.equalsIgnoreCase(identifierEntity.getSchema())) {
                                        foundMatch = true;
                                        if (!extractIdentifierId(matchedIdentity).equalsIgnoreCase(identifierEntity.getIdentifier())) {
                                            identifierEntity.setActivityDate(new Date());
                                            identifierEntity.setIdentifier(identifierIdentifier);
                                            entityManager.merge(identifierEntity);
                                        }
                                        break;
                                    }
                                }
                                if (!foundMatch) {
                                    IdentifierEntity identifierEntity = new IdentifierEntity();
                                    identifierEntity.setSubmission(submissionEntity);
                                    identifierEntity.setSchema(identifierSchema);
                                    identifierEntity.setIdentifier(identifierIdentifier);
                                    entityManager.persist(identifierEntity);

                                }
                            }
                            usedIdentifiers.add(matchedIdentity);
                        }
                    }
                }

//				String submissionModel = submissionEntity.getSubmissionData();
//
//				if (!StringUtils.isEmpty(submissionModel)) {
//					try {
//						TransformerFactory factory = TransformerFactory.newInstance();
//						InputStream stream = getClass().getClassLoader().getResourceAsStream("/edu/harvard/mgh/lcs/sprout/forms/study/stylesheets/updateModel.xsl");
//						Transformer transformer = factory.newTransformer(new javax.xml.transform.stream.StreamSource(stream));
//						transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
//
//						if (matchedAssertions != null && matchedAssertions.length > 0) {
//							for (String matchedAssertion : matchedAssertions) {
//								String variableName = extractAssertionVariableName(matchedAssertion);
//								String value = extractAssertionValue(matchedAssertion);
//
//								if (!StringUtils.isEmpty(variableName) && !StringUtils.isEmpty(value)) {
//									String submissionFieldCode = convertFieldCodeToSubmissionFieldCode(convertVariableNameToFieldCode(variableName));
//
//									try {
//										transformer.setParameter("fieldCode", submissionFieldCode);
//										transformer.setParameter("fieldValue", value);
//										StreamSource ss = new StreamSource(new StringReader(submissionModel));
//										StringWriter writer = new StringWriter();
//										transformer.transform(ss, new javax.xml.transform.stream.StreamResult(writer));
//										submissionModel = writer.toString();
//									} catch (TransformerConfigurationException e) {
//										e.printStackTrace();
//									} catch (TransformerException e) {
//										e.printStackTrace();
//									}
//								}
//
//
//							}
//						}
//
//						stream = getClass().getClassLoader().getResourceAsStream("/edu/harvard/mgh/lcs/sprout/forms/study/stylesheets/updateAssertion.xsl");
//						transformer = factory.newTransformer(new javax.xml.transform.stream.StreamSource(stream));
//						transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
//
//						if (matchedAssertions != null && matchedAssertions.length > 0) {
//							for (String matchedAssertion : matchedAssertions) {
//								String variableName = extractAssertionVariableName(matchedAssertion);
//								String value = extractAssertionValue(matchedAssertion);
//
//								if (!StringUtils.isEmpty(variableName) && !StringUtils.isEmpty(value)) {
//									try {
//										transformer.setParameter("variableName", variableName);
//										transformer.setParameter("fieldValue", value);
//										StreamSource ss = new StreamSource(new StringReader(submissionModel));
//										StringWriter writer = new StringWriter();
//										transformer.transform(ss, new javax.xml.transform.stream.StreamResult(writer));
//										submissionModel = writer.toString();
//									} catch (TransformerConfigurationException e) {
//										e.printStackTrace();
//									} catch (TransformerException e) {
//										e.printStackTrace();
//									}
//								}
//
//							}
//						}
//
//						stream = getClass().getClassLoader().getResourceAsStream("/edu/harvard/mgh/lcs/sprout/forms/study/stylesheets/addIdentities.xsl");
//						transformer = factory.newTransformer(new javax.xml.transform.stream.StreamSource(stream));
//						transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
//
//						if (matchedIdentifiers != null && matchedIdentifiers.length > 0) {
//							for (String matchedIdentifier : matchedIdentifiers) {
//								String identifierSchema = extractIdentifierSchema(matchedIdentifier);
//								String identifierIdentifier = extractIdentifierId(matchedIdentifier);
//
//								if (!StringUtils.isEmpty(identifierIdentifier) && !StringUtils.isEmpty(identifierSchema)) {
//									try {
//										transformer.setParameter("schema", identifierSchema);
//										transformer.setParameter("identity", identifierIdentifier);
//										StreamSource ss = new StreamSource(new StringReader(submissionModel));
//										StringWriter writer = new StringWriter();
//										transformer.transform(ss, new javax.xml.transform.stream.StreamResult(writer));
//										submissionModel = writer.toString();
//									} catch (TransformerConfigurationException e) {
//										e.printStackTrace();
//									} catch (TransformerException e) {
//										e.printStackTrace();
//									}
//								}
//							}
//						}
//					} catch (TransformerConfigurationException e1) {
//						e1.printStackTrace();
//						return new BooleanTO(false);
//					}
//					submissionEntity.setSubmissionData(submissionModel);
//					submissionEntity.setActivityDate(new Date());
//					submissionEntity = entityManager.merge(submissionEntity);
//				}
            }
            return new BooleanTO(true);
        }
        return new BooleanTO(false);
    }

    @Override
    public BooleanTO isPatientVerified(String mrn, String user) {
        if (!StringUtils.isEmpty(mrn)) {
            Query query = entityManager.createNamedQuery(IdentifierEntity.FIND_SCHEMA_VALUE);
            query.setParameter("schema", "mgh");
            query.setParameter("identifier", mrn);
            return new BooleanTO(query.getResultList().size() > 0);
        }
        return new BooleanTO(false);
    }

    @Override
    public BooleanTO isPatientAssertive(String mrn, String user) {
        if (!StringUtils.isEmpty(mrn)) {
            Query query = entityManager.createNamedQuery(AssertionEntity.FIND_MRN_VALUE);
            query.setParameter("mrn", mrn);
            return new BooleanTO(query.getResultList().size() > 0);
        }
        return new BooleanTO(false);
    }

    private String extractIdentifierSchema(String identifier) {
        return identifier.indexOf("@") > 0 ? identifier.substring(identifier.lastIndexOf("@") + 1) : null;
    }

    private String extractIdentifierId(String identifier) {
        return identifier.indexOf("@") > 0 ? identifier.substring(0, identifier.lastIndexOf("@")) : null;
    }

    private String extractAssertionVariableName(String assertion) {
        return assertion.indexOf("@") > -1 ? assertion.substring(assertion.lastIndexOf("@") + 1) : null;
    }

    private String extractAssertionValue(String assertion) {
        return assertion.indexOf("@") > 0 ? assertion.substring(0, assertion.lastIndexOf("@")) : "";
    }

}
