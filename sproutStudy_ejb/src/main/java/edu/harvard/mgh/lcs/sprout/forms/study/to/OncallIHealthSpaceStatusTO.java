package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;

public class OncallIHealthSpaceStatusTO implements Serializable {

	private static final long serialVersionUID = 3791123004758365662L;

	private String mrn;
	private String patientEmailAddress;
	private String ihealthspacePin;
	private String ihealthspaceEnrollmentCode;
	private boolean optOut;
	private String groupName;
	private String lastEnrollmentAttemptErrorMsg;
	private String patientUsername;
	private String custodianEmailAddress;
	private String custodianUsername;
	
	public String getMrn() {
		return mrn;
	}
	public void setMrn(String mrn) {
		this.mrn = mrn;
	}
	public String getPatientEmailAddress() {
		return patientEmailAddress;
	}
	public void setPatientEmailAddress(String patientEmailAddress) {
		this.patientEmailAddress = patientEmailAddress;
	}
	public String getIhealthspacePin() {
		return ihealthspacePin;
	}
	public void setIhealthspacePin(String ihealthspacePin) {
		this.ihealthspacePin = ihealthspacePin;
	}
	public String getIhealthspaceEnrollmentCode() {
		return ihealthspaceEnrollmentCode;
	}
	public void setIhealthspaceEnrollmentCode(String ihealthspaceEnrollmentCode) {
		this.ihealthspaceEnrollmentCode = ihealthspaceEnrollmentCode;
	}
	public boolean isOptOut() {
		return optOut;
	}
	public void setOptOut(boolean optOut) {
		this.optOut = optOut;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getLastEnrollmentAttemptErrorMsg() {
		return lastEnrollmentAttemptErrorMsg;
	}
	public void setLastEnrollmentAttemptErrorMsg(
			String lastEnrollmentAttemptErrorMsg) {
		this.lastEnrollmentAttemptErrorMsg = lastEnrollmentAttemptErrorMsg;
	}
	public String getPatientUsername() {
		return patientUsername;
	}
	public void setPatientUsername(String patientUsername) {
		this.patientUsername = patientUsername;
	}
	public String getCustodianEmailAddress() {
		return custodianEmailAddress;
	}
	public void setCustodianEmailAddress(String custodianEmailAddress) {
		this.custodianEmailAddress = custodianEmailAddress;
	}
	public String getCustodianUsername() {
		return custodianUsername;
	}
	public void setCustodianUsername(String custodianUsername) {
		this.custodianUsername = custodianUsername;
	}

}
