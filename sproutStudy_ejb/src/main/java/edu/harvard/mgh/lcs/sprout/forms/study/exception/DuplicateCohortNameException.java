package edu.harvard.mgh.lcs.sprout.forms.study.exception;

public class DuplicateCohortNameException extends Exception {

	private static final long serialVersionUID = 6804407248723428067L;

	private String cohortName;

	public DuplicateCohortNameException(String cohortName) {
		this.cohortName = cohortName;
	}
	
	@Override
	public String getMessage() {
		return String.format("Invalid Cohort Name.  Cohort Name, %s, is already used.  Please choose a different name for your cohort.", cohortName);
	}
	
}
