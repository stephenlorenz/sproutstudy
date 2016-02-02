package edu.harvard.mgh.lcs.sprout.forms.study.ws;

import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.SproutListService;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.SproutTransformService;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.StudyService;
import edu.harvard.mgh.lcs.sprout.forms.study.exception.UnauthorizedActionException;
import edu.harvard.mgh.lcs.sprout.forms.study.to.BooleanTO;
import edu.harvard.mgh.lcs.sprout.forms.study.to.SproutListTO;
import edu.harvard.mgh.lcs.sprout.forms.study.wsinterface.SproutStudyWebService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.jws.WebService;
import java.util.List;

@Stateless
@WebService(name="SproutStudyAPIWS", endpointInterface="edu.harvard.mgh.lcs.sprout.forms.study.wsinterface.SproutStudyWebService")
@TransactionManagement
public class SproutStudyWebServiceImpl implements SproutStudyWebService {

    @EJB
    private StudyService studyService;

    @EJB
    private SproutListService sproutListService;

    @EJB
    private SproutTransformService sproutTransformService;

    @Override
    public BooleanTO markAsRead(String instanceId) {
        return studyService.markInboxMessageAsRead(instanceId);

    }
    @Override
    public String getFormFromPublicationKey(String publicationKey) {
        return studyService.getFormFromPublicationKey(publicationKey);
    }

    @Override
    public String getFormKeyFromPublicationKey(String publicationKey) {
        return studyService.getFormKeyFromPublicationKey(publicationKey);
    }

    @Override
    public List<SproutListTO> getSproutList(String cohortName, String listKey, String publicationKey) {
        try {
            return sproutListService.getList(cohortName, listKey, publicationKey);
        } catch (UnauthorizedActionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public SproutListTO getSproutListTO(String cohortName, String listKey, String publicationKey, String value) {
        try {
            return sproutListService.getListTO(cohortName, listKey, publicationKey, value);
        } catch (UnauthorizedActionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getPublicationKeyFromFormKey(String formKey) {
        return studyService.getPublicationKeyFromFormKey(formKey);
    }

    @Override
    public String getNarrativeByInstanceId(String instanceId) {
        return sproutTransformService.getNarrativeByInstanceId(instanceId);
    }

    @Override
    public String getNarrativeByInstanceIdWithFormat(String instanceId, String format) {
        return sproutTransformService.getNarrativeByInstanceId(instanceId, format);
    }

    public String getNarrative(String publicationKey, String instanceId, String jsonData) {
        return sproutTransformService.getNarrative(publicationKey, instanceId, jsonData);
    }

    @Override
    public String getNarrative(String publicationKey, String instanceId, String jsonData, String format) {
        return sproutTransformService.getNarrative(publicationKey, instanceId, jsonData, format);
    }
}
