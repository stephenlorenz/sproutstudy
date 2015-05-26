package edu.harvard.mgh.lcs.sprout.forms.study.ws;

import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.SproutTransformService;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.StudyService;
import edu.harvard.mgh.lcs.sprout.forms.study.to.BooleanTO;
import edu.harvard.mgh.lcs.sprout.forms.study.wsinterface.SproutStudyWebService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.jws.WebService;

@Stateless
@WebService(name="SproutStudyAPIWS", endpointInterface="edu.harvard.mgh.lcs.sprout.forms.study.wsinterface.SproutStudyWebService")
@TransactionManagement
public class SproutStudyWebServiceImpl implements SproutStudyWebService {

    @EJB
    private StudyService studyService;

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
    public String getPublicationKeyFromFormKey(String formKey) {
        return studyService.getPublicationKeyFromFormKey(formKey);
    }

    public String getNarrative(String publicationKey, String instanceId, String jsonData) {
        return sproutTransformService.getNarrative(publicationKey, instanceId, jsonData);
    }

    @Override
    public String getNarrative(String publicationKey, String instanceId, String jsonData, String format) {
        return sproutTransformService.getNarrative(publicationKey, instanceId, jsonData, format);
    }
}
