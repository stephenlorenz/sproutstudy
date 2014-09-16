package edu.harvard.mgh.lcs.sprout.forms.study.exception;

public class InvalidAuditVerbosityCodeException extends Exception {

	private static final long serialVersionUID = 6804407248723428067L;

	private String auditVerbosityCode;
	
	public InvalidAuditVerbosityCodeException(String auditVerbosityCode) {
		this.auditVerbosityCode = auditVerbosityCode;
	}
	
	@Override
	public String getMessage() {
		return String.format("Invalid Audit Verbosity Code.  No Audit Verbosity Level found with code: %s", auditVerbosityCode);
	}
	
}
