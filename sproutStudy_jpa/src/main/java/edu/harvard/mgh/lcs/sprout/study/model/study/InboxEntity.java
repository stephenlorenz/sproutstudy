package edu.harvard.mgh.lcs.sprout.study.model.study;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@NamedQueries({
        @NamedQuery(name=InboxEntity.GET_USER_INBOX, query="FROM InboxEntity WHERE recipient = :recipient AND status.mutable = true ORDER BY activityDate DESC"),
        @NamedQuery(name=InboxEntity.GET_USER_INBOX_BY_INSTANCE_ID, query="FROM InboxEntity WHERE instanceId = :instanceId AND status.mutable = true")
})
@Entity
@Table(schema="dbo", name="inbox")
public class InboxEntity implements Serializable {

    public static final String GET_USER_INBOX = "InboxEntity.getUserInbox";
    public static final String GET_USER_INBOX_BY_INSTANCE_ID = "InboxEntity.getUserInboxByInstanceId";

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="to_id")
    private UserEntity recipient;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="from_id")
    private UserEntity sender;

    @Basic
    @Column(name="subject")
    private String subject;

    @Basic
    @Column(name="body")
    private String body;

    @Basic
    @Column(name="subject_name")
    private String subjectName;

    @Basic
    @Column(name="subject_id")
    private String subjectId;

    @Basic
    @Column(name="instance_id")
    private String instanceId;

    @Basic
    @Column(name="form")
    private String form;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="cohort_id")
    private CohortEntity cohort;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="status")
    private VInboxStatusEntity status;

    @Basic
    @Column(name="activity_date", columnDefinition="datetime", nullable=false)
    private Date activityDate = new Date();

    @Basic
    @Column(name="delivery_date", columnDefinition="datetime", nullable=false)
    private Date deliveryDate = new Date();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public UserEntity getRecipient() {
        return recipient;
    }

    public void setRecipient(UserEntity recipient) {
        this.recipient = recipient;
    }

    public UserEntity getSender() {
        return sender;
    }

    public void setSender(UserEntity sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public CohortEntity getCohort() {
        return cohort;
    }

    public void setCohort(CohortEntity cohort) {
        this.cohort = cohort;
    }

    public VInboxStatusEntity getStatus() {
        return status;
    }

    public void setStatus(VInboxStatusEntity status) {
        this.status = status;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}