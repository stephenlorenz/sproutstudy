package edu.harvard.mgh.lcs.sprout.forms.study.bean;

import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.*;
import edu.harvard.mgh.lcs.sprout.forms.study.exception.UnauthorizedListAccessException;
import edu.harvard.mgh.lcs.sprout.forms.study.to.*;
import edu.harvard.mgh.lcs.sprout.forms.study.util.StringUtils;
import edu.harvard.mgh.lcs.sprout.study.model.study.*;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;
import java.util.logging.Logger;

@Stateless
@Remote(SproutListService.class)
@TransactionManagement
public class SproutListServiceImpl implements SproutListService {

    private static final Logger LOGGER = Logger.getLogger(SproutListServiceImpl.class.getName());

    @PersistenceContext(unitName = "sproutStudy_PU")
    private EntityManager entityManager;

    @EJB
    private AuditService auditService;

    @EJB
    private StudyService studyService;


    @Override
    public List<SproutListTO> getList(String cohortName, String listKey, String publicationKey) {
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
                            try {
                                throw new UnauthorizedListAccessException("This SproutList is not public; you must provide a valid publication key.");
                            } catch (UnauthorizedListAccessException e) {
                                e.printStackTrace();
                                return null;
                            }
                        }
                        if (!authorizedInd) {
                            try {
                                throw new UnauthorizedListAccessException("You are not authorized to access this SproutList.  Please provided a valid publication key.");
                            } catch (UnauthorizedListAccessException e) {
                                e.printStackTrace();
                                return null;
                            }
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

    private Map<String, String> generateSproutListDetails(CohortListDataEntity cohortListDataEntity) {
        if (cohortListDataEntity != null && cohortListDataEntity.getCohortListDetailData() != null && cohortListDataEntity.getCohortListDetailData().size() > 0) {
            Map<String, String> sproutListDetailsMap = new HashMap<String, String>();
            for (CohortListDetailDataEntity cohortListDetailDataEntity : cohortListDataEntity.getCohortListDetailData()) {
                sproutListDetailsMap.put(cohortListDetailDataEntity.getCohortListDetail().getName(), cohortListDetailDataEntity.getValue());
            }
            return sproutListDetailsMap;
        }
        return null;
    }
}
