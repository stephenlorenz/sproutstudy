package edu.harvard.mgh.lcs.sprout.study.model.study;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema="dbo", name="domain")
@NamedQueries({
	@NamedQuery(name = DomainEntity.BY_NAME, query = "FROM DomainEntity WHERE name = :name")
})
public class DomainEntity implements Serializable {

	public static final String BY_NAME = "DomainEntity.byUsername";

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(targetEntity=DomainAttrEntity.class, mappedBy="domain", cascade=CascadeType.MERGE)
    private Set<DomainAttrEntity> domainAttrs = new HashSet<DomainAttrEntity>();

    @Basic
    @Column(name="name")
    private String name;

    @Basic
    @Column(name="description")
    private String description;

    @Basic
    @Column(name="activity_date", columnDefinition="datetime", nullable=false)
    private Date activityDate = new Date();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public Set<DomainAttrEntity> getDomainAttrs() {
        return domainAttrs;
    }

    public void setDomainAttrs(Set<DomainAttrEntity> domainAttrs) {
        this.domainAttrs = domainAttrs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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