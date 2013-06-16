package com.bupt.pcncad.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: liboyang01
 * Date: 12-7-23
 * Time: 上午8:18
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "TAG")
public class Tag implements Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @Column(name = "tag_name")
    private String tagName;
    @Column(name = "reference_times")
    private int referenceTimes;
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "userTags")
    private Set<User> tagUsers = new HashSet<User>();
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "resourceTags")
    private Set<Resource> tagResources = new HashSet<Resource>();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public int getReferenceTimes() {
        return referenceTimes;
    }

    public void setReferenceTimes(int referenceTimes) {
        this.referenceTimes = referenceTimes;
    }

    public Set<User> getTagUsers() {
        return tagUsers;
    }

    public void setTagUsers(Set<User> tagUsers) {
        this.tagUsers = tagUsers;
    }
    public Set<Resource> getTagResources() {
        return tagResources;
    }

    public void setTagResources(Set<Resource> tagResources) {
        this.tagResources = tagResources;
    }
}
