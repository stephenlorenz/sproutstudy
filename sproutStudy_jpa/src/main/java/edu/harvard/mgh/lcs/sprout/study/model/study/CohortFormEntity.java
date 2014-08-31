package edu.harvard.mgh.lcs.sprout.study.model.study;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(schema="dbo", name="cohort_form")
public class CohortFormEntity implements Serializable {

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="cohort_id")
    private CohortEntity cohort;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="form_id")
    private FormEntity form;

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

    public FormEntity getForm() {
        return form;
    }

    public void setForm(FormEntity form) {
        this.form = form;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }
}