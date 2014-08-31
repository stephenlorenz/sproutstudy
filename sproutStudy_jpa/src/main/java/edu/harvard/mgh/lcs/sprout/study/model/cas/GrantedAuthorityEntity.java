package edu.harvard.mgh.lcs.sprout.study.model.cas;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(name=GrantedAuthorityEntity.GET_GRANTED_AUTHORITIES, query="FROM GrantedAuthorityEntity WHERE applicationAuthority IN :applicationAuthorities ORDER BY applicationAuthority.description"),
        @NamedQuery(name=GrantedAuthorityEntity.GET_ALL_GRANTED_AUTHORITIES, query="FROM GrantedAuthorityEntity ORDER BY applicationAuthority.description"),
        @NamedQuery(name=GrantedAuthorityEntity.GET_GRANTED_AUTHORITY, query="FROM GrantedAuthorityEntity WHERE applicationAuthority = :applicationAuthority AND principal = :principal")
})
@Entity
@Table(schema="dbo", name="lcs_granted_authority")
public class GrantedAuthorityEntity implements Serializable {

	private static final long serialVersionUID = 4208774628955495801L;

    public static final String GET_GRANTED_AUTHORITIES = "GrantedAuthorityEntity.getGrantedAuthorities";
    public static final String GET_ALL_GRANTED_AUTHORITIES = "GrantedAuthorityEntity.getAllGrantedAuthorities";
    public static final String GET_GRANTED_AUTHORITY = "GrantedAuthorityEntity.getGrantedAuthority";

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="application_authority_id")
    private ApplicationAuthorityEntity applicationAuthority;

    @Basic
    @Column(name="principal", nullable=false, length=25)
    private String principal;

    @Basic
    @Column(name="active", nullable=false)
    private boolean active = true;

    @Basic
    @Column(name="manager_ind", nullable=false)
    private boolean manager;

    @Basic
    @Column(name="activity_date", nullable=false)
    private Date activityDate = new Date();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ApplicationAuthorityEntity getApplicationAuthority() {
		return applicationAuthority;
	}

	public void setApplicationAuthority(
			ApplicationAuthorityEntity applicationAuthority) {
		this.applicationAuthority = applicationAuthority;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
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

    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }


}