package edu.harvard.mgh.lcs.sprout.forms.study.bean;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.*;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.AuditService;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.SproutStudyConstantService;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.StudyService;
import edu.harvard.mgh.lcs.sprout.forms.study.beanws.Result;
import edu.harvard.mgh.lcs.sprout.forms.study.exception.DuplicateCohortNameException;
import edu.harvard.mgh.lcs.sprout.forms.study.exception.UnauthorizedActionException;
import edu.harvard.mgh.lcs.sprout.forms.study.to.*;
import edu.harvard.mgh.lcs.sprout.forms.study.to.IdentityTO;
import edu.harvard.mgh.lcs.sprout.forms.study.util.StringUtils;
import edu.harvard.mgh.lcs.sprout.study.model.study.*;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

@Stateless
@Remote(StudyService.class)
@TransactionManagement
public class StudyServiceImpl implements StudyService, SproutStudyConstantService {

    @PersistenceContext(unitName = "sproutStudy_PU")
    private EntityManager entityManager;

    @EJB
    private AuditService auditService;

    public static final String COHORT_ATTR_QUERY_URL = "QUERY";
    public static final String COHORT_ATTR_IDENTITY_SCHEMA_PRIMARY = "IDENTITY_SCHEMA_PRIMARY";
    public static final String COHORT_ATTR_QUERY_AUTH_USERNAME = "QUERY_AUTH_USERNAME";
    public static final String COHORT_ATTR_QUERY_AUTH_PASSWORD = "QUERY_AUTH_PASSWORD";

    @Override
    public List<CohortTO> getAuthorizedCohorts(String username) {
        List<CohortTO> cohortTOList = new ArrayList<CohortTO>();
        try {
            UserEntity userEntity = getUserFromUsername(username);
            if (userEntity != null) {
                List<CohortAuthEntity> cohortAuthEntityList = userEntity.getCohortAuthorizations();
                if (cohortAuthEntityList != null && cohortAuthEntityList.size() > 0) {
                    for (CohortAuthEntity cohortAuthEntity : cohortAuthEntityList) {
                        CohortEntity cohortEntity = cohortAuthEntity.getCohort();
                        CohortTO cohortTO = new CohortTO();
                        cohortTO.setId(cohortEntity.getId());
                        cohortTO.setName(cohortEntity.getName());
                        cohortTO.setDescription(cohortEntity.getDescription());
                        cohortTO.setActivityDate(cohortEntity.getActivityDate());
                        cohortTO.setCohortKey(cohortEntity.getCohortKey());
                        cohortTO.setActive(cohortEntity.isActive());

                        List<CohortAttrTO> cohortAttrTOList = getCohortAttributes(cohortEntity);
                        cohortTO.setAttributes(cohortAttrTOList);
                        cohortTO.setForms(getCohortForms(cohortEntity));
                        cohortTO.setCohortQueryURL(getCohortAttr(cohortAttrTOList, COHORT_ATTR_QUERY_URL));
                        cohortTO.setCohortSubjectSchema(getCohortAttr(cohortAttrTOList, COHORT_ATTR_IDENTITY_SCHEMA_PRIMARY));

                        cohortTOList.add(cohortTO);
                    }
                }
            }
        } catch (NoResultException e) {
        }
        return cohortTOList;
    }

