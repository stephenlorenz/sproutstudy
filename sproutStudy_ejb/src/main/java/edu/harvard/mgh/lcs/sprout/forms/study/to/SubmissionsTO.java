package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;
import java.util.List;

public class SubmissionsTO implements Serializable {

	private static final long serialVersionUID = 8109728591315653877L;

	private List<FormSubmissionTO> submissions;

	public SubmissionsTO(List<FormSubmissionTO> submissions) {
		super();
		this.submissions = submissions;
	}

	public SubmissionsTO() {
		super();
	}

	public List<FormSubmissionTO> getSubmissions() {
		return submissions;
	}

	public void setSubmissions(List<FormSubmissionTO> submissions) {
		this.submissions = submissions;
	}

}
