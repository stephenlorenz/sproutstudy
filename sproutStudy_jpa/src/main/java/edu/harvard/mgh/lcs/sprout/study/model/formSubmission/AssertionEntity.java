package edu.harvard.mgh.lcs.sprout.study.model.formSubmission;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@Entity
@Table(schema="dbo", name="assertion")
@NamedQueries({
        @NamedQuery(name=AssertionEntity.FIND_MRN_VALUE, query="FROM AssertionEntity WHERE name = 'mrn' AND value LIKE :mrn")
})
public class AssertionEntity implements Serializable {
	
	private static final long serialVersionUID = 7064789187969863650L;

    public static final String FIND_MRN_VALUE = "AssertionEntity.FindByMrnValue";

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
    @Column(name="label", nullable=false)
    private String label;

	@Basic
    @Column(name="variable_name", nullable=false)
    private String variableName;

	@Basic
    @Column(name="search_field", nullable=false)
    private String searchField;

	@Basic
    @Column(name="identifier_ind")
    private boolean identifier;

	@Basic
    @Column(name="activity_date", columnDefinition="datetime", nullable=false)
    private Date activityDate = new Date();

	@Basic
    @Column(name="position_no")
    private int positionNo;
	
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

	public boolean isIdentifier() {
		return identifier;
	}

	public void setIdentifier(boolean identifier) {
		this.identifier = identifier;
	}

	public Date getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public int getPositionNo() {
		return positionNo;
	}

	public void setPositionNo(int positionNo) {
		this.positionNo = positionNo;
	}

	public String getSearchField() {
		return searchField;
	}

	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}



}