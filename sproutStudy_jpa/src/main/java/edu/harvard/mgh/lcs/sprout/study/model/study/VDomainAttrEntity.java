package edu.harvard.mgh.lcs.sprout.study.model.study;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema="dbo", name="v_users_preference")
@NamedQueries({
    @NamedQuery(name= VDomainAttrEntity.ALL, query="FROM VDomainAttrEntity"),
    @NamedQuery(name= VDomainAttrEntity.FIND_BY_CODE, query="FROM VDomainAttrEntity WHERE code = :code")
})
public class VDomainAttrEntity implements Serializable {

	private static final long serialVersionUID = 4054147250959530201L;

    public static final String ALL = "VDomainAttrEntity.all";
    public static final String FIND_BY_CODE = "VDomainAttrEntity.findByCode";

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(targetEntity=DomainAttrEntity.class, mappedBy="domainAttr", cascade=CascadeType.MERGE)
    private Set<DomainAttrEntity> domainAttrs = new HashSet<DomainAttrEntity>();

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