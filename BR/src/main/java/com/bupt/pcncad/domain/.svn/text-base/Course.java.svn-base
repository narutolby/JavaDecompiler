package com.bupt.pcncad.domain;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: liboyang01
 * Date: 12-9-5
 * Time: 下午2:54
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "COURSE")
@JsonIgnoreProperties({"users", "major","communities"})
public class Course implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @Column(name = "name")
    private String name;
    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "major_id")
    private Major major;
    /*1:第一学期；2：第二学期.。。。。*/
    @Column(name = "term")
    private int term;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "courses")
    private Set<User> users = new HashSet<User>();
    @OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinColumn(name="course_id")
    private Set<Community> communities = new HashSet<Community>();
    @Formula("(select count(r.id) from RESOURCE r inner join RESOURCE_COURSE rc on r.id = rc.resource_id where rc.course_id=id and r.delete_flag=0)")
    private int childrenCount;
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Set<DoubanJson> doubanJsons = new HashSet<DoubanJson>();

    @Column(name = "search_keyword")
    private String searchKeyword;

    public int getChildrenCount() {
        return childrenCount;
    }

    public void setChildrenCount(int childrenCount) {
        this.childrenCount = childrenCount;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

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

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public Set<Community> getCommunities() {
        return communities;
    }

    public void setCommunities(Set<Community> communities) {
        this.communities = communities;
    }

    public Set<DoubanJson> getDoubanJsons() {
        return doubanJsons;
    }

    public void setDoubanJsons(Set<DoubanJson> doubanJsons) {
        this.doubanJsons = doubanJsons;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    @Override
    public int hashCode() {
        if (this.id != null) {
            return this.id.hashCode();
        } else {
            return super.hashCode();
        }
    }

    @Override
    public boolean equals(Object course) {
        if (this == course) {
            return true;
        } else if (this.getId().equals(((Course) course).getId())) {
            return true;
        } else {
            return false;
        }
    }
}
