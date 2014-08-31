package edu.harvard.mgh.lcs.sprout.study.model.study;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema="dbo", name="users_preference")
public class UsersPreferenceEntity implements Serializable {

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="users_id")
    private UserEntity user;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="preference_id")
    private VUserPreferenceEntity userPreference;

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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public VUserPreferenceEntity getUserPreference() {
        return userPreference;
    }

    public void setUserPreference(VUserPreferenceEntity userPreference) {
        this.userPreference = userPreference;
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