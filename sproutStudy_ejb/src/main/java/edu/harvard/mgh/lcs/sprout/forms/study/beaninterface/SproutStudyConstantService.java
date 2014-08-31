package edu.harvard.mgh.lcs.sprout.forms.study.beaninterface;

public interface SproutStudyConstantService {

	public static enum SubmissionStatus {NEW, INPROGRESS, PUSHED, HOLD, REVOKED, NONE, ERROR}
	public static enum ProviderCodeType {IHS_GROUP_CODE, PUN}
	public static enum PatientVerificationSearchSelector {MRN, LASTNAME, FIRSTNAME, MIDDLENAME, DOB, SEX, PHONE, ADDRESS}
	public static final String SENDER_FRONT_OFFICE = "FRONT_OFFICE";
	
}
