package edu.harvard.mgh.lcs.sprout.study.model.study;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(schema="dbo", name="v_form_attr")
@NamedQueries({
    @NamedQuery(name= VFormAttrEntity.ALL, query="FROM VFormAttrEntity"),
    @NamedQuery(name= VFormAttrEntity.FIND_BY_CODE, query="FROM VFormAttrEntity WHERE code = :code")
})
public class VFormAttrEntity implements Serializable {

    public static final String ALL = "VFormAttrEntity.all";
    public static final String FIND_BY_CODE = "VFormAttrEntity.findByCode";

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