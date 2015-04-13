package edu.harvard.mgh.lcs.sprout.forms.study.to;

import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.SproutStudyConstantService;

import java.io.Serializable;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: slorenz
 * Date: 9/16/13
 * Time: 4:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserTO implements Serializable {

    private int id;
    private String username;
    private String password;
    private int salt;
    private String firstName;
    private String lastName;
    private boolean active;
    private String email;

    private DomainTO domain;
    private Set<UserPreferenceTO> preferences;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSalt() {
        return salt;
    }

    public void setSalt(int salt) {
        this.salt = salt;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public DomainTO getDomain() {
        return domain;
    }

    public void setDomain(DomainTO domain) {
        this.domain = domain;
    }

    public Set<UserPreferenceTO> getPreferences() {
        return preferences;
    }

    public void setPreferences(Set<UserPreferenceTO> preferences) {
        this.preferences = preferences;
    }

    public String getFullName() {
        if (firstName != null && lastName != null) {
            return String.format("%s %s", firstName, lastName);
        }
        return null;
    }

    public String getEmail() {
        if (preferences != null && preferences.size() > 0) {
            for (UserPreferenceTO preference : preferences) {
                if (preference.getCode().equalsIgnoreCase(SproutStudyConstantService.UserPreference.EMAIL_PRIMARY.toString())) {
                    return preference.getValue();
                }
            }
        }
        return null;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
