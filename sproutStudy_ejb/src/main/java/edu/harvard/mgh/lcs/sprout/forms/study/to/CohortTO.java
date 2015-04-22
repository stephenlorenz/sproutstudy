package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CohortTO implements Serializable {

    private int id;
    private String name;
    private String description;
    private Date activityDate;
    private List<CohortAttrTO> attributes;
    private List<CohortFormTO> forms;
    private String cohortQueryURL;
    private String cohortSubjectSchema;
    private boolean active;
    private String cohortKey;
    private boolean transformEnabled;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    public List<CohortAttrTO> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<CohortAttrTO> attributes) {
        this.attributes = attributes;
    }

    public List<CohortFormTO> getForms() {
        return forms;
    }

    public void setForms(List<CohortFormTO> forms) {
        this.forms = forms;
    }

    public String getCohortQueryURL() {
        return cohortQueryURL;
    }

    public void setCohortQueryURL(String cohortQueryURL) {
        this.cohortQueryURL = cohortQueryURL;
    }

    public String getCohortSubjectSchema() {
        return cohortSubjectSchema;
    }

    public void setCohortSubjectSchema(String cohortSubjectSchema) {
        this.cohortSubjectSchema = cohortSubjectSchema;
    }

    public String getCohortKey() {
        return cohortKey;
    }

    public void setCohortKey(String cohortKey) {
        this.cohortKey = cohortKey;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isTransformEnabled() {
        if (attributes != null && attributes.size() > 0) {
            for (CohortAttrTO attribute : attributes) {
                if (attribute.getName() != null && attribute.getValue() != null && attribute.getName().equalsIgnoreCase("SPROUT_TRANSFORM_IND") && attribute.getValue().equalsIgnoreCase("true")) {
                    return true;
                }
            }
        }
        return false;
    }

}
