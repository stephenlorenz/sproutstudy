package edu.harvard.mgh.lcs.sprout.study.model.study;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(schema="dbo", name="cohort")
@NamedQueries({
	@NamedQuery(name = CohortEntity.BY_COHORT_NAME, query = "FROM CohortEntity WHERE name = :name"),
	@NamedQuery(name = CohortEntity.BY_COHORT_KEY_AND_AUTH, query = "SELECT c FROM CohortEntity c, IN (c.cohortAuthorizations) AS a WHERE c.cohortKey = :key AND a.user = :user"),
	@NamedQuery(name = CohortEntity.BY_COHORT_KEY, query = "FROM CohortEntity WHERE cohortKey = :key")
})
public class CohortEntity implements Serializable {

	public static final String BY_COHORT_NAME = "CohortEntity.byName";
	public static final String BY_COHORT_KEY_AND_AUTH = "CohortEntity.byKeyAndAuth";
	public static final String BY_COHORT_KEY = "CohortEntity.byKey";

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(targetEntity=CohortAttrEntity.class, mappedBy="cohort", cascade=CascadeType.MERGE)
    private Set<CohortAttrEntity> cohortAttributes = new HashSet<CohortAttrEntity>();

    @OneToMany(targetEntity=CohortAuthEntity.class, mappedBy="cohort", cascade=CascadeType.MERGE)
    private List<CohortAuthEntity> cohortAuthorizations = new ArrayList<CohortAuthEntity>();

    @OneToMany(targetEntity=CohortFormEntity.class, mappedBy="cohort", cascade=CascadeType.MERGE)
    @OrderBy("id ASC")
    private List<CohortFormEntity> cohortForms = new ArrayList<CohortFormEntity>();

    @OneToMany(targetEntity=InboxEntity.class, mappedBy="cohort", cascade=CascadeType.MERGE)
    @OrderBy("id ASC")
    private List<InboxEntity> inboxMessages = new ArrayList<InboxEntity>();

    @Basic
    @Column(name="name")
    private String name;

    @Basic
    @Column(name="description")
    private String description;

    @Basic
    @Column(name="cohort_key")
    private String cohortKey;

    @Basic
    @Column(name="active_ind")
    private boolean active;

    @Basic
    @Column(name="activity_date", columnDefinition="datetime", nullable=false)
    private Date activityDate = new Date();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public Set<CohortAttrEntity> getCohortAttributes() {
        return cohortAttributes;
    }

    public void setCohortAttributes(Set<CohortAttrEntity> cohortAttributes) {
        this.cohortAttributes = cohortAttributes;
    }

    public List<CohortAuthEntity> getCohortAuthorizations() {
        return cohortAuthorizations;
    }

    public void setCohortAuthorizations(List<CohortAuthEntity> cohortAuthorizations) {
        this.cohortAuthorizations = cohortAuthorizations;
    }

    public List<CohortFormEntity> getCohortForms() {
        return cohortForms;
    }

    public void setCohortForms(List<CohortFormEntity> cohortForms) {
        this.cohortForms = cohortForms;
    }

    public List<InboxEntity> getInboxMessages() {
        return inboxMessages;
    }

    public void setInboxMessages(List<InboxEntity> inboxMessages) {
        this.inboxMessages = inboxMessages;
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

    public String getCohortKey() {
        return cohortKey;
    }

    public void setCohortKey(String cohortKey) {
        this.cohortKey = cohortKey;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }


}