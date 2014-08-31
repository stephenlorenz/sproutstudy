package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: slorenz
 * Date: 9/17/13
 * Time: 9:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationAuthorityTO implements Serializable {

    private int id;
    private String code;
    private String description;
    private int applicationId;
    private String applicationName;
    private String applicationDescription;
    private String applicationTitle;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationDescription() {
        return applicationDescription;
    }

    public void setApplicationDescription(String applicationDescription) {
        this.applicationDescription = applicationDescription;
    }

    public String getApplicationTitle() {
        return applicationTitle;
    }

    public void setApplicationTitle(String applicationTitle) {
        this.applicationTitle = applicationTitle;
    }
}
