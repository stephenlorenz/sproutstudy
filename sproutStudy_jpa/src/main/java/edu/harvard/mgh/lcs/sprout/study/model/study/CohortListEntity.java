package edu.harvard.mgh.lcs.sprout.study.model.study;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(schema="dbo", name="cohort_list")
public class CohortListEntity implements Serializable {

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="cohort_id")
    private CohortEntity cohort;

    @Column
    private String name;

    @Column
    private String description;

    @Column(name="name_column_title")
    private String nameColumnTitle;

    @Column(name="value_column_title")
    private String valueColumnTitle;

    @Column(name="active_ind")
    private boolean active = false;

    @OneToMany(targetEntity=CohortListDataEntity.class, mappedBy="cohortList", cascade=CascadeType.MERGE)
    private List<CohortListDataEntity> cohortListData = new ArrayList<CohortListDataEntity>();

    @Basic
    @Column(name="activity_date", columnDefinition="datetime", nullable=false)
    private Date activityDate = new Date();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CohortEntity getCohort() {
        return cohort;
    }

    public void setCohort(CohortEntity cohort) {
        this.cohort = cohort;
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


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<CohortListDataEntity> getCohortListData() {
        return cohortListData;
    }

    public void setCohortListData(List<CohortListDataEntity> cohortListData) {
        this.cohortListData = cohortListData;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }
}