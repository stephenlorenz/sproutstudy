package edu.harvard.mgh.lcs.sprout.study.model.study;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(schema="dbo", name="cohort_list_detail_data")
@NamedQueries({
        @NamedQuery(name = CohortListDetailDataEntity.BY_KEY, query = "FROM CohortListDetailDataEntity WHERE cohortListData = :cohortListData AND cohortListDetail = :cohortListDetail")
})
public class CohortListDetailDataEntity implements Serializable {

    public static final String BY_KEY = "CohortListDetailDataEntity.byKey";

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="cohort_list_data_id")
    private CohortListDataEntity cohortListData;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="cohort_list_detail_id")
    private CohortListDetailEntity cohortListDetail;

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

    public CohortListDataEntity getCohortListData() {
        return cohortListData;
    }

    public void setCohortListData(CohortListDataEntity cohortListData) {
        this.cohortListData = cohortListData;
    }

    public CohortListDetailEntity getCohortListDetail() {
        return cohortListDetail;
    }

    public void setCohortListDetail(CohortListDetailEntity cohortListDetail) {
        this.cohortListDetail = cohortListDetail;
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