package edu.harvard.mgh.lcs.sprout.study.model.transform;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(schema="dbo", name="v_narrative_format")
@NamedQueries({
    @NamedQuery(name= VNarrativeFormatEntity.ALL, query="FROM VNarrativeFormatEntity"),
    @NamedQuery(name= VNarrativeFormatEntity.FIND_BY_CODE, query="FROM VNarrativeFormatEntity WHERE code = :code")
})
public class VNarrativeFormatEntity implements Serializable {

    public static final String ALL = "VNarrativeFormatEntity.all";
    public static final String FIND_BY_CODE = "VNarrativeFormatEntity.findByCode";

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(nullable=false, length=20)
    private String code;

    @Basic
    @Column(nullable=false, length=100)
    private String description;

    @Basic
    @Column(name="content_type", nullable=false)
    private String contentType;

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

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }
}