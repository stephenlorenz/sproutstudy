package edu.harvard.mgh.lcs.sprout.study.model.study;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@Entity
@Table(schema="dbo", name="v_audit_category")
public class VAuditCategoryEntity implements Serializable {

	private static final long serialVersionUID = 7902967846126374089L;

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

    @OneToMany(targetEntity=VAuditTypeEntity.class, mappedBy="category", cascade=CascadeType.MERGE)
    private Set<VAuditTypeEntity> auditTypes = new HashSet<VAuditTypeEntity>();

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

	public Set<VAuditTypeEntity> getAuditTypes() {
		return auditTypes;
	}

	public void setAuditTypes(Set<VAuditTypeEntity> auditTypes) {
		this.auditTypes = auditTypes;
	}

}