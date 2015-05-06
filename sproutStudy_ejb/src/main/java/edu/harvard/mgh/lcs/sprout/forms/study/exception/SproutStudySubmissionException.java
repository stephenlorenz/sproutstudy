package edu.harvard.mgh.lcs.sprout.forms.study.exception;

public class SproutStudySubmissionException extends Exception {

	private String message;

	public SproutStudySubmissionException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
}
