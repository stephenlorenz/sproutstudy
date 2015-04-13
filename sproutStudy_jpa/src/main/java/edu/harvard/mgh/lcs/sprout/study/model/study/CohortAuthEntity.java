package edu.harvard.mgh.lcs.sprout.study.model.study;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema="dbo", name="cohort_auth")
@NamedQueries({
        @NamedQuery(name = CohortAuthEntity.BY_USERNAME_AND_COHORT_KEY, query = "FROM CohortAuthEntity WHERE user.username = :username AND active = TRUE AND cohort.cohortKey = :cohortKey"),
        @NamedQuery(name = CohortAuthEntity.MANAGER_BY_USERNAME_AND_COHORT_KEY, query = "FROM CohortAuthEntity WHERE user.username = :username AND active = TRUE AND manager = TRUE AND cohort.cohortKey = :cohortKey")
})
public class CohortAuthEntity implements Serializable {

    public static final String BY_USERNAME_AND_COHORT_KEY = "CohortAuthEntity.byUsernameAndCohortKey";
    public static final String MANAGER_BY_USERNAME_AND_COHORT_KEY = "CohortAuthEntity.managerByUsernameAndCohortKey";
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="cohort_id")
    private CohortEntity cohort;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="users_id")
    private UserEntity user;

    @Basic
    @Column(name="active_ind")
    private boolean active = false;

    @Basic
    @Column(name="manager_ind")
    private boolean manager = false;

    @Basic
    @Column(name="activity_date", columnDefinition="datetime", nullable=false)
    private Date activityDate = new Date();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public CohortEntity getCohort() {
        return cohort;
    }

    public void setCohort(CohortEntity cohort) {
        this.cohort = cohort;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }
}