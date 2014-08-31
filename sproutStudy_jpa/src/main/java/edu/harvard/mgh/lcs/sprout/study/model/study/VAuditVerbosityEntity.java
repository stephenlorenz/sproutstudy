package edu.harvard.mgh.lcs.sprout.study.model.study;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@Entity
@Table(schema="dbo", name="v_audit_verbosity")
@NamedQueries({
	@NamedQuery(name = VAuditVerbosityEntity.ALL_VERBOSITY_LEVELS, query = "FROM VAuditVerbosityEntity")
})
public class VAuditVerbosityEntity implements Serializable {

	private static final long serialVersionUID = 4242395163265309293L;

	public static final String ALL_VERBOSITY_LEVELS = "VAuditVerbosityEntity.all";

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

}