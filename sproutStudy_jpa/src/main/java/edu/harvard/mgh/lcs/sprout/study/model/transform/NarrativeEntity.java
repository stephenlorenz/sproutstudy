package edu.harvard.mgh.lcs.sprout.study.model.transform;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(schema="dbo", name="narrative")
@NamedQueries({
	@NamedQuery(name = NarrativeEntity.BY_INSTANCE_ID, query = "FROM NarrativeEntity WHERE instanceId = :instanceId"),
	@NamedQuery(name = NarrativeEntity.BY_NARRATIVE_KEY, query = "FROM NarrativeEntity WHERE key = :narrativeKey")
})
public class NarrativeEntity implements Serializable {

	public static final String BY_INSTANCE_ID = "NarrativeEntity.byInstanceId";
	public static final String BY_NARRATIVE_KEY = "NarrativeEntity.byNarrativeKey";

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name="instance_id")
    private String instanceId;

    @Basic
    @Column(name="narrative_key")
    private String key;

    @OneToMany(targetEntity=NarrativeFormatEntity.class, mappedBy="narrative", cascade=CascadeType.MERGE)
    private Set<NarrativeFormatEntity> formats = new HashSet<NarrativeFormatEntity>();

    @OneToMany(targetEntity=NarrativeModelEntity.class, mappedBy="narrative", cascade=CascadeType.MERGE)
    private List<NarrativeModelEntity> model = new ArrayList<NarrativeModelEntity>();

    @Basic
    @Column(name="activity_date", columnDefinition="datetime", nullable=false)
    private Date activityDate = new Date();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

    public Set<NarrativeFormatEntity> getFormats() {
        return formats;
    }

    public void setFormats(Set<NarrativeFormatEntity> formats) {
        this.formats = formats;
    }

    public List<NarrativeModelEntity> getModel() {
        return model;
    }

    public void setModel(List<NarrativeModelEntity> model) {
        this.model = model;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }
}