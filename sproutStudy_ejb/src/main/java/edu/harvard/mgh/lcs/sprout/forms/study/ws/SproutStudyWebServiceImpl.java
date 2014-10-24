package edu.harvard.mgh.lcs.sprout.forms.study.ws;

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

    @Override
    public BooleanTO markAsRead(String instanceId) {
        return studyService.markInboxMessageAsRead(instanceId);
    }
}
