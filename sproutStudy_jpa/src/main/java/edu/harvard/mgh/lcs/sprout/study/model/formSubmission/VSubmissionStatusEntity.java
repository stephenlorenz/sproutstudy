package edu.harvard.mgh.lcs.sprout.study.model.formSubmission;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@Entity
@Table(schema="dbo", name="v_submission_status")
@NamedQueries({
    @NamedQuery(name=VSubmissionStatusEntity.ALL, query="FROM VSubmissionStatusEntity"),
    @NamedQuery(name=VSubmissionStatusEntity.FIND_BY_CODE, query="FROM VSubmissionStatusEntity WHERE code = :code")
})
public class VSubmissionStatusEntity implements Serializable {

	private static final long serialVersionUID = 4054147250959530201L;

    public static final String ALL = "VSubmissionStatusEntity.all";
    public static final String FIND_BY_CODE = "VSubmissionStatusEntity.findByCode";

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(nullable=false, length=25)
    private String code;

    @Basic
    @Column(nullable=false, length=250)
    private String description;

    @Basic
    @Column(name="mutable_ind", nullable=false)
    private boolean mutable;

	@Basic
    @Column(name="activity_date", columnDefinition="datetime", nullable=false)
    private Date activityDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isMutable() {
		return mutable;
	}

	public void setMutable(boolean mutable) {
		this.mutable = mutable;
	}

	public Date getActivityDate() {
		return activityDate = new Date();
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

}