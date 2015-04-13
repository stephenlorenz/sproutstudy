package edu.harvard.mgh.lcs.sprout.study.model.study;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(schema="dbo", name="users_role")
@NamedQueries({
        @NamedQuery(name = UsersRoleEntity.FIND_BY_ROLE_CODE, query = "SELECT r FROM UsersRoleEntity r WHERE r.user = :user AND r.role.name = :role")
})
public class UsersRoleEntity implements Serializable {

    public static final String FIND_BY_ROLE_CODE = "UsersRoleEntity.findByRoleCode";

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
    @JoinColumn(name="users_id")
    private UserEntity user;

    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.MERGE)
    @JoinColumn(name="role_id")
    private VUserRoleEntity role;

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

    public VUserRoleEntity getRole() {
        return role;
    }

    public void setRole(VUserRoleEntity role) {
        this.role = role;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }
}