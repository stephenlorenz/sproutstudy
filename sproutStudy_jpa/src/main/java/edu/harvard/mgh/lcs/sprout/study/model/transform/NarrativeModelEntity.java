package edu.harvard.mgh.lcs.sprout.study.model.transform;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(schema="dbo", name="narrative_model")
public class NarrativeModelEntity implements Serializable {

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name="model")
    private String model;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="narrative_id")
    private NarrativeEntity narrative;

    @Basic
    @Column(name="activity_date", columnDefinition="datetime", nullable=false)
    private Date activityDate = new Date();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public NarrativeEntity getNarrative() {
        return narrative;
    }

    public void setNarrative(NarrativeEntity narrative) {
        this.narrative = narrative;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }
}