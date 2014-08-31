package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CohortMemberTO implements Serializable {
	
    private String fullName;
    private String prettyName;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String gender;
    private Date birthDate;

    private List<CohortAddressTO> cohortAddressTOList;
    private List<CohortIdentityTO> cohortIdentityTOList;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPrettyName() {
        return prettyName;
    }

    public void setPrettyName(String prettyName) {
        this.prettyName = prettyName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<CohortAddressTO> getCohortAddressTOList() {
        return cohortAddressTOList;
    }

    public void setCohortAddressTOList(List<CohortAddressTO> cohortAddressTOList) {
        this.cohortAddressTOList = cohortAddressTOList;
    }

    public List<CohortIdentityTO> getCohortIdentityTOList() {
        return cohortIdentityTOList;
    }

    public void setCohortIdentityTOList(List<CohortIdentityTO> cohortIdentityTOList) {
        this.cohortIdentityTOList = cohortIdentityTOList;
    }
}
