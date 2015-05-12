package edu.harvard.mgh.lcs.sprout.study.model.study;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(schema="dbo", name="form")
@NamedQueries({
    @NamedQuery(name= FormEntity.ALL, query="FROM FormEntity"),
    @NamedQuery(name= FormEntity.FIND_BY_NAME, query="FROM FormEntity WHERE name = :name"),
    @NamedQuery(name= FormEntity.FIND_BY_FORM_OR_PUBLICATION_KEY, query="FROM FormEntity WHERE (publicationKey = :publicationKey OR formKey = :formKey)"),
    @NamedQuery(name= FormEntity.FIND_BY_PUBLICATION_KEY, query="FROM FormEntity WHERE publicationKey = :publicationKey"),
    @NamedQuery(name= FormEntity.FIND_BY_FORM_KEY, query="FROM FormEntity WHERE formKey = :formKey")
})
public class FormEntity implements Serializable {

    public static final String ALL = "FormEntity.all";
    public static final String FIND_BY_NAME = "FormEntity.findByCode";
    public static final String FIND_BY_FORM_OR_PUBLICATION_KEY = "FormEntity.findByFormOrPublicationKey";
    public static final String FIND_BY_PUBLICATION_KEY = "FormEntity.findByPublicationKey";
    public static final String FIND_BY_FORM_KEY = "FormEntity.findByFormKey";

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
    @Column(name="form_key")
    private String formKey;

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

    @OneToMany(targetEntity=FormAttrEntity.class, mappedBy="form", cascade=CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<FormAttrEntity> formAttributes = new HashSet<FormAttrEntity>();

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

    public String getFormKey() {
        return formKey;
    }

    public void setFormKey(String formKey) {
        this.formKey = formKey;
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

    public Set<FormAttrEntity> getFormAttributes() {
        return formAttributes;
    }

    public void setFormAttributes(Set<FormAttrEntity> formAttributes) {
        this.formAttributes = formAttributes;
    }
}