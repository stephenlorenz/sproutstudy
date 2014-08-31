package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;
import java.util.Date;

public class CohortFormTO implements Serializable {

    private String name;
    private String publicationKey;
    private boolean demographic;
    private Date activityDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublicationKey() {
        return publicationKey;
    }

    public void setPublicationKey(String publicationKey) {
        this.publicationKey = publicationKey;
    }

    public boolean isDemographic() {
        return demographic;
    }

    public void setDemographic(boolean demographic) {
        this.demographic = demographic;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }
}
