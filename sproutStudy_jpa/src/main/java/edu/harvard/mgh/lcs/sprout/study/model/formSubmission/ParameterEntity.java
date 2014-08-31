package edu.harvard.mgh.lcs.sprout.study.model.formSubmission;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@Entity
@Table(schema="dbo", name="parameter")
public class ParameterEntity implements Serializable {
	
	private static final long serialVersionUID = 4437762704342120528L;

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="submission_id")
    private SubmissionEntity submission;

	@Basic
    @Column(name="name", nullable=false)
    private String name;

	@Basic
    @Column(name="value", nullable=false)
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

	public SubmissionEntity getSubmission() {
		return submission;
	}

	public void setSubmission(SubmissionEntity submission) {
		this.submission = submission;
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

	public Date getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}



}