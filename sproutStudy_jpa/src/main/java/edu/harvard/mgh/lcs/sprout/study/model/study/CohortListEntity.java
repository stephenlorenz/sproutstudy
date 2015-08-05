package edu.harvard.mgh.lcs.sprout.study.model.study;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(schema="dbo", name="cohort_list")
@NamedQueries({
        @NamedQuery(name = CohortListEntity.BY_LIST_KEY, query = "FROM CohortListEntity WHERE key = :key")
})
public class CohortListEntity implements Serializable {

    public static final String BY_LIST_KEY = "CohortListEntity.byKey";

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
    @JoinColumn(name="cohort_id")
    private CohortEntity cohort;

    @OneToMany(fetch = FetchType.EAGER, targetEntity=CohortListDataEntity.class, mappedBy="cohortList", cascade=CascadeType.MERGE)
    private Set<CohortListDataEntity> cohortListData = new HashSet<CohortListDataEntity>();

    @OneToMany(fetch = FetchType.EAGER, targetEntity=CohortListDetailEntity.class, mappedBy="cohortList", cascade=CascadeType.MERGE)
    @OrderBy("id asc")
    private List<CohortListDetailEntity> cohortListDetail = new ArrayList<CohortListDetailEntity>();

    @Basic
    @Column(name="name")
    private String name;

    @Basic
    @Column(name="description")
    private String description;

    @Basic
    @Column(name="name_column_title")
    private String nameColumnTitle;

    @Basic
    @Column(name="value_column_title")
    private String valueColumnTitle;

    @Basic
    @Column(name="list_key")
    private String key;

    @Basic
    @Column(name="active_ind")
    private boolean active = false;

    @Basic
    @Column(name="public_ind")
    private boolean publicInd = false;

    @Basic
    @Column(name="activity_date", columnDefinition="datetime", nullable=false)
    private Date activityDate = new Date();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<CohortListDataEntity> getCohortListData() {
        return cohortListData;
    }

    public void setCohortListData(Set<CohortListDataEntity> cohortListData) {
        this.cohortListData = cohortListData;
    }

    public List<CohortListDetailEntity> getCohortListDetail() {
        return cohortListDetail;
    }

    public void setCohortListDetail(List<CohortListDetailEntity> cohortListDetail) {
        this.cohortListDetail = cohortListDetail;
    }

    public CohortEntity getCohort() {
        return cohort;
    }

    public void setCohort(CohortEntity cohort) {
        this.cohort = cohort;
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

    public String getNameColumnTitle() {
        return nameColumnTitle;
    }

    public void setNameColumnTitle(String nameColumnTitle) {
        this.nameColumnTitle = nameColumnTitle;
    }

    public String getValueColumnTitle() {
        return valueColumnTitle;
    }

    public void setValueColumnTitle(String valueColumnTitle) {
        this.valueColumnTitle = valueColumnTitle;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isPublicInd() {
        return publicInd;
    }

    public void setPublicInd(boolean publicInd) {
        this.publicInd = publicInd;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }
}