package edu.harvard.mgh.lcs.sprout.study.model.transform;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema="dbo", name="template_clone")
@NamedQueries({
	@NamedQuery(name = TemplateCloneEntity.BY_INSTANCE_ID, query = "FROM TemplateCloneEntity WHERE instanceId = :instanceId AND active = TRUE")
})
public class TemplateCloneEntity implements Serializable {

	public static final String BY_INSTANCE_ID = "TemplateCloneEntity.byInstanceId";

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="template_master_id")
    private TemplateMasterEntity masterTemplate;

    @Basic
    @Column(name="template")
    private String template;

    @Basic
    @Column(name="instance_id")
    private String instanceId;

    @Basic
    @Column(name="template_key")
    private String key;

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

    public TemplateMasterEntity getMasterTemplate() {
        return masterTemplate;
    }

    public void setMasterTemplate(TemplateMasterEntity masterTemplate) {
        this.masterTemplate = masterTemplate;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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