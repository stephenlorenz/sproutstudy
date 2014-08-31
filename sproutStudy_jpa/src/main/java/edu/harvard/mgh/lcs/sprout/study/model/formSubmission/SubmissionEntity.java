package edu.harvard.mgh.lcs.sprout.study.model.formSubmission;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name=SubmissionEntity.FIND_BY_INSTANCE_ID, query="FROM SubmissionEntity WHERE instanceId = :instanceId"),
	@NamedQuery(name=SubmissionEntity.FIND_BY_PUBLICATION_KEYS, query="FROM SubmissionEntity WHERE publicationKey IN :publicationKeys AND status.mutable IN :mutableInd"),
	@NamedQuery(name=SubmissionEntity.FIND_BY_TRACKING_CODE, query="FROM SubmissionEntity WHERE trackingCode = :trackingCode"),
	@NamedQuery(name=SubmissionEntity.FIND_BY_KEYS, query="FROM SubmissionEntity WHERE instanceId = :instanceId AND deliveryKey = :deliveryKey AND publicationKey = :publicationKey")
})
@Entity
@Table(schema="dbo", name="submission")
public class SubmissionEntity implements Serializable {

	private static final long serialVersionUID = 8945920485092471858L;
	
	public static final String FIND_BY_INSTANCE_ID = "SubmissionEntity.FindByInstanceId";
	public static final String FIND_BY_PUBLICATION_KEYS = "SubmissionEntity.FindByPublicationKeys";
	public static final String FIND_BY_TRACKING_CODE = "SubmissionEntity.FindByTrackingCode";
	public static final String FIND_BY_KEYS = "SubmissionEntity.FindByKeys";

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
	
	@Basic
    @Column(name="activity_date", columnDefinition="datetime", nullable=false)
    private Date activityDate = new Date();

    @Basic
    @Column(name="publication_key", nullable=false, length=50)
    private String publicationKey;

    @Basic
    @Column(name="instance_id", nullable=false, length=50)
    private String instanceId;

    @Basic
    @Column(name="delivery_key", nullable=true, length=50)
    private String deliveryKey;

    @Basic
    @Column(name="form_title", nullable=false, length=500)
    private String formTitle;
    
    @Basic
    @Column(name="form_description", nullable=false)
    private String formDescription;
    
    @Basic
    @Column(name="submission_data", nullable=false)
    private String submissionData;

    @Basic
    @Column(name="tracking_code", nullable=false)
    private String trackingCode;

    @Basic
    @Column(name="submission_date", columnDefinition="datetime", nullable=false)
    private Date submissionDate;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="status", nullable=false)
    private VSubmissionStatusEntity status;
    
    @OneToMany(targetEntity=IdentifierEntity.class, mappedBy="submission", cascade=CascadeType.MERGE)
    private Set<IdentifierEntity> identifiers = new HashSet<IdentifierEntity>();

    @OneToMany(targetEntity=AssertionEntity.class, mappedBy="submission", cascade=CascadeType.MERGE)
    @OrderBy("positionNo ASC")
    private Set<AssertionEntity> assertions = new HashSet<AssertionEntity>();

    @OneToMany(targetEntity=ParameterEntity.class, mappedBy="submission", cascade=CascadeType.MERGE)
    private Set<ParameterEntity> parameters = new HashSet<ParameterEntity>();

    @OneToMany(targetEntity=LogEntity.class, mappedBy="submission", cascade=CascadeType.MERGE)
    private Set<LogEntity> logs = new HashSet<LogEntity>();

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

	public String getPublicationKey() {
		return publicationKey;
	}

	public void setPublicationKey(String publicationKey) {
		this.publicationKey = publicationKey;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getFormTitle() {
		return formTitle;
	}

	public void setFormTitle(String formTitle) {
		this.formTitle = formTitle;
	}

	public String getFormDescription() {
		return formDescription;
	}

	public void setFormDescription(String formDescription) {
		this.formDescription = formDescription;
	}

	public VSubmissionStatusEntity getStatus() {
		return status;
	}

	public void setStatus(VSubmissionStatusEntity status) {
		this.status = status;
	}


	public Set<AssertionEntity> getAssertions() {
		return assertions;
	}

	public void setAssertions(Set<AssertionEntity> assertions) {
		this.assertions = assertions;
	}

	public Set<IdentifierEntity> getIdentifiers() {
		return identifiers;
	}

	public void setIdentifiers(Set<IdentifierEntity> identifiers) {
		this.identifiers = identifiers;
	}

	public String getDeliveryKey() {
		return deliveryKey;
	}

	public void setDeliveryKey(String deliveryKey) {
		this.deliveryKey = deliveryKey;
	}

	public Set<ParameterEntity> getParameters() {
		return parameters;
	}

	public void setParameters(Set<ParameterEntity> parameters) {
		this.parameters = parameters;
	}

	public Date getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}

	public String getSubmissionData() {
		return submissionData;
	}

	public void setSubmissionData(String submissionData) {
		this.submissionData = submissionData;
	}


    public Set<LogEntity> getLogs() {
        return logs;
    }

    public void setLogs(Set<LogEntity> logs) {
        this.logs = logs;
    }

    public String getTrackingCode() {
        return trackingCode;
    }

    public void setTrackingCode(String trackingCode) {
        this.trackingCode = trackingCode;
    }
}