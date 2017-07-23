package edu.harvard.mgh.lcs.sprout.forms.study.exception;

public class HtmlToPdfException extends Exception {

	private String message;

	public HtmlToPdfException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
}
