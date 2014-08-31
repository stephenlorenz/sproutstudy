package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;
import java.util.Set;

public class SessionTO implements Serializable {
	
	private static final long serialVersionUID = -4577065110251788265L;

	private String user;
    private CohortTO cohortTO;

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}

    public CohortTO getCohortTO() {
        return cohortTO;
    }

    public void setCohortTO(CohortTO cohortTO) {
        this.cohortTO = cohortTO;
    }
}
