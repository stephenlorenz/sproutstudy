package edu.harvard.mgh.lcs.sprout.study.model.study;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema="dbo", name="cohort_attr")
public class CohortAttrEntity implements Serializable {

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="cohort_id")
    private CohortEntity cohort;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="attr_id")
    private VCohortAttrEntity cohortAttr;

    @Basic
    @Column(name="value")
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

    public CohortEntity getCohort() {
        return cohort;
    }

    public void setCohort(CohortEntity cohort) {
        this.cohort = cohort;
    }

    public VCohortAttrEntity getCohortAttr() {
        return cohortAttr;
    }

    public void setCohortAttr(VCohortAttrEntity cohortAttr) {
        this.cohortAttr = cohortAttr;
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