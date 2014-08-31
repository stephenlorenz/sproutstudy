package edu.harvard.mgh.lcs.sprout.study.model.study;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema="dbo", name="domain_attr")
public class DomainAttrEntity implements Serializable {

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="domain_id")
    private DomainEntity domain;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="attr_id")
    private VDomainAttrEntity domainAttr;

    @Basic
    @Column(name="value")
    private String value;

    @Basic
    @Column(name="activity_date", columnDefinition="datetime", nullable=false)
    private Date activityDate = new Date();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public DomainEntity getDomain() {
        return domain;
    }

    public void setDomain(DomainEntity domain) {
        this.domain = domain;
    }

    public VDomainAttrEntity getDomainAttr() {
        return domainAttr;
    }

    public void setDomainAttr(VDomainAttrEntity domainAttr) {
        this.domainAttr = domainAttr;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }
}