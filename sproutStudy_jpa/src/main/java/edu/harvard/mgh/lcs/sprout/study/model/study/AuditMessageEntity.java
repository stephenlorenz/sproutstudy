package edu.harvard.mgh.lcs.sprout.study.model.study;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@Entity
@Table(schema="dbo", name="audit_message")
public class AuditMessageEntity implements Serializable {

	private static final long serialVersionUID = -2666317588412710545L;

	public AuditMessageEntity() {
		super();
	}

	public AuditMessageEntity(AuditEntity auditEntity) {
		super();
		this.audit = auditEntity;
	}

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="audit_id", nullable=false)
    private AuditEntity audit;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="verbosity", nullable=false)
    private VAuditVerbosityEntity verbosity;

    @Basic
    @Column(name="message")
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

	public AuditEntity getAudit() {
		return audit;
	}

	public void setAudit(AuditEntity audit) {
		this.audit = audit;
	}

	public VAuditVerbosityEntity getVerbosity() {
		return verbosity;
	}

	public void setVerbosity(VAuditVerbosityEntity verbosity) {
		this.verbosity = verbosity;
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