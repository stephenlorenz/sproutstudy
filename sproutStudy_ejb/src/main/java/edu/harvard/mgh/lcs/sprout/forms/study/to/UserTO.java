package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: slorenz
 * Date: 9/16/13
 * Time: 4:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserTO implements Serializable {

    private String cn;
    private String sn;
    private String givenName;
    private String displayName;
    private String email;

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
