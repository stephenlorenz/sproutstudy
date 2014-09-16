package edu.harvard.mgh.lcs.sprout.study.model.study;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@Entity
@Table(schema="dbo", name="audit")
@NamedQueries({
	@NamedQuery(name = AuditEntity.BY_ID_AND_USER, query = "FROM AuditEntity WHERE id = :id AND user = :user"),
	@NamedQuery(name = AuditEntity.MOVE_USER, query = "UPDATE AuditEntity SET user = :userNew WHERE user = :userOld")
})
public class AuditEntity implements Serializable {

	private static final long serialVersionUID = -2666317588412710545L;

	public static final String BY_ID_AND_USER = "AuditEntity.byIdAndUser";
	public static final String MOVE_USER = "AuditEntity.moveUser";

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="users_id")
    private UserEntity user;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="audit_type", nullable=false)
    private VAuditTypeEntity auditType;

    @Basic
    @Column(name="title")
    private String title;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="cohort_id")
    private CohortEntity cohort;

    @Basic
    @Column(name="subject_schema")
    private String subjectSchema;

    @Basic
    @Column(name="subject_id")
    private String subjectId;

    @Basic
    @Column(name="activity_date", columnDefinition="datetime", nullable=false)
    private Date activityDate = new Date();

    @OneToMany(targetEntity=AuditMessageEntity.class, mappedBy="audit", cascade=CascadeType.MERGE)
    @OrderBy("id ASC")
    private List<AuditMessageEntity> messages = new ArrayList<AuditMessageEntity>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public VAuditTypeEntity getAuditType() {
		return auditType;
	}

	public void setAuditType(VAuditTypeEntity auditType) {
		this.auditType = auditType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

    public CohortEntity getCohort() {
        return cohort;
    }

    public void setCohort(CohortEntity cohort) {
        this.cohort = cohort;
    }

    public String getSubjectSchema() {
        return subjectSchema;
    }

    public void setSubjectSchema(String subjectSchema) {
        this.subjectSchema = subjectSchema;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public Date getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

	public List<AuditMessageEntity> getMessages() {
		return messages;
	}

	public void setMessages(List<AuditMessageEntity> messages) {
		this.messages = messages;
	}



}