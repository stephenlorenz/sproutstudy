package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;
import java.util.Date;

public class CohortFormTO implements Serializable, Comparable<CohortFormTO> {

    private String id;
    private String name;
    private String publicationKey;
    private String formKey;
    private boolean demographic;
    private boolean manual = true;
    private boolean unique;
    private boolean active;
    private boolean archive;
    private boolean narrative;
    private boolean returnToHome;
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

    public String getFormKey() {
        return formKey;
    }

    public void setFormKey(String formKey) {
        this.formKey = formKey;
    }

    public boolean isDemographic() {
        return demographic;
    }

    public void setDemographic(boolean demographic) {
        this.demographic = demographic;
    }

    public boolean isManual() {
        return manual;
    }

    public void setManual(boolean manual) {
        this.manual = manual;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    public boolean isNarrative() {
        return narrative;
    }

    public void setNarrative(boolean narrative) {
        this.narrative = narrative;
    }

    public boolean isReturnToHome() {
        return returnToHome;
    }

    public void setReturnToHome(boolean returnToHome) {
        this.returnToHome = returnToHome;
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
