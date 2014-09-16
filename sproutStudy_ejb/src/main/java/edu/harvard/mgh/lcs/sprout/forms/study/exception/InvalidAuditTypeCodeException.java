package edu.harvard.mgh.lcs.sprout.forms.study.exception;

public class InvalidAuditTypeCodeException extends Exception {

	private static final long serialVersionUID = 6804407248723428067L;

	private String auditTypeCode;
	
	public InvalidAuditTypeCodeException(String auditTypeCode) {
		this.auditTypeCode = auditTypeCode;
	}
	
	@Override
	public String getMessage() {
		return String.format("Invalid Audit Type Code.  No Audit Type found with code: %s", auditTypeCode);
	}
	
}
