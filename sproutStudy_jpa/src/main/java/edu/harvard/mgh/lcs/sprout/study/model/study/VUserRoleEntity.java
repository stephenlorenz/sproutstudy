package edu.harvard.mgh.lcs.sprout.study.model.study;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema="dbo", name="v_users_role")
@NamedQueries({
    @NamedQuery(name= VUserRoleEntity.ALL, query="FROM VUserRoleEntity"),
    @NamedQuery(name= VUserRoleEntity.FIND_BY_CODE, query="FROM VUserRoleEntity WHERE code = :code")
})
public class VUserRoleEntity implements Serializable {

    public static final String ALL = "VUserRoleEntity.all";
    public static final String FIND_BY_CODE = "VUserRoleEntity.findByCode";

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(nullable=false, length=25)
    private String name;

    @Basic
    @Column(nullable=false, length=100)
    private String description;

    @Basic
    @Column(name="admin_ind", nullable=false)
    private boolean admin;

    @OneToMany(targetEntity=UsersRoleEntity.class, mappedBy="role", cascade=CascadeType.MERGE)
    private Set<UsersRoleEntity> roles = new HashSet<UsersRoleEntity>();

    @Basic
    @Column(name="activity_date", columnDefinition="datetime", nullable=false)
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

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Set<UsersRoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<UsersRoleEntity> roles) {
        this.roles = roles;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }
}