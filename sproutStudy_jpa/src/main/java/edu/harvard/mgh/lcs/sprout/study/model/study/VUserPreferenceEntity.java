package edu.harvard.mgh.lcs.sprout.study.model.study;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema="dbo", name="v_users_preference")
@NamedQueries({
    @NamedQuery(name= VUserPreferenceEntity.ALL, query="FROM VUserPreferenceEntity"),
    @NamedQuery(name= VUserPreferenceEntity.FIND_BY_CODE, query="FROM VUserPreferenceEntity WHERE code = :code")
})
public class VUserPreferenceEntity implements Serializable {

	private static final long serialVersionUID = 4054147250959530201L;

    public static final String ALL = "VUserPreferenceEntity.all";
    public static final String FIND_BY_CODE = "VUserPreferenceEntity.findByCode";

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(nullable=false, length=50)
    private String code;

    @Basic
    @Column(nullable=false, length=200)
    private String description;

    @OneToMany(targetEntity=UsersPreferenceEntity.class, mappedBy="userPreference", cascade=CascadeType.MERGE)
    private Set<UsersPreferenceEntity> userPreferences = new HashSet<UsersPreferenceEntity>();

    @Basic
    @Column(name="activity_date", columnDefinition="datetime", nullable=false)
    private Date activityDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    public Set<UsersPreferenceEntity> getUserPreferences() {
        return userPreferences;
    }

    public void setUserPreferences(Set<UsersPreferenceEntity> userPreferences) {
        this.userPreferences = userPreferences;
    }

    public Date getActivityDate() {
		return activityDate = new Date();
	}

	public void setActivityDate(Date activityDate) {
		this.activityDate = activityDate;
	}

}