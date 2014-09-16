package edu.harvard.mgh.lcs.sprout.study.web.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//import org.apache.log4j.Logger;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.SecurityService;
import edu.harvard.mgh.lcs.sprout.forms.study.util.StringUtils;
//import org.springframework.security.GrantedAuthority;
//import org.springframework.security.GrantedAuthorityImpl;
//import org.springframework.security.userdetails.UserDetails;

public class SproutStudyUserDetails implements UserDetails {

	private static final long serialVersionUID = 3704597264685178162L;

//	protected Logger logger = Logger.getLogger(this.getClass().getName());

	private String username;
	private String firstName;
	private String lastName;
	private String email;
	
	private Set<String> practices;

	public SproutStudyUserDetails(Assertion assertion, SecurityService securityService) {
		username = assertion.getPrincipal().getName();
		firstName = getStringAttribute(assertion, "first_name");
		lastName = getStringAttribute(assertion, "last_name");
		email = getStringAttribute(assertion, "email");
	}

	private String getStringAttribute(Assertion assertion, String key) {
		final Object value = assertion.getPrincipal().getAttributes().get(key);

		if (value != null && value instanceof String) {
			return (String) value;
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	private List<String> getListAttribute(Assertion assertion, String key) {
		List<String> attrValueList = new ArrayList<String>();  
		Object value = assertion.getPrincipal().getAttributes().get(key);

		if (value != null) {

			if (value instanceof List) {
				final List list = (List) value;

				for (final Object object : list) {
					attrValueList.add(object.toString());
				}
				return attrValueList;

			}
		}
		return null;
	}


	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> retVal = new ArrayList<GrantedAuthority>();
		retVal.add(new SimpleGrantedAuthority("ROLE_USER"));
		return retVal;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getDisplayName() {
		if (!StringUtils.isEmpty(firstName) && !StringUtils.isEmpty(lastName)) {
			return String.format("%s %s", firstName, lastName);
		}
		return null;
	}

	public Set<String> getPractices() {
		return practices;
	}

	public void setPractices(Set<String> practices) {
		this.practices = practices;
	}

	public void addPractice(String practice) {
		if (practices == null) practices = new HashSet<String>();
		practices.add(practice);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
