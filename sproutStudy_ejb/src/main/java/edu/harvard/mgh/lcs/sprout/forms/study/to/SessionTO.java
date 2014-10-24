package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;
import java.lang.String;
import java.util.Set;

public class SessionTO implements Serializable {
	
	private static final long serialVersionUID = -4577065110251788265L;

	private String user;
    private String firstName;
    private String lastName;
    private String email;
    private CohortTO cohortTO;

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CohortTO getCohortTO() {
        return cohortTO;
    }

    public void setCohortTO(CohortTO cohortTO) {
        this.cohortTO = cohortTO;
    }
}
