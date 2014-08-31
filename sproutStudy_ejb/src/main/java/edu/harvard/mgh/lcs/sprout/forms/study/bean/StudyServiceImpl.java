package edu.harvard.mgh.lcs.sprout.forms.study.bean;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.StudyService;
import edu.harvard.mgh.lcs.sprout.forms.study.beanws.Result;
import edu.harvard.mgh.lcs.sprout.forms.study.to.*;
import edu.harvard.mgh.lcs.sprout.forms.study.util.StringUtils;
import edu.harvard.mgh.lcs.sprout.study.model.study.*;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Stateless
@Remote(StudyService.class)
@TransactionManagement
public class StudyServiceImpl implements StudyService {

    @PersistenceContext(unitName = "sproutStudy_PU")
    private EntityManager entityManager;

    public static final String COHORT_ATTR_QUERY_URL = "QUERY";
    public static final String COHORT_ATTR_QUERY_AUTH_USERNAME = "QUERY_AUTH_USERNAME";
    public static final String COHORT_ATTR_QUERY_AUTH_PASSWORD = "QUERY_AUTH_PASSWORD";

    @Override
    public List<CohortTO> getCohorts(String username) {
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
                        cohortTO.setCohortQueryURL(getCohortQueryURL(cohortAttrTOList));

                        cohortTOList.add(cohortTO);
                    }
                }
            }
        } catch (NoResultException e) {
        }
        return cohortTOList;
    }

    private String getCohortQueryURL(List<CohortAttrTO> cohortAttrTOList) {
        if (cohortAttrTOList != null && cohortAttrTOList.size() > 0) {
            for (CohortAttrTO cohortAttrTO : cohortAttrTOList) {
                if (cohortAttrTO.getName().equalsIgnoreCase(COHORT_ATTR_QUERY_URL)) {
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
    public CohortTO getCohort(Integer cohortId) {
        if (cohortId != null && cohortId > 0) {
            CohortEntity cohortEntity = entityManager.find(CohortEntity.class, cohortId);
            if (cohortEntity != null) {
                CohortTO cohortTO = new CohortTO();
                cohortTO.setId(cohortEntity.getId());
                cohortTO.setName(cohortEntity.getName());
                cohortTO.setDescription(cohortEntity.getDescription());
                cohortTO.setActivityDate(cohortEntity.getActivityDate());

                List<CohortAttrTO> cohortAttrTOList = getCohortAttributes(cohortEntity);
                cohortTO.setAttributes(cohortAttrTOList);
                cohortTO.setForms(getCohortForms(cohortEntity));
                cohortTO.setCohortQueryURL(getCohortQueryURL(cohortAttrTOList));
                return cohortTO;
            }
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
                        return getCohort(Integer.parseInt(cohortId));
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
                cohortFormTO.setName(cohortFormEntity.getForm().getName());
                cohortFormTO.setPublicationKey(cohortFormEntity.getForm().getPublicationKey());
                cohortFormTO.setDemographic(cohortFormEntity.getForm().getDemographic());
                cohortFormTO.setActivityDate(cohortFormEntity.getForm().getActivityDate());
                cohortFormTOList.add(cohortFormTO);
            }
        }
        return cohortFormTOList;
    }


    private CohortEntity getCohortById(String cohortId) {
        if (StringUtils.isInteger(cohortId)) {
            return entityManager.find(CohortEntity.class, Integer.parseInt(cohortId));
        }
        return null;
    }

}
