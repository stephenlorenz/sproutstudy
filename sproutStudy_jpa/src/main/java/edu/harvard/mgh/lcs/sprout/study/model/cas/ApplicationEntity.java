package edu.harvard.mgh.lcs.sprout.study.model.cas;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

@NamedQueries({
//	@NamedQuery(name=ApplicationEntity.FIND_BY_PRACTICE_ID, query="FROM PracticeEntity WHERE ihsGroupId = :ihsGroupId")
})
@Entity
@Table(schema="dbo", name="lcs_application")
public class ApplicationEntity implements Serializable {
	
//	public static final String FIND_BY_PRACTICE_ID = "PracticeEntity.findByPracticeId";

	private static final long serialVersionUID = 2582085017887391730L;

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

	@Basic
    @Column(name="name", nullable=false)
    private String name;

	@Basic
    @Column(name="description", nullable=false)
    private String description;

    @Basic
    @Column(name="title", nullable=false)
    private String title;

	@Basic
    @Column(name="active", nullable=false)
    private boolean active;

	@Basic
    @Column(name="activityDate", nullable=false)
    private Date activityDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}