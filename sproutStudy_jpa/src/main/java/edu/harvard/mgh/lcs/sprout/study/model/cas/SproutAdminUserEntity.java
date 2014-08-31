package edu.harvard.mgh.lcs.sprout.study.model.cas;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@NamedQueries({
	@NamedQuery(name= SproutAdminUserEntity.BY_PRINCIPAL, query="FROM SproutAdminUserEntity WHERE principal = :principal")
})
@Entity
@Table(schema="dbo", name="lcs_sprout_admin_user")
public class SproutAdminUserEntity implements Serializable {
	
	public static final String BY_PRINCIPAL = "SproutAdminUserEntity.byPrincipal";

	private static final long serialVersionUID = 2582085017887391730L;

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name="principal", nullable=false)
    private String principal;

    @Basic
    @Column(name="activity_date", nullable=false)
    private Date activityDate = new Date();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }
}