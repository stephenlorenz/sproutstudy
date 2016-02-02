package edu.harvard.mgh.lcs.sprout.study.model.study;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@Entity
@Table(schema="dbo", name="poll_event")
@NamedQueries({
	@NamedQuery(name = PollEventEntity.BY_ID_AND_COHORT, query = "FROM PollEventEntity WHERE id > :id AND cohort = :cohortKey ORDER BY id ASC")
})
public class PollEventEntity implements Serializable {

	public static final String BY_ID_AND_COHORT = "PollEventEntity.byIdAndCohort";

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name="cohort_key")
    private String cohort;

    @Basic
    @Column(name="data")
    private String data;

    @Basic
    @Column(name="activity_date", columnDefinition="datetime", nullable=false)
    private Date activityDate = new Date();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCohort() {
        return cohort;
    }

    public void setCohort(String cohort) {
        this.cohort = cohort;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }
}