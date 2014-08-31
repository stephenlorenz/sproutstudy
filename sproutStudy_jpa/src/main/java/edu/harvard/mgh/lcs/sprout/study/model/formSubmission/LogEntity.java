package edu.harvard.mgh.lcs.sprout.study.model.formSubmission;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(schema="dbo", name="log")
public class LogEntity implements Serializable {
	
	private static final long serialVersionUID = 7064789187969863650L;

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="submission_id")
    private SubmissionEntity submission;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="status_id", nullable=false)
    private VSubmissionStatusEntity status;

	@Basic
    @Column(name="message", nullable=true)
    private String message;

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

    public VSubmissionStatusEntity getStatus() {
        return status;
    }

    public void setStatus(VSubmissionStatusEntity status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }
}