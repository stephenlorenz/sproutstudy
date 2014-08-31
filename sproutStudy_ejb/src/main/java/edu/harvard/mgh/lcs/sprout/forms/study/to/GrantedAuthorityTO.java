package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: slorenz
 * Date: 9/16/13
 * Time: 4:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class GrantedAuthorityTO implements Serializable {

    private int id;
    private String principal;
    private String sn;
    private String givenName;
    private String displayName;
    private String email;
    private String cn;
    private int applicationAuthId;
    private String applicationAuthName;
    private Boolean manager;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
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

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public int getApplicationAuthId() {
        return applicationAuthId;
    }

    public void setApplicationAuthId(int applicationAuthId) {
        this.applicationAuthId = applicationAuthId;
    }

    public String getApplicationAuthName() {
        return applicationAuthName;
    }

    public void setApplicationAuthName(String applicationAuthName) {
        this.applicationAuthName = applicationAuthName;
    }

    public Boolean getManager() {
        return manager;
    }

    public void setManager(Boolean manager) {
        this.manager = manager;
    }
}
