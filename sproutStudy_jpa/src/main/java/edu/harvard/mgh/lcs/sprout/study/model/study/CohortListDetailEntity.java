package edu.harvard.mgh.lcs.sprout.study.model.study;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema="dbo", name="cohort_list_detail")
@NamedQueries({
        @NamedQuery(name = CohortListDetailEntity.BY_KEY, query = "FROM CohortListDetailEntity WHERE key = :key")
})
public class CohortListDetailEntity implements Serializable {

    public static final String BY_KEY = "CohortListDetailEntity.byKey";

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="cohort_list_id")
    private CohortListEntity cohortList;

    @OneToMany(targetEntity=CohortListDetailDataEntity.class, mappedBy="cohortListDetail", cascade=CascadeType.MERGE)
    private Set<CohortListDetailDataEntity> cohortListDetailData = new HashSet<CohortListDetailDataEntity>();

    @Basic
    @Column(name="name")
    private String name;

    @Basic
    @Column(name="description")
    private String description;

    @Basic
    @Column(name="detail_key")
    private String key;

    @Basic
    @Column(name="required_ind")
    private boolean required = false;

    @Basic
    @Column(name="active_ind")
    private boolean active = false;

    @Basic
    @Column(name="activity_date", columnDefinition="datetime", nullable=false)
    private Date activityDate = new Date();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CohortListEntity getCohortList() {
        return cohortList;
    }

    public void setCohortList(CohortListEntity cohortList) {
        this.cohortList = cohortList;
    }

    public Set<CohortListDetailDataEntity> getCohortListDetailData() {
        return cohortListDetailData;
    }

    public void setCohortListDetailData(Set<CohortListDetailDataEntity> cohortListDetailData) {
        this.cohortListDetailData = cohortListDetailData;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public Boolean getActive() {
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