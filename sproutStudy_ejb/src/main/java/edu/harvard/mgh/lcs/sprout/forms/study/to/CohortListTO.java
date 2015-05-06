package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CohortListTO implements Serializable, Comparable<CohortListTO> {

    private String id;
    private String name;
    private String description;
    private String nameColumnTitle;
    private String valueColumnTitle;
    private List<CohortListDataTO> data;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNameColumnTitle() {
        return nameColumnTitle;
    }

    public void setNameColumnTitle(String nameColumnTitle) {
        this.nameColumnTitle = nameColumnTitle;
    }

    public String getValueColumnTitle() {
        return valueColumnTitle;
    }

    public void setValueColumnTitle(String valueColumnTitle) {
        this.valueColumnTitle = valueColumnTitle;
    }

    public List<CohortListDataTO> getData() {
        return data;
    }

    public void setData(List<CohortListDataTO> data) {
        this.data = data;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    public void addData(CohortListDataTO cohortListDataTO) {
        if (data == null) data = new ArrayList<CohortListDataTO>();
        data.add(cohortListDataTO);
    }

    @Override
    public int compareTo(CohortListTO cohortListTO) {
        return name.compareTo(cohortListTO.getName());
    }
}
