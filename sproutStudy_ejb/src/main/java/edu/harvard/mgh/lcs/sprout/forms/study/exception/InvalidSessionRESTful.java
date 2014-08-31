package edu.harvard.mgh.lcs.sprout.forms.study.exception;

public class InvalidSessionRESTful extends Exception {

	private static final long serialVersionUID = -1135516442740290746L;

	@Override
	public String getMessage() {
		return "Invalid user session";
	}

}
