package com.bupt.pcncad.domain;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: liboyang01
 * Date: 12-7-21
 * Time: 上午11:30
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "USER")
/*@Access(AccessType.PROPERTY)*/
public class User implements Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "user_password")
    private String userPasswd;
    @Column(name = "first_login_time")
    private Date firstLoginTime;
    @Column(name = "activity")
    private int activity;
    @Column(name = "state")
    private boolean state;
    @Column(name = "user_birth")
    private Date userBirth;
    @Column(name = "user_gender")
    private int userGender;
    @Column(name = "user_teleph")
    private String userTeleph;
    @Column(name = "user_email")
    private String userEmail;
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name = "USER_COLLECT_RESOURCE", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "resource_id"))
    private Set<Resource> collectResources;
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name = "USER_DOWNLOAD_RESOURCE", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "resource_id"))
    private Set<Resource> downloadResources = new HashSet<Resource>();
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name = "USER_SHARE_RESOURCE", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "resource_id"))
    private Set<Resource> shareResources = new HashSet<Resource>();
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "groupUsers")
    private Set<Group> userGroups = new HashSet<Group>();
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name = "USER_TAG", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> userTags = new HashSet<Tag>();
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "USER_FOLLOW_MAJOR", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "major_id"))
    private Set<Major> majors = new HashSet<Major>();
    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(name = "USER_FOLLOW_COURSE", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> courses = new HashSet<Course>();
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "COMMUNITY_USER", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "community_id"))
    private Set<Community> communities = new HashSet<Community>();
    @Column(name="role")
    private Short role;

    public Short getRole() {
        return role;
    }

    public void setRole(Short role) {
        this.role = role;
    }

    public Set<Community> getCommunities() {
        return communities;
    }

    public void setCommunities(Set<Community> communities) {
        this.communities = communities;
    }


    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<Resource> getCollectResources() {
        return collectResources;
    }

    public void setCollectResources(Set<Resource> collectResources) {
        this.collectResources = collectResources;
    }

    public Set<Resource> getDownloadResources() {
        return downloadResources;
    }

    public void setDownloadResources(Set<Resource> downloadResources) {
        this.downloadResources = downloadResources;
    }

    public Set<Resource> getShareResources() {
        return shareResources;
    }

    public void setShareResources(Set<Resource> shareResources) {
        this.shareResources = shareResources;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getFirstLoginTime() {
        return firstLoginTime;
    }

    public void setFirstLoginTime(Date firstLoginTime) {
        this.firstLoginTime = firstLoginTime;
    }

    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Date getUserBirth() {
        return userBirth;
    }

    public void setUserBirth(Date userBirth) {
        this.userBirth = userBirth;
    }

    public int getUserGender() {
        return userGender;
    }

    public void setUserGender(int userGender) {
        this.userGender = userGender;
    }

    public String getUserTeleph() {
        return userTeleph;
    }

    public void setUserTeleph(String userTeleph) {
        this.userTeleph = userTeleph;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Set<Group> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(Set<Group> userGroups) {
        this.userGroups = userGroups;
    }

    public Set<Tag> getUserTags() {
        return userTags;
    }

    public void setUserTags(Set<Tag> userTags) {
        this.userTags = userTags;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPasswd() {
        return userPasswd;
    }

    public void setUserPasswd(String userPasswd) {
        this.userPasswd = userPasswd;
    }

    public Set<Major> getMajors() {
        return majors;
    }

    public void setMajors(Set<Major> majors) {
        this.majors = majors;
    }

}
