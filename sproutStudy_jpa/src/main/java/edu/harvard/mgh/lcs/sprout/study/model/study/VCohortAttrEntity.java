package edu.harvard.mgh.lcs.sprout.study.model.study;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(schema="dbo", name="v_cohort_attr")
@NamedQueries({
    @NamedQuery(name= VCohortAttrEntity.ALL, query="FROM VCohortAttrEntity"),
    @NamedQuery(name= VCohortAttrEntity.FIND_BY_CODE, query="FROM VCohortAttrEntity WHERE code = :code")
})
public class VCohortAttrEntity implements Serializable {

	private static final long serialVersionUID = 4054147250959530201L;

    public static final String ALL = "VCohortAttrEntity.all";
    public static final String FIND_BY_CODE = "VCohortAttrEntity.findByCode";

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(nullable=false, length=50)
    private String code;

    @Basic
    @Column(nullable=false, length=500)
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
		return activityDate = new Date();
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

}