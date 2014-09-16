package edu.harvard.mgh.lcs.sprout.study.model.study;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(schema="dbo", name="v_inbox_status")
@NamedQueries({
	@NamedQuery(name = VInboxStatusEntity.BY_CODE, query = "FROM VInboxStatusEntity WHERE UPPER(code) = UPPER(:code)"),
	@NamedQuery(name = VInboxStatusEntity.ALL_STATUSES, query = "FROM VAuditTypeEntity")
})
public class VInboxStatusEntity implements Serializable {

	private static final long serialVersionUID = 4772162010766307373L;

	public static final String BY_CODE = "VInboxStatusEntity.byCode";
	public static final String ALL_STATUSES = "VInboxStatusEntity.all";

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(nullable=false)
    private String code;

    @Basic
    @Column(nullable=false)
    private String description;

    @Basic
    @Column(name="mutable", nullable=false)
    private Boolean mutable;

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

    public Boolean isMutable() {
        return mutable;
    }

    public void setMutable(Boolean mutable) {
        this.mutable = mutable;
    }

    public Date getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

}