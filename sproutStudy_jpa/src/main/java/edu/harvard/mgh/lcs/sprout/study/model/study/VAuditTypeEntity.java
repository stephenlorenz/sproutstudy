package edu.harvard.mgh.lcs.sprout.study.model.study;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@Entity
@Table(schema="dbo", name="v_audit_type")
@NamedQueries({
	@NamedQuery(name = VAuditTypeEntity.BY_CODE, query = "FROM VAuditTypeEntity WHERE UPPER(code) = UPPER(:code)"),
	@NamedQuery(name = VAuditTypeEntity.ALL_AUDIT_TYPES, query = "FROM VAuditTypeEntity")
})
public class VAuditTypeEntity implements Serializable {

	private static final long serialVersionUID = 4772162010766307373L;

	public static final String BY_CODE = "VAuditTypeEntity.byCode";
	public static final String ALL_AUDIT_TYPES = "VAuditTypeEntity.all";

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
    @Column(name="activity_date", columnDefinition="datetime", nullable=false)
    private Date activityDate;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="category", nullable=false)
    private VAuditCategoryEntity category;

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

	public Date getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

	public VAuditCategoryEntity getCategory() {
		return category;
	}

	public void setCategory(VAuditCategoryEntity category) {
		this.category = category;
	}

}