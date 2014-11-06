package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;
import java.util.Date;

public class CohortFormTO implements Serializable, Comparable<CohortFormTO> {

    private String id;
    private String name;
    private String publicationKey;
    private boolean demographic;
    private boolean unique;
    private Date activityDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    @Override
    public int compareTo(CohortFormTO cohortFormTO) {
        return name.compareTo(cohortFormTO.getName());
    }
}
