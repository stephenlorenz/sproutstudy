package edu.harvard.mgh.lcs.sprout.study.model.transform;

import edu.harvard.mgh.lcs.sprout.study.model.study.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(schema="dbo", name="template_master")
@NamedQueries({
	@NamedQuery(name = TemplateMasterEntity.BY_PUBLICATION_KEY, query = "FROM TemplateMasterEntity WHERE publicationKey = :publicationKey AND active = TRUE")
})
public class TemplateMasterEntity implements Serializable {

	public static final String BY_PUBLICATION_KEY = "TemplateMasterEntity.byPublicationKey";

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(targetEntity=TemplateCloneEntity.class, mappedBy="masterTemplate", cascade=CascadeType.MERGE)
    private Set<TemplateCloneEntity> templates = new HashSet<TemplateCloneEntity>();

    @Basic
    @Column(name="template")
    private String template;

    @Basic
    @Column(name="publication_key")
    private String publicationKey;

    @Basic
    @Column(name="template_key")
    private String key;

    @Basic
    @Column(name="translations")
    private String translations;

    @Basic
    @Column(name="active_ind")
    private Boolean active;

    @Basic
    @Column(name="activity_date", columnDefinition="datetime", nullable=false)
    private Date activityDate = new Date();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public Set<TemplateCloneEntity> getTemplates() {
        return templates;
    }

    public void setTemplates(Set<TemplateCloneEntity> templates) {
        this.templates = templates;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getPublicationKey() {
        return publicationKey;
    }

    public void setPublicationKey(String publicationKey) {
        this.publicationKey = publicationKey;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTranslations() {
        return translations;
    }

    public void setTranslations(String translations) {
        this.translations = translations;
    }

    public Boolean isActive() {
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
}