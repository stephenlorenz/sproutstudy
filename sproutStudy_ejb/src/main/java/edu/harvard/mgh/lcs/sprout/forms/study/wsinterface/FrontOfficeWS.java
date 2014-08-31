package edu.harvard.mgh.lcs.sprout.forms.study.wsinterface;

import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.SproutStudyConstantService;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface FrontOfficeWS {

    public abstract String postFormSubmission (
            @WebParam(name = "orgAuthKey") String orgAuthKey,
            @WebParam(name = "trackingCode") String trackingCode,
            @WebParam(name = "submissionData") String submissionData);

    public abstract String logFormProcessing (
            @WebParam(name = "orgAuthKey") String orgAuthKey,
            @WebParam(name = "trackingCode") String trackingCode,
            @WebParam(name = "submissionData") String submissionData,
            @WebParam(name = "status") SproutStudyConstantService.SubmissionStatus status,
            @WebParam(name = "message") String message);


}
