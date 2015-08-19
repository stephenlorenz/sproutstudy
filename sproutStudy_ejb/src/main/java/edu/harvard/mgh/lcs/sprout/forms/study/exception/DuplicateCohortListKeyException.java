package edu.harvard.mgh.lcs.sprout.forms.study.exception;

public class DuplicateCohortListKeyException extends Exception {

	private static final long serialVersionUID = 6804407248723428067L;

	private String cohortListKey;

	public DuplicateCohortListKeyException(String cohortListKey) {
		this.cohortListKey = cohortListKey;
	}
	
	@Override
	public String getMessage() {
		return String.format("Invalid Cohort List Key.  Cohort List Key, %s, is already used.  Please choose a different key for your cohort list.", cohortListKey);
	}
	
}
