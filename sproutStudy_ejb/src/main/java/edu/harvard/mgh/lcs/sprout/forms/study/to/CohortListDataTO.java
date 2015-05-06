package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CohortListDataTO implements Serializable {

    private String id;
    private String name;
    private String value;
    private List<CohortListDataDiscriminatorTO> discriminators;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<CohortListDataDiscriminatorTO> getDiscriminators() {
        return discriminators;
    }

    public void setDiscriminators(List<CohortListDataDiscriminatorTO> discriminators) {
        this.discriminators = discriminators;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }
}
