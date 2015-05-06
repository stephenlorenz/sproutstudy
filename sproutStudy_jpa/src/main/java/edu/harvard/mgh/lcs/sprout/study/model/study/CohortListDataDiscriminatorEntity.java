package edu.harvard.mgh.lcs.sprout.study.model.study;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(schema="dbo", name="cohort_list_data_discriminator")
public class CohortListDataDiscriminatorEntity implements Serializable {

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="cohort_list_data_id")
    private CohortListDataEntity cohortListData;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="cohort_list_discriminator_id")
    private CohortListDiscriminatorEntity cohortListDiscriminator;

    @Column
    private String value;

    @Basic
    @Column(name="activity_date", columnDefinition="datetime", nullable=false)
    private Date activityDate = new Date();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CohortListDataEntity getCohortListData() {
        return cohortListData;
    }

    public void setCohortListData(CohortListDataEntity cohortListData) {
        this.cohortListData = cohortListData;
    }

    public CohortListDiscriminatorEntity getCohortListDiscriminator() {
        return cohortListDiscriminator;
    }

    public void setCohortListDiscriminator(CohortListDiscriminatorEntity cohortListDiscriminator) {
        this.cohortListDiscriminator = cohortListDiscriminator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }
}