package edu.harvard.mgh.lcs.sprout.study.model.study;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema="dbo", name="cohort_list_data")
@NamedQueries({
        @NamedQuery(name = CohortListDataEntity.BY_KEY, query = "FROM CohortListDataEntity WHERE key = :key"),
        @NamedQuery(name = CohortListDataEntity.BY_VALUE, query = "FROM CohortListDataEntity WHERE value = :value AND cohortList = :cohortList AND active = TRUE")
})
public class CohortListDataEntity implements Serializable {

    public static final String BY_KEY = "CohortListDataEntity.byKey";
    public static final String BY_VALUE = "CohortListDataEntity.byValue";

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="cohort_list_id")
    private CohortListEntity cohortList;

    @OneToMany(targetEntity=CohortListDetailDataEntity.class, mappedBy="cohortListData", cascade=CascadeType.MERGE)
    private Set<CohortListDetailDataEntity> cohortListDetailData = new HashSet<CohortListDetailDataEntity>();

    @Basic
    @Column(name="name")
    private String name;

    @Basic
    @Column(name="value")
    private String value;

    @Basic
    @Column(name="data_key")
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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
}