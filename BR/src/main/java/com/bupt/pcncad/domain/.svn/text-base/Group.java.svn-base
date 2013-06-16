package com.bupt.pcncad.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.engine.CascadeStyle;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: liboyang01
 * Date: 12-7-23
 * Time: 上午1:58
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "`GROUP`")
public class Group implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @Column(name = "group_name")
    private String groupName;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "group_admin_id")
    private User groupAdmin;
    @Column(name = "group_users_count")
    private int groupUsersCount;
    @Column(name = "description")
    private String description;
    @ManyToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    @JoinTable(name="GROUP_USER",joinColumns = @JoinColumn(name="group_id"),inverseJoinColumns =@JoinColumn(name="user_id") )
    private Set<User> groupUsers = new HashSet<User>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public User getGroupAdmin() {
        return groupAdmin;
    }

    public void setGroupAdmin(User groupAdmin) {
        this.groupAdmin = groupAdmin;
    }

    public int getGroupUsersCount() {
        return groupUsersCount;
    }

    public void setGroupUsersCount(int groupUsersCount) {
        this.groupUsersCount = groupUsersCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
