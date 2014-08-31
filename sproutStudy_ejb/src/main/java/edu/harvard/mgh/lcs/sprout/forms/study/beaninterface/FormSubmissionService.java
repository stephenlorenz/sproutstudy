package edu.harvard.mgh.lcs.sprout.forms.study.beaninterface;

import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.SproutStudyConstantService.SubmissionStatus;
import edu.harvard.mgh.lcs.sprout.forms.study.to.BooleanTO;
import edu.harvard.mgh.lcs.sprout.forms.study.to.FormSubmissionTO;
import edu.harvard.mgh.lcs.sprout.study.model.formSubmission.SubmissionEntity;

import java.util.List;
import java.util.Set;

public interface FormSubmissionService {
	public abstract String postFormSubmission(String orgAuthKey, String trackingCode, String metaData);
    public abstract String logFormSubmissionEvent(String orgAuthKey, String trackingCode, String metaData, SubmissionStatus status, String message);
	public abstract SubmissionStatus getSubmissionStatus(String publicationKey, String instanceId, String deliveryKey);
	public abstract SubmissionStatus pushToEMR(String instanceId);
	public abstract SubmissionStatus returnToSender(String instanceId);
	public abstract List<FormSubmissionTO> getFormSubmissions(Set<String> publicationKeys, boolean mutableInd, String principal);
	public abstract BooleanTO syncFormSubmission(String instanceId, String[] verifiedIdentifiers, String[] matchedIdentifiers, String[] matchedAssertions, String principal);
    public abstract BooleanTO appendProviderAsParameter(String instanceId, String partnersUsername, String principal);
    public abstract FormSubmissionTO getFormSubmission(String instanceId, String principal);
    public abstract BooleanTO isPatientVerified(String mrn, String user);
    public abstract BooleanTO isPatientAssertive(String mrn, String user);
    public abstract SubmissionEntity findSubmissionByInstanceId(String instanceId);
}
