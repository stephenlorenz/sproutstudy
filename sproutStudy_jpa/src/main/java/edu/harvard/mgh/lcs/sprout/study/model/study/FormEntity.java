package edu.harvard.mgh.lcs.sprout.study.model.study;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(schema="dbo", name="form")
@NamedQueries({
    @NamedQuery(name= FormEntity.ALL, query="FROM FormEntity"),
    @NamedQuery(name= FormEntity.FIND_BY_NAME, query="FROM FormEntity WHERE name = :name")
})
public class FormEntity implements Serializable {

    public static final String ALL = "FormEntity.all";
    public static final String FIND_BY_NAME = "FormEntity.findByCode";

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(nullable=false)
    private String name;

    @Basic
    @Column(name="publication_key", nullable=false)
    private String publicationKey;

    @Basic
    @Column(name="demographic_ind", nullable=false)
    private Boolean demographic;

    @Basic
    @Column(name="active_ind", nullable=false)
    private Boolean active;

	@Basic
    @Column(name="activity_date", columnDefinition="datetime", nullable=false)
    private Date activityDate;

    @OneToMany(targetEntity=CohortFormEntity.class, mappedBy="form", cascade=CascadeType.MERGE)
    private List<CohortFormEntity> cohortForms = new ArrayList<CohortFormEntity>();

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublicationKey() {
        return publicationKey;
    }

    public void setPublicationKey(String publicationKey) {
        this.publicationKey = publicationKey;
    }

    public Boolean getDemographic() {
        return demographic;
    }

    public void setDemographic(Boolean demographic) {
        this.demographic = demographic;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    public List<CohortFormEntity> getCohortForms() {
        return cohortForms;
    }

    public void setCohortForms(List<CohortFormEntity> cohortForms) {
        this.cohortForms = cohortForms;
    }
}