    private String getCohortAttr(List<CohortAttrTO> cohortAttrTOList, String attrKey) {
        if (cohortAttrTOList != null && cohortAttrTOList.size() > 0) {
            for (CohortAttrTO cohortAttrTO : cohortAttrTOList) {
                if (cohortAttrTO.getName().equalsIgnoreCase(attrKey)) {
                    return cohortAttrTO.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public List<Result> findCohortMember(String user, String cohortQueryURL, String query) {
        List<CohortMemberTO> cohortMemberTOList = new ArrayList<CohortMemberTO>();
        if (!StringUtils.isEmpty(cohortQueryURL)) {
            try {
                String queryUrl = String.format(cohortQueryURL, URLEncoder.encode(query, "UTF-8"));

                HttpClient httpClient = new HttpClient();
                //				client.getState().setCredentials(
                //						new AuthScope(formsAuthHost, formsAuthPort, formsAuthRealm),
                //						new UsernamePasswordCredentials(orgAuthKey, psk)
                //						);


                GetMethod getMethod = new GetMethod(queryUrl);
                int status = httpClient.executeMethod(getMethod);

                if (status == 200) {
                    String response = getMethod.getResponseBodyAsString();
                    if (!StringUtils.isEmpty(response)) {
//                        System.out.println("response = " + response);

                        List<Result> results = new ObjectMapper().readValue(response, new TypeReference<List<Result>>(){});
                        return results;
                    }
                }

                getMethod.releaseConnection();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<StudyInboxTO> getStudyInbox(String username, CohortTO cohortTO) {
        List<StudyInboxTO> studyInboxTOList = new ArrayList<StudyInboxTO>();

        UserEntity userEntity = getUserFromUsername(username);

        if (userEntity != null) {
            Query query = entityManager.createNamedQuery(InboxEntity.GET_USER_INBOX);
            query.setParameter("recipient", userEntity);
            List<InboxEntity> inboxEntities = query.getResultList();

            if (inboxEntities != null && inboxEntities.size() > 0) {
                for (InboxEntity inboxEntity : inboxEntities) {
                    if (inboxEntity != null && inboxEntity.getCohort() != null && inboxEntity.getCohort().getId() == cohortTO.getId()) studyInboxTOList.add(constructStudyInboxTO(inboxEntity, cohortTO));
                }
            }
        }

        return studyInboxTOList;
    }

    @Override
    public StudyInboxTO deleteInboxMessage(int id, CohortTO cohortTO) {
        InboxEntity inboxEntity = entityManager.find(InboxEntity.class, id);
        if (inboxEntity != null) {
            inboxEntity.setStatus(getVInboxStatusEntity(InboxStatus.DELETE));
            inboxEntity.setActivityDate(new Date());
            return constructStudyInboxTO(entityManager.merge(inboxEntity), cohortTO);
        }
        return null;
    }

    @Override
    public StudyInboxTO markInboxMessageAsRead(int id, CohortTO cohortTO) {
        return changeInboxMessageStatus(id, cohortTO, InboxStatus.READ);
    }

    @Override
    public BooleanTO markInboxMessageAsRead(String instanceId) {
        if (StringUtils.isFull(instanceId)) {
            Query query = entityManager.createNamedQuery(InboxEntity.GET_USER_INBOX_BY_INSTANCE_ID);
            query.setParameter("instanceId", instanceId);
            List<InboxEntity> inboxEntities = query.getResultList();
            if (inboxEntities != null) {
                for (InboxEntity inboxEntity : inboxEntities) {
                    inboxEntity.setStatus(getVInboxStatusEntity(InboxStatus.READ));
                    inboxEntity.setActivityDate(new Date());
                    entityManager.merge(inboxEntity);
                }
                return new BooleanTO(true);
            }
        }
        return new BooleanTO(false);
    }

    @Override
    public StudyInboxTO changeInboxMessageStatus(int id, CohortTO cohortTO, InboxStatus inboxStatus) {
        InboxEntity inboxEntity = entityManager.find(InboxEntity.class, id);
        if (inboxEntity != null) {
            inboxEntity.setStatus(getVInboxStatusEntity(inboxStatus));
            inboxEntity.setActivityDate(new Date());
            return constructStudyInboxTO(entityManager.merge(inboxEntity), cohortTO);
        }
        return null;
    }

//    private StudyInboxTO constructStudyInboxTO(InboxEntity inboxEntity) {
//        return constructStudyInboxTO(inboxEntity, null);
//    }

    private StudyInboxTO constructStudyInboxTO(InboxEntity inboxEntity, CohortTO cohortTO) {

        if (inboxEntity != null && inboxEntity.getCohort() != null && inboxEntity.getCohort().getId() == cohortTO.getId()) {
            StudyInboxTO studyInboxTO = new StudyInboxTO();
            studyInboxTO.setId(inboxEntity.getId());
            studyInboxTO.setSubject(inboxEntity.getSubject());
            studyInboxTO.setBody(inboxEntity.getBody());
            studyInboxTO.setInstanceId(inboxEntity.getInstanceId());
            studyInboxTO.setSubjectName(inboxEntity.getSubjectName());
            studyInboxTO.setSubjectId(inboxEntity.getSubjectId());
            studyInboxTO.setForm(inboxEntity.getForm());
            studyInboxTO.setFormTitle(extractFormTitleFromJSON(inboxEntity.getForm()));
            studyInboxTO.setActivityDate(inboxEntity.getActivityDate());
            studyInboxTO.setDeliveryDate(inboxEntity.getDeliveryDate());
            studyInboxTO.setSender(constructUserTO(inboxEntity.getSender()));
            studyInboxTO.setCohortTO(constructCohortTO(inboxEntity.getCohort()));
            studyInboxTO.setRecipient(constructUserTO(inboxEntity.getRecipient()));
            studyInboxTO.setStatus(constructInboxStatus(inboxEntity.getStatus()));
            return studyInboxTO;
        }
        return null;
    }

    private CohortTO constructCohortTO(CohortEntity cohortEntity) {
        if (cohortEntity != null) {
            CohortTO cohortTO = new CohortTO();
            cohortTO.setId(cohortEntity.getId());
            cohortTO.setName(cohortEntity.getName());
            cohortTO.setDescription(cohortEntity.getDescription());
            cohortTO.setCohortKey(cohortEntity.getCohortKey());
            cohortTO.setActivityDate(cohortEntity.getActivityDate());

            List<CohortAttrTO> cohortAttrTOList = getCohortAttributes(cohortEntity);
            cohortTO.setAttributes(cohortAttrTOList);
            cohortTO.setForms(getCohortForms(cohortEntity));
            cohortTO.setCohortQueryURL(getCohortAttr(cohortAttrTOList, COHORT_ATTR_QUERY_URL));
            cohortTO.setCohortSubjectSchema(getCohortAttr(cohortAttrTOList, COHORT_ATTR_IDENTITY_SCHEMA_PRIMARY));
            return cohortTO;
        }
        return null;
    }

    public UserTO getUser(String username) {
        if (StringUtils.isFull(username)) {
            UserEntity userEntity = getUserFromUsername(username);
            if (userEntity != null) {
                return constructUserTO(userEntity);
            }
        }
        return null;
    }

    @Override
    public BooleanTO saveFormPublicationKey(String id, String publicationKey) {
        if (StringUtils.isFull(id, publicationKey) && StringUtils.isInteger(id) && publicationKeyIsUnique(publicationKey)) {
            FormEntity formEntity = entityManager.find(FormEntity.class, Integer.parseInt(id));
            if (formEntity != null) {
                FormEntity formEntityNew = new FormEntity();
                formEntityNew.setName(formEntity.getName());
                formEntityNew.setPublicationKey(formEntity.getPublicationKey());
                formEntityNew.setFormKey(formEntity.getFormKey());
                formEntityNew.setDemographic(formEntity.getDemographic());
                formEntityNew.setActivityDate(new Date());
                formEntityNew.setActive(false);
                entityManager.persist(formEntityNew);

                for (CohortFormEntity cohortFormEntityCurrent : formEntity.getCohortForms()) {
                    CohortFormEntity cohortFormEntity = new CohortFormEntity();
                    cohortFormEntity.setCohort(cohortFormEntityCurrent.getCohort());
                    cohortFormEntity.setForm(formEntityNew);
                    cohortFormEntity.setActivityDate(new Date());
                    entityManager.persist(cohortFormEntity);
                }

                for (FormAttrEntity formAttrEntityCurrent : formEntity.getFormAttributes()) {
                    FormAttrEntity formAttrEntity = new FormAttrEntity();
                    formAttrEntity.setForm(formEntityNew);
                    formAttrEntity.setFormAttr(formAttrEntityCurrent.getFormAttr());
                    formAttrEntity.setValue(formAttrEntityCurrent.getValue());
                    formAttrEntity.setActivityDate(new Date());
                    entityManager.persist(formAttrEntity);
                }

                formEntity.setPublicationKey(publicationKey);
                formEntity.setActivityDate(new Date());
                entityManager.merge(formEntity);

                return new BooleanTO(true);
            }
        }
        return new BooleanTO(false);
    }

    private boolean publicationKeyIsUnique(String publicationKey) {
        return StringUtils.isEmpty(getFormFromPublicationKey(publicationKey));
    }

    @Override
    public String getFormFromPublicationKey(String publicationKey) {
        if (StringUtils.isFull(publicationKey)) {
            try {
                Query query = entityManager.createNamedQuery(FormEntity.FIND_BY_PUBLICATION_KEY);
                query.setParameter("publicationKey", publicationKey);
                FormEntity formEntity = (FormEntity) query.getSingleResult();
                if (formEntity != null) return formEntity.getName();
            } catch (NoResultException e) {}
        }
        return null;
    }

    @Override
    public String getFormKeyFromPublicationKey(String publicationKey) {
        if (StringUtils.isFull(publicationKey)) {
            try {
                Query query = entityManager.createNamedQuery(FormEntity.FIND_BY_PUBLICATION_KEY);
                query.setParameter("publicationKey", publicationKey);
                FormEntity formEntity = (FormEntity) query.getSingleResult();
                if (formEntity != null) return formEntity.getFormKey();
            } catch (NoResultException e) {}
        }
        return null;
    }

    public List<FormEntity> getFormsKeyFromFormKey(String formKey) {
        if (StringUtils.isFull(formKey)) {
            Query query = entityManager.createNamedQuery(FormEntity.FIND_BY_FORM_KEY);
            query.setParameter("formKey", formKey);
            return query.getResultList();
        }
        return null;
    }

    @Override
    public Set<String> getPublicationKeysFromPublicationKey(String publicationKey) {
        if (StringUtils.isFull(publicationKey)) {
            Set<String> publicationKeySet = new HashSet<String>();
            String formKey = getFormKeyFromPublicationKey(publicationKey);
            if (StringUtils.isFull(formKey)) {
                List<FormEntity> formEntities = getFormsKeyFromFormKey(formKey);
                for (FormEntity formEntity : formEntities) {
                    publicationKeySet.add(formEntity.getPublicationKey());
                }
                return publicationKeySet;
            }
        }
        return null;
    }

    private String extractFormTitleFromJSON(String form) {
        if (StringUtils.isFull(form)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                Map<String, Object> map = mapper.readValue(form, Map.class);
                String title = (String) map.get("title");
//                System.out.println("title = " + title);
                return title;
            } catch (JsonParseException exception) {
            } catch (IOException e) {
            }
        }
        return form;
    }

    private InboxStatusTO constructInboxStatus(VInboxStatusEntity vInboxStatusEntity) {
        if (vInboxStatusEntity != null) {
            InboxStatusTO inboxStatusTO = new InboxStatusTO();
            inboxStatusTO.setId(vInboxStatusEntity.getId());
            inboxStatusTO.setCode(vInboxStatusEntity.getCode());
            inboxStatusTO.setDescription(vInboxStatusEntity.getDescription());
            inboxStatusTO.setMutable(vInboxStatusEntity.isMutable());
            return inboxStatusTO;
        }
        return null;
    }


    @Override
    public boolean sendMessage(String usernameSender, String usernameRecipient, CohortTO cohortTO, String message, String instanceId, String form, String subjectId, String subjectName) {
        if (StringUtils.isFull(usernameSender, usernameRecipient, message, instanceId) && cohortTO != null) {

            CohortEntity cohortEntity = getCohort(cohortTO.getId());

            if (cohortEntity != null) {
                UserEntity senderUserEntity = getUserFromUsername(usernameSender);
                UserEntity recipientUserEntity = getUserFromUsername(usernameRecipient);

                if (senderUserEntity != null && recipientUserEntity != null) {
                    InboxEntity inboxEntity = new InboxEntity();
                    inboxEntity.setRecipient(recipientUserEntity);
                    inboxEntity.setSender(senderUserEntity);
                    inboxEntity.setSubject(System.getProperty("edu.harvard.mgh.lcs.sprout.forms.study.form_email_subject", DEFAULT_FORM_EMAIL_SUBJECT));
                    inboxEntity.setBody(message);
                    inboxEntity.setInstanceId(instanceId);
                    inboxEntity.setForm(form);
                    inboxEntity.setStatus(getVInboxStatusEntity(InboxStatus.NEW));
                    inboxEntity.setSubjectId(subjectId);
                    inboxEntity.setSubjectName(subjectName);
                    inboxEntity.setCohort(cohortEntity);
                    inboxEntity.setActivityDate(new Date());
                    inboxEntity.setDeliveryDate(new Date());
                    entityManager.persist(inboxEntity);

                    publishToMailQueue(constructStudyInboxTO(inboxEntity, cohortTO));
                    return true;
                }
            }

        }
        return false;
    }

    @Override
    public List<Result> getRecentCohortMembers(String user, CohortTO cohortTO) {
        List<CohortMemberTO> cohortMemberTOList = new ArrayList<CohortMemberTO>();

//        System.out.println("StudyServiceImpl.getRecentCohortMembers");

        if (cohortTO != null) {
            StringBuilder queryText = new StringBuilder()
                    .append("SELECT TOP 5 MAX(id) AS id, subject_id AS subject_id FROM audit ")
                    .append(" WHERE users_id = (SELECT id FROM users WHERE username = :username) ")
                    .append(" AND   audit_type = (SELECT id FROM v_audit_type WHERE code = :auditTypeGetInbox) ")
                    .append(" AND   cohort_id = :cohortId ")
                    .append(" AND   subject_schema = :subjectSchema ")
                    .append(" GROUP BY subject_id ")
                    .append(" ORDER BY id DESC ");

            Query query = entityManager.createNativeQuery(queryText.toString(), RecentCohortMemberEntity.class);
            query.setParameter("username", user);
            query.setParameter("auditTypeGetInbox", AuditType.GET_INBOX.toString());
            query.setParameter("cohortId", cohortTO.getId());
            query.setParameter("subjectSchema", cohortTO.getCohortSubjectSchema());

            List<RecentCohortMemberEntity> recentCohortMemberEntities = query.getResultList();

            if (recentCohortMemberEntities != null && recentCohortMemberEntities.size() > 0) {
                StringBuilder mrnList = new StringBuilder();
                for (RecentCohortMemberEntity recentCohortMemberEntity : recentCohortMemberEntities) {
                    if (mrnList.length() > 0) mrnList.append("|");
                    mrnList.append(recentCohortMemberEntity.getSubjectId());
                }

                return getRemoteCohortSubjectsByList(cohortTO, mrnList.toString());
            }
        }

        return null;
    }

    @Override
    public List<CohortAuthTO> getCohortAuthorizations(CohortTO cohortTO) {
        List<CohortAuthTO> cohortAuthorizations = new ArrayList<CohortAuthTO>();

        CohortEntity cohortEntity = getCohort(cohortTO);

        if (cohortEntity != null) {
            List<CohortAuthEntity> cohortAuthEntities = cohortEntity.getCohortAuthorizations();
            if (cohortAuthEntities != null && cohortAuthEntities.size() > 0) {
                for (CohortAuthEntity cohortAuthEntity : cohortAuthEntities) {
                    CohortAuthTO cohortAuthTO = new CohortAuthTO();
                    cohortAuthTO.setId(cohortAuthEntity.getId());
                    cohortAuthTO.setActive(cohortAuthEntity.isActive());
                    cohortAuthTO.setManager(cohortAuthEntity.isManager());

                    UserEntity userEntity = cohortAuthEntity.getUser();
                    if (userEntity != null) cohortAuthTO.setUser(constructUserTOLite(userEntity));

                    cohortAuthorizations.add(cohortAuthTO);
                }
            }
        }
        return cohortAuthorizations;
    }

    private UserTO constructUserTO(UserEntity userEntity) {
        if (userEntity != null) {
            UserTO userTO = new UserTO();
            userTO.setId(userEntity.getId());
            userTO.setUsername(userEntity.getUsername());
            userTO.setPassword(userEntity.getPassword());
            userTO.setSalt(userEntity.getSalt() != null ? userEntity.getSalt() : 0);
            userTO.setFirstName(userEntity.getFirstName());
            userTO.setLastName(userEntity.getLastName());
            userTO.setActive(userEntity.getActive());
            userTO.setDomain(constructDomainTO(userEntity.getDomain()));
            userTO.setPreferences(constructUserPreferencesTO(userEntity.getPreferences()));
            return userTO;
        }
        return null;
    }

    private UserTO constructUserTOLite(UserEntity userEntity) {
        if (userEntity != null) {
            UserTO userTO = new UserTO();
            userTO.setUsername(userEntity.getUsername());
            userTO.setFirstName(userEntity.getFirstName());
            userTO.setLastName(userEntity.getLastName());
            userTO.setPreferences(constructUserPreferencesTOLite(userEntity.getPreferences()));
            return userTO;
        }
        return null;
    }

    private Set<UserPreferenceTO> constructUserPreferencesTO(Set<UsersPreferenceEntity> usersPreferenceEntities) {
        if (usersPreferenceEntities != null) {
            Set<UserPreferenceTO> userPreferenceTOSet = new HashSet<UserPreferenceTO>();
            for (UsersPreferenceEntity usersPreferenceEntity : usersPreferenceEntities) {
                UserPreferenceTO userPreferenceTO = new UserPreferenceTO();
                userPreferenceTO.setId(usersPreferenceEntity.getId());
                userPreferenceTO.setCode(usersPreferenceEntity.getUserPreference().getCode());
                userPreferenceTO.setDescription(usersPreferenceEntity.getUserPreference().getDescription());
                userPreferenceTO.setValue(usersPreferenceEntity.getValue());
                userPreferenceTO.setUserEditable(usersPreferenceEntity.getUserPreference().isUserEditable());
                userPreferenceTOSet.add(userPreferenceTO);
            }
            return userPreferenceTOSet;
        }
        return null;
    }
    private Set<UserPreferenceTO> constructUserPreferencesTOLite(Set<UsersPreferenceEntity> usersPreferenceEntities) {
        if (usersPreferenceEntities != null) {
            Set<UserPreferenceTO> userPreferenceTOSet = new HashSet<UserPreferenceTO>();
            for (UsersPreferenceEntity usersPreferenceEntity : usersPreferenceEntities) {
                if (usersPreferenceEntity.getUserPreference().getCode().equalsIgnoreCase("EMAIL_PRIMARY")) {
                    UserPreferenceTO userPreferenceTO = new UserPreferenceTO();
                    userPreferenceTO.setId(usersPreferenceEntity.getId());
                    userPreferenceTO.setCode(usersPreferenceEntity.getUserPreference().getCode());
                    userPreferenceTO.setDescription(usersPreferenceEntity.getUserPreference().getDescription());
                    userPreferenceTO.setValue(usersPreferenceEntity.getValue());
                    userPreferenceTO.setUserEditable(usersPreferenceEntity.getUserPreference().isUserEditable());
                    userPreferenceTOSet.add(userPreferenceTO);
                }
            }
            return userPreferenceTOSet;
        }
        return null;
    }

    private DomainTO constructDomainTO(DomainEntity domainEntity) {
        if (domainEntity != null) {
            DomainTO domainTO = new DomainTO();
            domainTO.setId(domainEntity.getId());
            domainTO.setName(domainEntity.getName());
            domainTO.setDescription(domainEntity.getDescription());
            domainTO.setAttributes(constructDomainAttributes(domainEntity.getDomainAttrs()));
            return domainTO;
        }
        return null;
    }

    private Set<DomainAttrTO> constructDomainAttributes(Set<DomainAttrEntity> domainAttributes) {
        if (domainAttributes != null) {
            Set<DomainAttrTO> domainAttrTOSet = new HashSet<DomainAttrTO>();
            for (DomainAttrEntity domainAttribute : domainAttributes) {
                DomainAttrTO domainAttrTO = new DomainAttrTO();
                domainAttrTO.setId(domainAttribute.getId());
                domainAttrTO.setCode(domainAttribute.getDomainAttr().getCode());
                domainAttrTO.setDescription(domainAttribute.getDomainAttr().getDescription());
                domainAttrTO.setValue(domainAttribute.getValue());
                domainAttrTOSet.add(domainAttrTO);
            }
            return domainAttrTOSet;
        }
        return null;
    }

    @Override
    public List<Result> getRemoteCohortSubjectsByList(CohortTO cohortTO, String idList) {
        GetMethod getMethod = null;
        try {
            String queryUrl = String.format(cohortTO.getCohortQueryURL() + "&mode=list", URLEncoder.encode(idList.toString(), "UTF-8"));

            HttpClient httpClient = new HttpClient();
            //				client.getState().setCredentials(
            //						new AuthScope(formsAuthHost, formsAuthPort, formsAuthRealm),
            //						new UsernamePasswordCredentials(orgAuthKey, psk)
            //						);


            getMethod = new GetMethod(queryUrl);
            int status = httpClient.executeMethod(getMethod);

            if (status == 200) {
                String response = getMethod.getResponseBodyAsString();
                if (!StringUtils.isEmpty(response)) {
                    return new ObjectMapper().readValue(response, new TypeReference<List<Result>>() {});
                }
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (getMethod != null) getMethod.releaseConnection();
        }
        return null;
    }

    @Override
    public BooleanTO deleteSubmission(SessionTO sessionTO, CohortTO cohortTO, String instanceId, Boolean demographicInd, String[] identityArray) {
        GetMethod getMethod = null;
        try {
            String queryUrl = String.format(cohortTO.getCohortQueryURL() + String.format("&mode=delete&uid=%s", sessionTO.getUser()), URLEncoder.encode(instanceId, "UTF-8"));

            HttpClient httpClient = new HttpClient();
            //				client.getState().setCredentials(
            //						new AuthScope(formsAuthHost, formsAuthPort, formsAuthRealm),
            //						new UsernamePasswordCredentials(orgAuthKey, psk)
            //						);


            getMethod = new GetMethod(queryUrl);
            int status = httpClient.executeMethod(getMethod);

            if (status == HttpServletResponse.SC_OK) {
                String response = getMethod.getResponseBodyAsString();
                if (!StringUtils.isEmpty(response)) {
                    List<Result> results = new ObjectMapper().readValue(response, new TypeReference<List<Result>>() {});
                    if (results != null && results.size() == 1) {
                        Result result = results.get(0);
                        if (result != null && result.getId().equalsIgnoreCase("true")) {

                            if (demographicInd != null && demographicInd) {

                                List<edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.IdentityTO> identities = parseIdentityArray(identityArray);

                                String cohortPrimaryIdentitySchema = getCohortPrimaryIdentitySchema(cohortTO);
                                String cohortPrimaryIdentityId = null;

                                if (cohortPrimaryIdentitySchema != null) {
                                    for (edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.IdentityTO identity : identities) {
                                        if (identity.getScheme().equalsIgnoreCase(cohortPrimaryIdentitySchema)) {
                                            cohortPrimaryIdentityId = identity.getId();
                                        }
                                    }
                                }

                                auditService.log(findUserEntity(sessionTO).getUsername(), AuditType.DELETE_INBOX, SproutStudyConstantService.AuditVerbosity.INFO, "Deleting User Inbox", cohortTO, cohortPrimaryIdentitySchema, cohortPrimaryIdentityId, String.format("Retrieving subject, %s, inbox.", getIdentityArrayAsString(identityArray)));


                            }

                            return new BooleanTO(true);
                        } else if (result != null) {
                            return new BooleanTO(false, result.getFullName());
                        }
                        return new BooleanTO(false);
                    } else {
                        return new BooleanTO(false);
                    }
                }
            } else if (status == HttpServletResponse.SC_INTERNAL_SERVER_ERROR) {
                Header sproutExceptionDetailsHeader = getMethod.getResponseHeader("sproutExceptionDetails");
                if (sproutExceptionDetailsHeader != null) {
                    return new BooleanTO(false, sproutExceptionDetailsHeader.getValue());
                }
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (getMethod != null) getMethod.releaseConnection();
        }
        return new BooleanTO(false);
    }

    @Override
    public CohortTO getCohortTO(Integer cohortId) {
        if (cohortId != null && cohortId > 0) {
            CohortEntity cohortEntity = entityManager.find(CohortEntity.class, cohortId);
            if (cohortEntity != null) return constructCohortTO(cohortEntity);
        }
        return null;
    }

    @Override
    public CohortTO getCohortTO(SessionTO sessionTO, String cohortKey) {
        if (StringUtils.isFull(cohortKey)) {
            try {
                Query query = entityManager.createNamedQuery(CohortEntity.BY_COHORT_KEY_AND_AUTH);
                query.setParameter("key", cohortKey);
                query.setParameter("user", findUserEntity(sessionTO));
                CohortEntity cohortEntity = (CohortEntity) query.getSingleResult();
                if (cohortEntity != null) return constructCohortTO(cohortEntity);
            } catch (NoResultException e) {}
        }
        return null;
    }

    @Override
    public void persistUserPreference(String username, String key, String value) {
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(key) && value != null) {
            UserEntity userEntity = getUserFromUsername(username);
            if (userEntity != null) {
                UsersPreferenceEntity usersPreferenceEntity = getUserPreference(userEntity, key);
                if (usersPreferenceEntity != null) {
                    usersPreferenceEntity.setValue(value);
                    usersPreferenceEntity.setActivityDate(new Date());
                    entityManager.merge(usersPreferenceEntity);
                } else {
                    VUserPreferenceEntity vUserPreferenceEntity = getVUserPreferenceEntity(key);
                    if (vUserPreferenceEntity != null) {
                        usersPreferenceEntity = new UsersPreferenceEntity();
                        usersPreferenceEntity.setUserPreference(vUserPreferenceEntity);
                        usersPreferenceEntity.setUser(userEntity);
                        usersPreferenceEntity.setValue(value);
                        usersPreferenceEntity.setActivityDate(new Date());
                        entityManager.persist(usersPreferenceEntity);
                    }
                }
            }
        }
    }

    @Override
    public CohortTO getLastSelectedCohort(String username) {
        if (!StringUtils.isEmpty(username)) {
            UserEntity userEntity = getUserFromUsername(username);
            if (userEntity != null) {
                UsersPreferenceEntity usersPreferenceEntity = getUserPreference(userEntity, StudyService.USER_PREFERENCE_COHORT_ID);
                if (usersPreferenceEntity != null) {
                    String cohortId = usersPreferenceEntity.getValue();
                    if (cohortId != null && StringUtils.isInteger(cohortId)) {
                        return getCohortTO(Integer.parseInt(cohortId));
                    }
                }
            }
        }
        return null;
    }

    private VUserPreferenceEntity getVUserPreferenceEntity(String key) {
        try {
            Query query = entityManager.createNamedQuery(VUserPreferenceEntity.FIND_BY_CODE);
            query.setParameter("code", key);
            return (VUserPreferenceEntity) query.getSingleResult();
        } catch (NoResultException e) {}
        return null;
    }

    private VInboxStatusEntity getVInboxStatusEntity(InboxStatus inboxStatus) {
        try {
            Query query = entityManager.createNamedQuery(VInboxStatusEntity.BY_CODE);
            query.setParameter("code", inboxStatus.toString());
            return (VInboxStatusEntity) query.getSingleResult();
        } catch (NoResultException e) {}
        return null;
    }

    private UsersPreferenceEntity getUserPreference(UserEntity userEntity, String key) {
        Set<UsersPreferenceEntity> usersPreferenceEntities = userEntity.getPreferences();
        if (usersPreferenceEntities != null) {
            for (UsersPreferenceEntity usersPreferenceEntity : usersPreferenceEntities) {
                if (usersPreferenceEntity.getUserPreference().getCode().equalsIgnoreCase(key)) {
                    return usersPreferenceEntity;
                }
            }
        }
        return null;
    }

    private UserEntity getUserFromUsername(String username) {
        try {
            Query query = entityManager.createNamedQuery(UserEntity.BY_USERNAME);
            query.setParameter("username", username);
            return (UserEntity) query.getSingleResult();
        } catch (NoResultException e) {}
        return null;
    }

    private List<CohortAttrTO> getCohortAttributes(CohortEntity cohortEntity) {
        List<CohortAttrTO> cohortAttrTOList = new ArrayList<CohortAttrTO>();
        if (cohortEntity != null && cohortEntity.getCohortAttributes() != null) {
            for (CohortAttrEntity cohortAttrEntity : cohortEntity.getCohortAttributes()) {
                CohortAttrTO cohortAttrTO = new CohortAttrTO();
                cohortAttrTO.setName(cohortAttrEntity.getCohortAttr().getCode());
                cohortAttrTO.setDescription(cohortAttrEntity.getCohortAttr().getDescription());
                cohortAttrTO.setActivityDate(cohortAttrEntity.getActivityDate());
                cohortAttrTO.setValue(cohortAttrEntity.getValue());
                cohortAttrTOList.add(cohortAttrTO);
            }
        }
        return cohortAttrTOList;
    }

    private List<CohortFormTO> getCohortForms(CohortEntity cohortEntity) {
        List<CohortFormTO> cohortFormTOList = new ArrayList<CohortFormTO>();
        if (cohortEntity != null && cohortEntity.getCohortForms() != null) {
            for (CohortFormEntity cohortFormEntity : cohortEntity.getCohortForms()) {
                CohortFormTO cohortFormTO = new CohortFormTO();
                cohortFormTO.setId(cohortFormEntity.getForm().getId() + "");
                cohortFormTO.setName(cohortFormEntity.getForm().getName());
                cohortFormTO.setPublicationKey(cohortFormEntity.getForm().getPublicationKey());
                cohortFormTO.setFormKey(cohortFormEntity.getForm().getFormKey());
                cohortFormTO.setDemographic(cohortFormEntity.getForm().getDemographic());
                cohortFormTO.setActive(cohortFormEntity.getForm().getActive());
                cohortFormTO.setActivityDate(cohortFormEntity.getForm().getActivityDate());

                if (cohortFormEntity.getForm().getFormAttributes() != null) {
                    for (FormAttrEntity formAttrEntity : cohortFormEntity.getForm().getFormAttributes()) {
                        if (formAttrEntity.getFormAttr().getCode().equalsIgnoreCase(FormAttr.UNIQUE.toString())) {
                            cohortFormTO.setUnique(true);
                            break;
                        }
                    }
                }
                cohortFormTOList.add(cohortFormTO);
            }
            Collections.sort(cohortFormTOList);
        }
        return cohortFormTOList;
    }

    @Override
    public CohortEntity getCohort(CohortTO cohortTO) {
        if (cohortTO != null) {
            return entityManager.find(CohortEntity.class, cohortTO.getId());
        }
        return null;
    }

    @Override
    public CohortEntity getCohort(int cohortId) {
        return entityManager.find(CohortEntity.class, cohortId);
    }

    private void publishToMailQueue(StudyInboxTO studyInboxTO) {

        auditService.log(studyInboxTO.getSender().getUsername(), AuditType.PUBLISH_FORM_EMAIL_QUEUE, SproutStudyConstantService.AuditVerbosity.INFO, "Publishing Form Email to Queue", studyInboxTO.getCohortTO(), studyInboxTO.getCohortTO().getCohortSubjectSchema(), studyInboxTO.getSubjectId(), String.format("Publishing Form Email for instance, %s, from %s (%s) to %s (%s), to queue.", studyInboxTO.getInstanceId(), studyInboxTO.getSender().getFullName(), studyInboxTO.getSender().getUsername(), studyInboxTO.getRecipient().getFullName(), studyInboxTO.getRecipient().getUsername()));

        String destinationName = "queue/sproutStudyEmailQueue";
        Context ic = null;
        ConnectionFactory cf = null;
        Connection connection = null;

        try {
            ic = new InitialContext();

            cf = (ConnectionFactory) ic.lookup("/ConnectionFactory");
            javax.jms.Queue queue = (javax.jms.Queue) ic.lookup(destinationName);

            connection = cf.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer publisher = session.createProducer(queue);

            connection.start();

            ObjectMessage message = session.createObjectMessage(studyInboxTO);
            publisher.send(message);
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<CohortTO> getAuthorizedCohorts(SessionTO sessionTO) {
        try {
            UserEntity userEntity = findUserEntity(sessionTO);

            if (userEntity != null) {
                List<CohortAuthEntity> cohortAuthEntities = userEntity.getCohortAuthorizations();
                if (cohortAuthEntities != null) {
                    List<CohortTO> cohortTOList = new ArrayList<CohortTO>();

                    boolean adminInd = isAdmin(sessionTO);

                    for (CohortAuthEntity cohortAuthEntity : cohortAuthEntities) {
                        if (adminInd || cohortAuthEntity.isManager()) {
                            if (cohortAuthEntity.getCohort().isActive()) {
                                CohortTO cohortTO = new CohortTO();
                                cohortTO.setName(cohortAuthEntity.getCohort().getName());
                                cohortTO.setDescription(cohortAuthEntity.getCohort().getDescription());
                                cohortTO.setCohortKey(cohortAuthEntity.getCohort().getCohortKey());
                                cohortTO.setActive(cohortAuthEntity.getCohort().isActive());
                                cohortTO.setActivityDate(cohortAuthEntity.getCohort().getActivityDate());
                                cohortTOList.add(cohortTO);
                            }
                        }
                    }
                    return cohortTOList;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public BooleanTO saveCohort(SessionTO sessionTO, String cohortKey, String name, String description, String group) throws UnauthorizedActionException {
        if (sessionTO != null && StringUtils.isFull(name)) {
            if (StringUtils.isFull(cohortKey) && !cohortKey.equalsIgnoreCase("undefined")) {
                CohortEntity cohortEntity = getAuthorizedCohortByKey(sessionTO, cohortKey);
                if (cohortEntity != null) {
                    if (isAdmin(sessionTO) || isManager(sessionTO, cohortEntity)) {
                        cohortEntity.setDescription(description);
                        cohortEntity.setActivityDate(new Date());
                        entityManager.merge(cohortEntity);
                        auditService.log(findUserEntity(sessionTO), AuditType.ADMIN_EDIT_COHORT, SproutStudyConstantService.AuditVerbosity.INFO, cohortEntity, "Edit Cohort", String.format("User (%s) edited cohort \"%s\" (%s).", sessionTO.getUser(), cohortEntity.getName(), cohortEntity.getCohortKey()));
                        return new BooleanTO(true);
                    } else {
                        throw new UnauthorizedActionException("You are not authorized to save this cohort.");
                    }
                } else {
                    return new BooleanTO(false, "Failed to save cohort changes.");
                }
            } else {
                if (isAdmin(sessionTO)) {
                    List<CohortEntity> cohortEntities = findCohortEntities(name);
                    try {
                        if (cohortEntities != null && cohortEntities.size() > 0) throw new DuplicateCohortNameException(name);
                    } catch (DuplicateCohortNameException e) {
                        return new BooleanTO(false, e.getMessage());
                    }
                    CohortEntity cohortEntity = new CohortEntity();
                    cohortEntity.setName(name);
                    cohortEntity.setDescription(description);
                    cohortEntity.setCohortKey(StringUtils.getGuid());
                    cohortEntity.setActive(true);
                    cohortEntity.setActivityDate(new Date());
                    entityManager.persist(cohortEntity);
                    auditService.log(findUserEntity(sessionTO), AuditType.ADMIN_NEW_COHORT, SproutStudyConstantService.AuditVerbosity.INFO, cohortEntity, "Add New Cohort", String.format("User (%s) created cohort \"%s\" (%s).", sessionTO.getUser(), cohortEntity.getName(), cohortEntity.getCohortKey()));
                    if (saveAuthorization(sessionTO.getUser(), sessionTO.getUser(), cohortEntity).isTrue()) {
                        return new BooleanTO(true);
                    }
                } else {
                    throw new UnauthorizedActionException("You are not authorized to save this cohort.");
                }
            }
        }
        return new BooleanTO(false);
    }

    @Override
    public BooleanTO saveAuthorization(SessionTO sessionTO, String usernane, String cohortKey) {
        CohortEntity cohortEntity = getAuthorizedCohortByKey(sessionTO, cohortKey);
        if (cohortEntity != null) {
            return saveAuthorization(sessionTO.getUser(), usernane, cohortEntity);
        }
        return new BooleanTO(false);
    }

    private BooleanTO saveAuthorization(String authorizedBy, String username, CohortEntity cohortEntity) {
        if (cohortEntity != null && StringUtils.isFull(username, authorizedBy)) {
            UserEntity userEntityAuthorizedBy = findUserEntity(authorizedBy);
            UserEntity userEntityAuthorizedTo = findUserEntity(username);
            if (userEntityAuthorizedBy != null && userEntityAuthorizedTo != null) {
                auditService.log(userEntityAuthorizedBy, AuditType.ADMIN_AUTHORIZE_COHORT, SproutStudyConstantService.AuditVerbosity.INFO, cohortEntity, "Authorize Cohort", String.format("User (%s) is granting access to user (%s) to cohort \"%s\" (%s).", authorizedBy, username, cohortEntity.getName(), cohortEntity.getCohortKey()));
                if (!isAuthorized(userEntityAuthorizedTo, cohortEntity)) {
                    CohortAuthEntity cohortAuthEntity = new CohortAuthEntity();
                    cohortAuthEntity.setCohort(cohortEntity);
                    cohortAuthEntity.setUser(userEntityAuthorizedTo);
                    cohortAuthEntity.setActive(true);
                    cohortAuthEntity.setManager(authorizedBy.equalsIgnoreCase(username));
                    cohortAuthEntity.setActivityDate(new Date());
                    entityManager.persist(cohortAuthEntity);
                    return new BooleanTO(true);
                } else {
                    return new BooleanTO(true);
                }
            }
        }
        return new BooleanTO(false);
    }

    private boolean isAuthorized(UserEntity userEntityAuthorizedTo, CohortEntity cohortEntity) {
        return getAuthorizedCohortByKey(userEntityAuthorizedTo.getUsername(), cohortEntity.getCohortKey()) != null;
    }

    private CohortEntity getAuthorizedCohortByKey(SessionTO sessionTO, String cohortKey) {
        if (sessionTO != null && StringUtils.isFull(cohortKey, sessionTO.getUser())) {
            return getAuthorizedCohortByKey(sessionTO.getUser(), cohortKey);
        }
        return null;
    }

    private CohortEntity getAuthorizedCohortByKey(String username, String cohortKey) {
        if (StringUtils.isFull(cohortKey, username)) {
            try {
                Query query = entityManager.createNamedQuery(CohortAuthEntity.BY_USERNAME_AND_COHORT_KEY);
                query.setParameter("username", username);
                query.setParameter("cohortKey", cohortKey);
                CohortAuthEntity cohortAuthEntity = (CohortAuthEntity) query.getSingleResult();
                if (cohortAuthEntity != null) return cohortAuthEntity.getCohort();
            } catch (NoResultException e) {}
        }
        return null;
    }

    private CohortEntity findCohortEntity(SessionTO sessionTO) {
        if (sessionTO != null && sessionTO.getCohortTO() != null) {
            return findCohortEntity(sessionTO.getCohortTO().getName());
        }
        return null;
    }

    private CohortEntity findCohortEntity(String cohort) {
        try {
            Query query = entityManager.createNamedQuery(CohortEntity.BY_COHORT_NAME);
            query.setParameter("name", cohort);
            return (CohortEntity) query.getSingleResult();
        } catch (NoResultException e) {}
        return null;
    }

    private List<CohortEntity> findCohortEntities(String cohortName) {
        try {
            Query query = entityManager.createNamedQuery(CohortEntity.BY_COHORT_NAME);
            query.setParameter("name", cohortName);
            return query.getResultList();
        } catch (NoResultException e) {}
        return null;
    }


    @Override
    public BooleanTO deleteCohort(SessionTO sessionTO, String cohortKey) {
        CohortEntity cohortEntity = getAuthorizedCohortByKey(sessionTO, cohortKey);
        if (cohortEntity != null) {
            if (isAdmin(sessionTO)) {
                cohortEntity.setActive(false);
                cohortEntity.setActivityDate(new Date());
                entityManager.merge(cohortEntity);
                return new BooleanTO(true);
            } else {
                try {
                    throw new UnauthorizedActionException("You are not authorized to delete this cohort.");
                } catch (UnauthorizedActionException e) {
                    return new BooleanTO(false, e.getMessage());
                }
            }
        }
        return new BooleanTO(false);
    }

    @Override
    public boolean isAdmin(SessionTO sessionTO) {
        UserEntity userEntity = findUserEntity(sessionTO);
        return (userEntity != null && userEntity.isAdmin());
    }

    @Override
    public boolean isManager(SessionTO sessionTO) {
        UserEntity userEntity = findUserEntity(sessionTO);
        if (userEntity != null && userEntity.getCohortAuthorizations() != null) {
            for (CohortAuthEntity cohortAuthEntity : userEntity.getCohortAuthorizations()) {
                if (cohortAuthEntity.isManager()) return true;
            }
        }
        return false;
    }

    private boolean isManager(SessionTO sessionTO, CohortEntity cohortEntity) {
        if (cohortEntity != null) {
            UserEntity userEntity = findUserEntity(sessionTO);
            if (userEntity != null && userEntity.getCohortAuthorizations() != null) {
                for (CohortAuthEntity cohortAuthEntity : userEntity.getCohortAuthorizations()) {
                    if (cohortAuthEntity.isManager() && cohortAuthEntity.getCohort().getId() == cohortEntity.getId()) return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<CohortAuthorizationTO> getCohortAuthorizations(SessionTO sessionTO, String cohortKey) {
        if (sessionTO != null && StringUtils.isFull(cohortKey) && !cohortKey.equalsIgnoreCase("undefined")) {
            CohortEntity cohortEntity = null;
            if (isAdmin(sessionTO)) {
                cohortEntity = getAuthorizedCohortByKey(sessionTO, cohortKey);
            } else {
                cohortEntity = getManagedCohortByKey(sessionTO, cohortKey);
            }
            if (cohortEntity != null) {
                List<CohortAuthEntity> cohortAuthEntities = cohortEntity.getCohortAuthorizations();
                if (cohortAuthEntities != null) {
                    List<CohortAuthorizationTO> cohortAuthorizationTOList = new ArrayList<CohortAuthorizationTO>();
                    for (CohortAuthEntity cohortAuthEntity : cohortAuthEntities) {
                        CohortAuthorizationTO cohortAuthorizationTO = new CohortAuthorizationTO();
                        cohortAuthorizationTO.setUser(generateUserTO(cohortAuthEntity.getUser()));
                        cohortAuthorizationTO.setActivityDate(cohortAuthEntity.getActivityDate());
                        cohortAuthorizationTO.setManager(cohortAuthEntity.isManager());
                        cohortAuthorizationTOList.add(cohortAuthorizationTO);
                    }
                    return cohortAuthorizationTOList;
                }
            }
        }
        return null;
    }

    private UserTO generateUserTO(UserEntity userEntity) {
        if (userEntity != null) {
            UserTO userTO = new UserTO();
            userTO.setUsername(userEntity.getUsername());
            userTO.setFirstName(userEntity.getFirstName());
            userTO.setLastName(userEntity.getLastName());
            userTO.setId(userEntity.getId());

            if (userEntity.getPreferences() != null) {
                for (UsersPreferenceEntity usersPreferenceEntity : userEntity.getPreferences()) {
                    if (usersPreferenceEntity.getUserPreference().getCode().equalsIgnoreCase("EMAIL_PRIMARY")) {
                        userTO.setEmail(usersPreferenceEntity.getValue());
                        break;
                    }
                }
            }
            return userTO;
        }
        return null;
    }

    @Override
    public BooleanTO grantCohortAuthorization(SessionTO sessionTO, String cohortKey, String firstName, String lastName, String username, String email, Boolean manager) throws UnauthorizedActionException {
        {
            if (sessionTO != null && StringUtils.isFull(cohortKey, firstName, lastName, username, email)) {
                if (StringUtils.isFull(cohortKey) && !cohortKey.equalsIgnoreCase("undefined")) {
                    CohortEntity cohortEntity = null;
                    if (isAdmin(sessionTO)) {
                        cohortEntity = getAuthorizedCohortByKey(sessionTO, cohortKey);
                    } else {
                        cohortEntity = getManagedCohortByKey(sessionTO, cohortKey);
                    }
                    if (cohortEntity != null) {
                        if (isAdmin(sessionTO) || isManager(sessionTO, cohortEntity)) {
                            UserEntity userEntity = findUserEntity(username);
                            if (userEntity != null) {
                                CohortAuthEntity cohortAuthEntity = getCohortAuthorizationByKey(username, cohortKey);
                                if (cohortAuthEntity == null) {
                                    cohortAuthEntity = new CohortAuthEntity();
                                    cohortAuthEntity.setUser(userEntity);
                                    cohortAuthEntity.setCohort(cohortEntity);
                                    cohortAuthEntity.setManager(manager != null && manager == true);
                                    cohortAuthEntity.setActive(true);
                                    cohortAuthEntity.setActivityDate(new Date());
                                    entityManager.persist(cohortAuthEntity);
                                } else {
                                    cohortAuthEntity.setManager(manager != null && manager == true);
                                    cohortAuthEntity.setActivityDate(new Date());
                                    entityManager.merge(cohortAuthEntity);
                                }
                                return new BooleanTO(true);
                            } else {
                                userEntity = new UserEntity();
                                userEntity.setFirstName(firstName);
                                userEntity.setLastName(lastName);
                                userEntity.setUsername(username);
                                userEntity.setDomain(getDomainByName(SproutStudyConstantService.TEMP_DOMAIN_NAME));
                                userEntity.setActive(true);
                                userEntity.setActivityDate(new Date());
                                entityManager.persist(userEntity);

                                UsersPreferenceEntity usersPreferenceEntity = new UsersPreferenceEntity();
                                usersPreferenceEntity.setUser(userEntity);
                                usersPreferenceEntity.setUserPreference(getVUserPreferenceByKey("EMAIL_PRIMARY"));
                                usersPreferenceEntity.setValue(email);
                                usersPreferenceEntity.setActivityDate(new Date());
                                entityManager.persist(usersPreferenceEntity);

                                CohortAuthEntity cohortAuthEntity = new CohortAuthEntity();
                                cohortAuthEntity.setUser(userEntity);
                                cohortAuthEntity.setCohort(cohortEntity);
                                cohortAuthEntity.setManager(manager != null && manager == true);
                                cohortAuthEntity.setActive(true);
                                cohortAuthEntity.setActivityDate(new Date());
                                entityManager.persist(cohortAuthEntity);

                                return new BooleanTO(true);
                            }
                        } else {
                            throw new UnauthorizedActionException("You are not authorized to grant this cohort authorization.");
                        }
                    }
                }
            }
            return new BooleanTO(false, "Failed to save user authorization.");
        }
    }

    private VUserPreferenceEntity getVUserPreferenceByKey(String key) {
        if (StringUtils.isFull(key)) {
            try {
                Query query = entityManager.createNamedQuery(VUserPreferenceEntity.FIND_BY_CODE);
                query.setParameter("code", key);
                return (VUserPreferenceEntity) query.getSingleResult();
            } catch (NoResultException e) {}
        }
        return null;
    }

    private DomainEntity getDomainByName(String domainName) {
        if (StringUtils.isFull(domainName)) {
            try {
                Query query = entityManager.createNamedQuery(DomainEntity.BY_NAME);
                query.setParameter("name", domainName);
                return  (DomainEntity) query.getSingleResult();
            } catch (NoResultException e) {}
        }
        return null;
    }

    @Override
    public BooleanTO revokeCohortAuthorization(SessionTO sessionTO, String cohortKey, String username) throws UnauthorizedActionException {
        if (sessionTO != null && StringUtils.isFull(cohortKey, username)) {
            if (StringUtils.isFull(cohortKey) && !cohortKey.equalsIgnoreCase("undefined")) {
                CohortEntity cohortEntity = null;
                if (isAdmin(sessionTO)) {
                    cohortEntity = getAuthorizedCohortByKey(sessionTO, cohortKey);
                } else {
                    cohortEntity = getManagedCohortByKey(sessionTO, cohortKey);
                }
                if (cohortEntity != null) {
                    if (isAdmin(sessionTO) || isManager(sessionTO, cohortEntity)) {
                        UserEntity userEntity = findUserEntity(username);
                        if (userEntity != null) {
                            CohortAuthEntity cohortAuthEntity = getCohortAuthorizationByKey(username, cohortKey);
                            if (cohortAuthEntity != null) {
                                entityManager.remove(cohortAuthEntity);
                                return new BooleanTO(true);
                            }

                        }
                    } else {
                        throw new UnauthorizedActionException("You are not authorized to revoke this cohort authorization.");
                    }
                }
            }
        }
        return new BooleanTO(false, "Failed to revoke cohort authorization.");
    }

    @Override
    public BooleanTO updateCohortAuthorization(SessionTO sessionTO, String cohortKey, String username, Boolean manager) throws UnauthorizedActionException {
        if (sessionTO != null && StringUtils.isFull(cohortKey, username)) {
            if (StringUtils.isFull(cohortKey) && !cohortKey.equalsIgnoreCase("undefined")) {
                CohortEntity cohortEntity = null;
                if (isAdmin(sessionTO)) {
                    cohortEntity = getAuthorizedCohortByKey(sessionTO, cohortKey);
                } else {
                    cohortEntity = getManagedCohortByKey(sessionTO, cohortKey);
                }
                if (cohortEntity != null) {
                    if (isAdmin(sessionTO) || isManager(sessionTO, cohortEntity)) {
                        UserEntity userEntity = findUserEntity(username);
                        if (userEntity != null) {
                            CohortAuthEntity cohortAuthEntity = getCohortAuthorizationByKey(username, cohortKey);
                            if (cohortAuthEntity != null) {
                                cohortAuthEntity.setManager(manager);
                                cohortAuthEntity.setActivityDate(new Date());
                                entityManager.merge(cohortAuthEntity);
                                return new BooleanTO(true);
                            }

                        }
                    } else {
                        throw new UnauthorizedActionException("You are not authorized to modify this cohort authorization.");
                    }
                }
            }
        }
        return new BooleanTO(false, "Failed to revoke cohort authorization.");
    }

    @Override
    public BooleanTO saveForm(SessionTO sessionTO, String cohortKey, String name, String formKey, String publicationKey, Boolean demographicInd) throws UnauthorizedActionException {
        if (sessionTO != null && StringUtils.isFull(cohortKey, name, formKey, publicationKey)) {
            if (StringUtils.isFull(cohortKey) && !cohortKey.equalsIgnoreCase("undefined")) {
                CohortEntity cohortEntity = null;
                if (isAdmin(sessionTO)) {
                    cohortEntity = getAuthorizedCohortByKey(sessionTO, cohortKey);
                } else {
                    cohortEntity = getManagedCohortByKey(sessionTO, cohortKey);
                }
                if (cohortEntity != null) {
                    if (isAdmin(sessionTO) || isManager(sessionTO, cohortEntity)) {
                        FormEntity formEntity = getFormByFormOrPublicationKey(formKey, publicationKey);
                        if (formEntity != null) {
                            formEntity.setName(name);
                            formEntity.setActivityDate(new Date());
                        } else {
                            formEntity = new FormEntity();
                            formEntity.setName(name);
                            formEntity.setFormKey(formKey);
                            formEntity.setPublicationKey(publicationKey);
                            formEntity.setActive(true);
                            formEntity.setDemographic(demographicInd);
                            formEntity.setActivityDate(new Date());
                            entityManager.persist(formEntity);

                            CohortFormEntity cohortFormEntity = new CohortFormEntity();
                            cohortFormEntity.setForm(formEntity);
                            cohortFormEntity.setCohort(cohortEntity);
                            cohortFormEntity.setActivityDate(new Date());
                            entityManager.persist(cohortFormEntity);
                        }
                        return new BooleanTO(true);
                    } else {
                        throw new UnauthorizedActionException("You are not authorized to create this form.");
                    }
                }
            }
        }
        return new BooleanTO(false, "Failed to create form.");
    }

    @Override
    public BooleanTO deleteForm(SessionTO sessionTO, String cohortKey, String formKey) throws UnauthorizedActionException {
        if (sessionTO != null && StringUtils.isFull(cohortKey, formKey)) {
            if (StringUtils.isFull(cohortKey) && !cohortKey.equalsIgnoreCase("undefined")) {
                CohortEntity cohortEntity = null;
                if (isAdmin(sessionTO)) {
                    cohortEntity = getAuthorizedCohortByKey(sessionTO, cohortKey);
                } else {
                    cohortEntity = getManagedCohortByKey(sessionTO, cohortKey);
                }
                if (cohortEntity != null) {
                    if (isAdmin(sessionTO) || isManager(sessionTO, cohortEntity)) {
                        List<FormEntity> formEntities = getFormsByFormKey(formKey);
                        if (formEntities != null) {
                            for (FormEntity formEntity : formEntities) {
                                if (formEntity.getCohortForms() != null) {
                                    for (CohortFormEntity cohortFormEntity : formEntity.getCohortForms()) {
                                        entityManager.remove(cohortFormEntity);
                                    }
                                }
                                if (formEntity.getFormAttributes() != null) {
                                    for (FormAttrEntity formAttrEntity : formEntity.getFormAttributes()) {
                                        entityManager.remove(formAttrEntity);
                                    }
                                }
                                entityManager.remove(formEntity);
                            }
                            return new BooleanTO(true);
                        }
                    } else {
                        throw new UnauthorizedActionException("You are not authorized to delete this form.");
                    }
                }
            }
        }
        return new BooleanTO(false, "Failed to delete form.");
    }

    private FormEntity getFormByFormOrPublicationKey(String formKey, String publicationKey) {
        try {
            Query query = entityManager.createNamedQuery(FormEntity.FIND_BY_FORM_OR_PUBLICATION_KEY);
            query.setParameter("formKey", formKey);
            query.setParameter("publicationKey", publicationKey);
            return (FormEntity) query.getSingleResult();
        } catch (NoResultException e) {}
        catch (Exception e) {}
        return null;
    }

    private List<FormEntity> getFormsByFormKey(String formKey) {
        Query query = entityManager.createNamedQuery(FormEntity.FIND_BY_FORM_KEY);
        query.setParameter("formKey", formKey);
        return query.getResultList();
    }

    private CohortAuthEntity getCohortAuthorizationByKey(String username, String cohortKey) {
        if (StringUtils.isFull(cohortKey, username)) {
            try {
                Query query = entityManager.createNamedQuery(CohortAuthEntity.BY_USERNAME_AND_COHORT_KEY);
                query.setParameter("username", username);
                query.setParameter("cohortKey", cohortKey);
                return (CohortAuthEntity) query.getSingleResult();
            } catch (NoResultException e) {}
        }
        return null;

    }

    private CohortEntity getManagedCohortByKey(SessionTO sessionTO, String cohortKey) {
        if (sessionTO != null && StringUtils.isFull(cohortKey, sessionTO.getUser())) {
            return getManagedCohortByKey(sessionTO.getUser(), cohortKey);
        }
        return null;
    }

    private CohortEntity getManagedCohortByKey(String username, String cohortKey) {
        if (StringUtils.isFull(cohortKey, username)) {
            try {
                Query query = entityManager.createNamedQuery(CohortAuthEntity.MANAGER_BY_USERNAME_AND_COHORT_KEY);
                query.setParameter("username", username);
                query.setParameter("cohortKey", cohortKey);
                CohortAuthEntity cohortAuthEntity = (CohortAuthEntity) query.getSingleResult();
                if (cohortAuthEntity != null) return cohortAuthEntity.getCohort();
            } catch (NoResultException e) {}
        }
        return null;
    }


    private UserEntity findUserEntity(SessionTO sessionTO) {
        if (sessionTO != null && sessionTO.getUser() != null) {
            return findUserEntity(sessionTO.getUser());
        }
        return null;
    }

    private UserEntity findUserEntity(String username) {
        try {
            Query query = entityManager.createNamedQuery(UserEntity.BY_USERNAME);
            query.setParameter("username", username);
            return (UserEntity) query.getSingleResult();
        } catch (NoResultException e) {}
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

    private List<edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.IdentityTO> parseIdentityArray(String[] identityArray) {
        List<edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.IdentityTO> identities = new ArrayList<edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.IdentityTO>();

        if (identityArray != null && identityArray.length > 0) {
            for (String identity : identityArray) {
                if (identity.indexOf("@") > -1) {
                    String[] identityParts = identity.split("@");
                    if (identityParts.length == 2) {
                        edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.IdentityTO identityTO = new edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.IdentityTO();
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

    private String getIdentityArrayAsString(String[] identityArray) {
        StringBuilder stringBuilder = new StringBuilder();
        if (identityArray != null && identityArray.length > 0) {
            List<edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.IdentityTO> identities = parseIdentityArray(identityArray);
            for (edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.IdentityTO identity : identities) {
                if (stringBuilder.length() > 0) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append(String.format("%s@%s", identity.getId(), identity.getScheme()));
            }
        }


        return null;
    }

}
