package edu.harvard.mgh.lcs.sprout.study.model.transform;

import edu.harvard.mgh.lcs.sprout.study.model.study.UsersRoleEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema="dbo", name="narrative_format")
public class NarrativeFormatEntity implements Serializable {

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name="data")
    private String data;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="narrative_id")
    private NarrativeEntity narrative;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="format_id")
    private VNarrativeFormatEntity format;

    @Basic
    @Column(name="locale")
    private String locale;

    @Basic
    @Column(name="type")
    private String type;

    @Basic
    @Column(name="activity_date", columnDefinition="datetime", nullable=false)
    private Date activityDate = new Date();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public NarrativeEntity getNarrative() {
        return narrative;
    }

    public void setNarrative(NarrativeEntity narrative) {
        this.narrative = narrative;
    }

    public VNarrativeFormatEntity getFormat() {
        return format;
    }

    public void setFormat(VNarrativeFormatEntity format) {
        this.format = format;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }
}