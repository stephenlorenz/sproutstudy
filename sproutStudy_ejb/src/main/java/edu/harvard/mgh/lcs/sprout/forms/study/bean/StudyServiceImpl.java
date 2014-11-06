package edu.harvard.mgh.lcs.sprout.forms.study.bean;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.InboxTO;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.AuditService;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.SproutStudyConstantService;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.StudyService;
import edu.harvard.mgh.lcs.sprout.forms.study.beanws.Result;
import edu.harvard.mgh.lcs.sprout.forms.study.to.*;
import edu.harvard.mgh.lcs.sprout.forms.study.util.StringUtils;
import edu.harvard.mgh.lcs.sprout.study.model.study.*;
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
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.Queue;

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
                Set<CohortAuthEntity> cohortAuthEntitySet = userEntity.getCohortAuthorizations();
                if (cohortAuthEntitySet != null && cohortAuthEntitySet.size() > 0) {
                    for (CohortAuthEntity cohortAuthEntity : cohortAuthEntitySet) {
                        CohortEntity cohortEntity = cohortAuthEntity.getCohort();
                        CohortTO cohortTO = new CohortTO();
                        cohortTO.setId(cohortEntity.getId());
                        cohortTO.setName(cohortEntity.getName());
                        cohortTO.setDescription(cohortEntity.getDescription());
                        cohortTO.setActivityDate(cohortEntity.getActivityDate());

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
        if (StringUtils.isFull(id, publicationKey) && StringUtils.isInteger(id)) {
            FormEntity formEntity = entityManager.find(FormEntity.class, Integer.parseInt(id));
            if (formEntity != null) {
                formEntity.setPublicationKey(publicationKey);
                formEntity.setActivityDate(new Date());
                entityManager.merge(formEntity);
                return new BooleanTO(true);
            }
        }
        return new BooleanTO(false);
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
            Set<CohortAuthEntity> cohortAuthEntities = cohortEntity.getCohortAuthorizations();
            if (cohortAuthEntities != null && cohortAuthEntities.size() > 0) {
                for (CohortAuthEntity cohortAuthEntity : cohortAuthEntities) {
                    CohortAuthTO cohortAuthTO = new CohortAuthTO();
                    cohortAuthTO.setId(cohortAuthEntity.getId());
                    cohortAuthTO.setActive(cohortAuthEntity.getActive());
                    cohortAuthTO.setManager(cohortAuthEntity.getManager());

                    UserEntity userEntity = cohortAuthEntity.getUser();
                    if (userEntity != null) cohortAuthTO.setUser(constructUserTO(userEntity));

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
    public BooleanTO deleteSubmission(CohortTO cohortTO, String instanceId) {
        GetMethod getMethod = null;
        try {
            String queryUrl = String.format(cohortTO.getCohortQueryURL() + "&mode=delete", URLEncoder.encode(instanceId, "UTF-8"));

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
                    List<Result> results = new ObjectMapper().readValue(response, new TypeReference<List<Result>>() {});
                    if (results != null && results.size() == 1) {
                        Result result = results.get(0);
                        if (result != null && result.getId().equalsIgnoreCase("true")) {
                            return new BooleanTO(true);
                        }
                        return new BooleanTO(false);
                    } else {
                        return new BooleanTO(false);
                    }
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
                cohortFormTO.setDemographic(cohortFormEntity.getForm().getDemographic());
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
}
