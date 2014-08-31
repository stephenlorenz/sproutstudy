package edu.harvard.mgh.lcs.sprout.forms.study.ws;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.jws.WebParam;
import javax.jws.WebService;

import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.FormSubmissionService;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.SproutStudyConstantService;
import edu.harvard.mgh.lcs.sprout.forms.study.wsinterface.FrontOfficeWS;

@Stateless
@WebService(name="FrontOfficeWS", endpointInterface= "edu.harvard.mgh.lcs.sprout.forms.study.wsinterface.FrontOfficeWS")
@TransactionManagement
public class FrontOfficeWSImpl implements FrontOfficeWS {

	@EJB
	private FormSubmissionService formSubmissionService;
	
	@Override
	public String postFormSubmission (
            @WebParam(name = "orgAuthKey") String orgAuthKey,
            @WebParam(name = "trackingCode") String trackingCode,
			@WebParam(name = "submissionData") String submissionData) {

//        System.out.println("+++++++++++++++++++++++++++++++++++++++\npostFormSubmission: orgAuthKey: " + orgAuthKey);
//        System.out.println("postFormSubmission: trackingCode: " + trackingCode);
//        System.out.println("postFormSubmission: submissionData: " + submissionData);

        String retVal = formSubmissionService.postFormSubmission(orgAuthKey, trackingCode, submissionData);

//        System.out.println("postFormSubmission: retVal: " + retVal);

        return retVal;
	}

    @Override
    public String logFormProcessing (
            @WebParam(name = "orgAuthKey") String orgAuthKey,
            @WebParam(name = "trackingCode") String trackingCode,
            @WebParam(name = "submissionData") String submissionData,
            @WebParam(name = "status") SproutStudyConstantService.SubmissionStatus status,
            @WebParam(name = "message") String message) {
        return formSubmissionService.logFormSubmissionEvent(orgAuthKey, trackingCode, submissionData, status, message);
    }

}
