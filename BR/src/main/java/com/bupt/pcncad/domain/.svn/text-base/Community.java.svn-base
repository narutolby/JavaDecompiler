package com.bupt.pcncad.domain;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: naruto
 * Date: 12-10-17
 * Time: 下午3:51
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "COMMUNITY")
@JsonIgnoreProperties({"state", "users", "admin"})
public class Community implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @Column(name = "name")
    private String name;
    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "admin_id")
    private User admin;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "communities")
    private Set<User> users = new HashSet<User>();
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "community_id")
    @OrderBy("pubDate desc")
    private Set<Bulletin> bulletins = new HashSet<Bulletin>();
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_id")
    @OrderBy("pubDate desc")
    @BatchSize(size = 4)
    private Set<Topic> topics = new HashSet<Topic>();
    @Column(name = "state")
    private short state;
    @Formula("(select count(cu.community_id) from COMMUNITY_USER cu where cu.community_id=id)")
    private int memberCount;
    @Formula("(select count(rc.resource_id) from RESOURCE_COURSE rc where rc.course_id=course_id and (select res.delete_flag from RESOURCE res where res.id=rc.resource_id)=0 and (select res.resource_mark from RESOURCE res where res.id=rc.resource_id)=1)")
    private int resourceCount;
    @Formula("(select count(t.id) from TOPIC t where t.community_id=id and t.delete_flag=1)")
    private int topicCount;

    public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    public int getResourceCount() {
        return resourceCount;
    }

    public void setResourceCount(int resourceCount) {
        this.resourceCount = resourceCount;
    }

    public int getTopicCount() {
        return topicCount;
    }

    public void setTopicCount(int topicCount) {
        this.topicCount = topicCount;
    }

    public Set<Topic> getTopics() {
        return topics;
    }

    public void setTopics(Set<Topic> topics) {
        this.topics = topics;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }


    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    /*private Set<User> userSet = new HashSet<User>();*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Set<Bulletin> getBulletins() {
        return bulletins;
    }

    public void setBulletins(Set<Bulletin> bulletins) {
        this.bulletins = bulletins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Community && this.getName().equals(((Community) o).getName())) {
            this.setState(((Community) o).getState());
            return true;
        } else {
            return false;
        }

    }

    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }


}
