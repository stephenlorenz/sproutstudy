package edu.harvard.mgh.lcs.sprout.forms.study.bean;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.*;
import edu.harvard.mgh.lcs.sprout.forms.study.beanws.Result;
import edu.harvard.mgh.lcs.sprout.forms.study.exception.DuplicateCohortNameException;
import edu.harvard.mgh.lcs.sprout.forms.study.exception.UnauthorizedActionException;
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
import java.util.logging.Logger;

@Stateless
@Remote(SproutListService.class)
@TransactionManagement
public class SproutListServiceImpl implements SproutListService, SproutStudyConstantService {

    private static final Logger LOGGER = Logger.getLogger(SproutListServiceImpl.class.getName());

    @PersistenceContext(unitName = "sproutStudy_PU")
    private EntityManager entityManager;

    @EJB
    private AuditService auditService;

    @EJB
    private StudyService studyService;

    @Override
    public List<SproutListTO> getList(String cohortName, String listKey, String publicationKey) throws UnauthorizedActionException {
        if (StringUtils.isFull(cohortName, listKey)) {
            CohortEntity cohortEntity = studyService.getCohortByName(cohortName);
            if (cohortEntity != null) {
                CohortListEntity cohortListEntity = studyService.getListByListKey(cohortEntity, listKey);
                if (cohortListEntity != null && cohortListEntity.getCohortListData() != null && cohortListEntity.getCohortListData().size() > 0) {
                    if (!cohortListEntity.isPublicInd()) {
                        boolean authorizedInd = false;
                        if (StringUtils.isFull(publicationKey)) {
                            if (publicationKey.equals("-1")) {
                                authorizedInd = true;
                            } else {
                                if (cohortListEntity.getCohort() != null && cohortListEntity.getCohort().getCohortForms() != null && cohortListEntity.getCohort().getCohortForms().size() > 0) {
                                    for (CohortFormEntity cohortFormEntity : cohortListEntity.getCohort().getCohortForms()) {
                                        if (cohortFormEntity.getForm() != null && cohortFormEntity.getForm().getPublicationKey() != null && cohortFormEntity.getForm().getPublicationKey().equalsIgnoreCase(publicationKey)) {
                                            authorizedInd = true;
                                            break;
                                        }
                                    }
                                }
                            }
                        } else {
                            throw new UnauthorizedActionException("This SproutList is not public; you must provide a valid publication key.");
                        }
                        if (!authorizedInd) {
                            throw new UnauthorizedActionException("You are not authorized to access this SproutList.  Please provided a valid publication key.");
                        }
                    }

                    List<SproutListTO> sproutListTOList = new ArrayList<SproutListTO>();
                    for (CohortListDataEntity cohortListDataEntity : cohortListEntity.getCohortListData()) {
                        SproutListTO sproutListTO = new SproutListTO();
                        sproutListTO.setName(cohortListDataEntity.getName());
                        sproutListTO.setValue(cohortListDataEntity.getValue());
                        sproutListTO.setDetails(generateSproutListDetails(cohortListDataEntity));
                        sproutListTOList.add(sproutListTO);
                    }
                    if (sproutListTOList != null) Collections.sort(sproutListTOList);
                    return sproutListTOList;
                }
            }

        }
        return null;
    }

    @Override
    public SproutListTO getListTO(String cohortName, String listKey, String publicationKey, String value) throws UnauthorizedActionException {
        if (StringUtils.isFull(cohortName, listKey, value)) {
            CohortEntity cohortEntityTmp = studyService.getCohortByName(cohortName);

            CohortEntity cohortEntity = entityManager.find(CohortEntity.class, cohortEntityTmp.getId());

            if (cohortEntity != null) {
                CohortListEntity cohortListEntity = studyService.getListByListKey(cohortEntity, listKey);
                if (cohortListEntity != null && cohortListEntity.getCohortListData() != null && cohortListEntity.getCohortListData().size() > 0) {
                    if (!cohortListEntity.isPublicInd()) {
                        boolean authorizedInd = false;
                        if (StringUtils.isFull(publicationKey)) {
                            if (publicationKey.equals("-1")) {
                                authorizedInd = true;
                            } else {
                                if (cohortListEntity.getCohort() != null) {
                                    if (cohortEntity.getCohortForms() != null && cohortEntity.getCohortForms().size() > 0) {
                                        for (CohortFormEntity cohortFormEntity : cohortEntity.getCohortForms()) {
                                            if (cohortFormEntity.getForm() != null && cohortFormEntity.getForm().getPublicationKey() != null && cohortFormEntity.getForm().getPublicationKey().equalsIgnoreCase(publicationKey)) {
                                                authorizedInd = true;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            throw new UnauthorizedActionException("This SproutList is not public; you must provide a valid publication key.");
                        }
                        if (!authorizedInd) {
                            throw new UnauthorizedActionException("You are not authorized to access this SproutList.  Please provided a valid publication key.");
                        }
                    }

                    try {
                        Query query = entityManager.createNamedQuery(CohortListDataEntity.BY_VALUE);
                        query.setParameter("value", value);
                        query.setParameter("cohortList", cohortListEntity);
                        List<CohortListDataEntity> cohortListDataEntities = query.getResultList();
                        if (cohortListDataEntities != null && cohortListDataEntities.size() > 0) {
                            for (CohortListDataEntity cohortListDataEntity : cohortListDataEntities) {
                                SproutListTO sproutListTO = new SproutListTO();
                                sproutListTO.setName(cohortListDataEntity.getName());
                                sproutListTO.setValue(cohortListDataEntity.getValue());
                                sproutListTO.setDetails(generateSproutListDetails(cohortListDataEntity));
                                return sproutListTO;
                            }
                        }

                    } catch (NoResultException e) {
                    }
                }
            }
        }
        return null;
    }

    private List<SproutListDetailTO> generateSproutListDetails(CohortListDataEntity cohortListDataEntity) {
        if (cohortListDataEntity != null && cohortListDataEntity.getCohortListDetailData() != null && cohortListDataEntity.getCohortListDetailData().size() > 0) {
            List<SproutListDetailTO> sproutListDetailTOList = new ArrayList<SproutListDetailTO>();
            for (CohortListDetailDataEntity cohortListDetailDataEntity : cohortListDataEntity.getCohortListDetailData()) {
                SproutListDetailTO sproutListDetailTO = new SproutListDetailTO();
                sproutListDetailTO.setName(cohortListDetailDataEntity.getCohortListDetail().getName());
                sproutListDetailTO.setValue(cohortListDetailDataEntity.getValue());
                sproutListDetailTOList.add(sproutListDetailTO);
            }
            return sproutListDetailTOList;
        }
        return null;
    }
}
