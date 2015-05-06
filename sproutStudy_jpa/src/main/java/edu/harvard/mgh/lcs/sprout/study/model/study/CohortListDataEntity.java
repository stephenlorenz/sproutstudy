package edu.harvard.mgh.lcs.sprout.study.model.study;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(schema="dbo", name="cohort_list_data")
public class CohortListDataEntity implements Serializable {

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="cohort_list_id")
    private CohortListEntity cohortList;

    @Column
    private String name;

    @Column
    private String value;

    @OneToMany(targetEntity=CohortListDataDiscriminatorEntity.class, mappedBy="cohortListData", cascade=CascadeType.MERGE)
    private List<CohortListDataDiscriminatorEntity> cohortListDataDiscriminators = new ArrayList<CohortListDataDiscriminatorEntity>();

    @Basic
    @Column(name="activity_date", columnDefinition="datetime", nullable=false)
    private Date activityDate = new Date();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CohortListEntity getCohortList() {
        return cohortList;
    }

    public void setCohortList(CohortListEntity cohortList) {
        this.cohortList = cohortList;
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

    public List<CohortListDataDiscriminatorEntity> getCohortListDataDiscriminators() {
        return cohortListDataDiscriminators;
    }

    public void setCohortListDataDiscriminators(List<CohortListDataDiscriminatorEntity> cohortListDataDiscriminators) {
        this.cohortListDataDiscriminators = cohortListDataDiscriminators;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }
}