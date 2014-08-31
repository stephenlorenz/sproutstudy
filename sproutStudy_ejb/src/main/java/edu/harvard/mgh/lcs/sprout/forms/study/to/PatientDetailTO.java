package edu.harvard.mgh.lcs.sprout.forms.study.to;

import edu.harvard.mgh.lcs.sprout.forms.study.util.StringUtils;
import org.apache.commons.lang.WordUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class PatientDetailTO implements Serializable {

	private static final long serialVersionUID = 4666604900086047409L;

	private String firstName = "";
	private String middleName = "";
	private String lastName = "";
	private String mrn;
	private String address1 = "";
	private String address2 = "";
	private String city = "";
	private String state = "";
	private String zip = "";
	private Date dob;
	private String sex;

	private String pin;
	private boolean optOut;
	
	private String practice;
	private String program;
	private String provider;
	private String userType;
	private String username;
	private String usernameVerify;
	private String email;
	private String emailVerify;
	
	private Set<IdentityTO> identities;

	public String getDobPretty() {
		return dob != null ? StringUtils.formatSimpleDate(dob) : "";
	}
	
	public String getFullName() {
		if (!StringUtils.isEmpty(firstName) && !StringUtils.isEmpty(lastName)) {
			if (!StringUtils.isEmpty(middleName)) {
				return WordUtils.capitalizeFully(String.format("%s %s %s", firstName, middleName, lastName)).trim();				
			} else {
				return WordUtils.capitalizeFully(String.format("%s %s", firstName, lastName).trim());
			}
		}
		return "";
	}
	
	public String getFirstNamePretty() {
		if (!StringUtils.isEmpty(firstName)) {
			return WordUtils.capitalizeFully(firstName).trim();
		}
		return "";
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
	public String getMrn() {
		return mrn;
	}
	public void setMrn(String mrn) {
		this.mrn = mrn;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public Date getDob() {
		return dob;
	}
	public String getBirthdate() {
        return getDobPretty();
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getSex() {
		return sex;
	}
	public String getGenderCode() {
		return getSex();
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public boolean isOptOut() {
		return optOut;
	}
	public void setOptOut(boolean optOut) {
		this.optOut = optOut;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getPractice() {
		return practice;
	}
	public void setPractice(String practice) {
		this.practice = practice;
	}
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsernameVerify() {
		return usernameVerify;
	}
	public void setUsernameVerify(String usernameVerify) {
		this.usernameVerify = usernameVerify;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmailVerify() {
		return emailVerify;
	}
	public void setEmailVerify(String emailVerify) {
		this.emailVerify = emailVerify;
	}

	public Set<IdentityTO> getIdentities() {
		return identities;
	}

	public void setIdentities(Set<IdentityTO> identities) {
		this.identities = identities;
	}

	
	
}
