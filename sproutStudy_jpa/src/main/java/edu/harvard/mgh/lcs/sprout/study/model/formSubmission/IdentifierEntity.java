package edu.harvard.mgh.lcs.sprout.study.model.formSubmission;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@Entity
@Table(schema="dbo", name="identifier")
@NamedQueries({
        @NamedQuery(name=IdentifierEntity.FIND_SCHEMA_VALUE, query="FROM IdentifierEntity WHERE schema = :schema AND identifier = :identifier")
})
public class IdentifierEntity implements Serializable {
	
	private static final long serialVersionUID = -3256453225781336769L;

    public static final String FIND_SCHEMA_VALUE = "IdentifierEntity.FindBySchemaValue";

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="submission_id")
    private SubmissionEntity submission;

	@Basic
    @Column(name="identifier_schema", nullable=false)
    private String schema;

	@Basic
    @Column(name="identifier_value", nullable=false)
    private String identifier;

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

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public Date getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}


}