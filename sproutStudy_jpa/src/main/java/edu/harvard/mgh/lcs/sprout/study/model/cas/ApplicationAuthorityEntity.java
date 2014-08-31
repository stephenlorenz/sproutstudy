package edu.harvard.mgh.lcs.sprout.study.model.cas;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@NamedQueries({
        @NamedQuery(name=ApplicationAuthorityEntity.GET_AUTHORITIES, query="SELECT aa FROM ApplicationAuthorityEntity aa, IN (aa.grantedAuthorities) AS ga WHERE aa.application.name = :applicationName AND ga.principal = :principal"),
        @NamedQuery(name=ApplicationAuthorityEntity.GET_ADMIN_AUTHORITIES, query="FROM ApplicationAuthorityEntity WHERE application.name = :applicationName ORDER BY description"),
        @NamedQuery(name=ApplicationAuthorityEntity.GET_MANAGER_AUTHORITIES, query="SELECT aa FROM ApplicationAuthorityEntity aa, IN (aa.grantedAuthorities) AS ga WHERE aa.application.name = :applicationName AND ga.principal = :principal AND ga.manager = TRUE ")
})
@Entity
@Table(schema="dbo", name="lcs_application_authority")
public class ApplicationAuthorityEntity implements Serializable {

    public static final String GET_AUTHORITIES = "ApplicationAuthorityEntity.getAuthorities";
    public static final String GET_ADMIN_AUTHORITIES = "ApplicationAuthorityEntity.getAdminAuthorities";
    public static final String GET_MANAGER_AUTHORITIES = "ApplicationAuthorityEntity.getManagerAuthorities";

	private static final long serialVersionUID = -7537856951420701786L;

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

	@Basic
    @Column(name="code", nullable=false)
    private String code;

    @Basic
    @Column(name="description", nullable=false)
    private String description;

    @Basic
    @Column(name="active", nullable=false)
    private boolean active;

	@Basic
    @Column(name="activity_date", nullable=false)
    private Date activityDate;
	
    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="application_id")
    private ApplicationEntity application;
    
    @OneToMany(targetEntity=GrantedAuthorityEntity.class, mappedBy="applicationAuthority", cascade=CascadeType.MERGE)
    private Set<GrantedAuthorityEntity> grantedAuthorities = new HashSet<GrantedAuthorityEntity>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public ApplicationEntity getApplication() {
		return application;
	}

	public void setApplication(ApplicationEntity application) {
		this.application = application;
	}

	public Set<GrantedAuthorityEntity> getGrantedAuthorities() {
		return grantedAuthorities;
	}

	public void setGrantedAuthorities(Set<GrantedAuthorityEntity> grantedAuthorities) {
		this.grantedAuthorities = grantedAuthorities;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}