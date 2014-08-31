package edu.harvard.mgh.lcs.sprout.study.model.study;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(schema="dbo", name="users")
@NamedQueries({
	@NamedQuery(name = UserEntity.BY_USERNAME, query = "FROM UserEntity WHERE username = :username AND active = TRUE")
})
public class UserEntity implements Serializable {

	public static final String BY_USERNAME = "UserEntity.byUsername";

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name="username")
    private String username;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="domain")
    private DomainEntity domain;

    @OneToMany(targetEntity=UsersPreferenceEntity.class, mappedBy="user", cascade=CascadeType.MERGE)
    private Set<UsersPreferenceEntity> preferences = new HashSet<UsersPreferenceEntity>();

    @OneToMany(targetEntity=CohortAuthEntity.class, mappedBy="user", cascade=CascadeType.MERGE)
    private Set<CohortAuthEntity> cohortAuthorizations = new HashSet<CohortAuthEntity>();

    @Basic
    @Column(name="password")
    private String password;

    @Basic
    @Column(name="salt")
    private Integer salt;

    @Basic
    @Column(name="first_name")
    private String firstName;

    @Basic
    @Column(name="last_name")
    private String lastName;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<CohortAuthEntity> getCohortAuthorizations() {
        return cohortAuthorizations;
    }

    public void setCohortAuthorizations(Set<CohortAuthEntity> cohortAuthorizations) {
        this.cohortAuthorizations = cohortAuthorizations;
    }

    public DomainEntity getDomain() {
        return domain;
    }

    public void setDomain(DomainEntity domain) {
        this.domain = domain;
    }

    public Set<UsersPreferenceEntity> getPreferences() {
        return preferences;
    }

    public void setPreferences(Set<UsersPreferenceEntity> preferences) {
        this.preferences = preferences;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSalt() {
        return salt;
    }

    public void setSalt(Integer salt) {
        this.salt = salt;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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