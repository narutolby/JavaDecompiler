package com.bupt.pcncad.domain;

import net.sf.ehcache.util.counter.sampled.TimeStampedCounterValue;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: liboyang01
 * Date: 12-7-23
 * Time: 上午2:33
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "RESOURCE")
@JsonIgnoreProperties({"uploadUser","collectResourceUsers","downloadResourceUsers","shareResourceUsers","resourceTags","resourceCourses"})
/*@Access(AccessType.PROPERTY)*/
public class Resource implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @Column(name = "resource_real_name")
    private String resourceRealName;
    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "upload_user_id")
    private User uploadUser;
    @Column(name = "upload_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadTime;
    @Column(name = "description")
    private String description;
    @Column(name = "resource_type")
    private String resourceType;
    @Column(name = "key_words")
    private String keyWords;
    @Column(name = "resource_mark")
    private int resourceMark;
    @Column(name = "resource_size")
    private long resourceSize;
    @Column(name = "category")
    private String category;
    @Column(name = "download_times")
    private int downloadTimes;
    @Column(name = "share_times")
    private int shareTimes;
    @Column(name = "collect_times")
    private int collectTimes;
    @Column(name = "resource_snapshot_path")
    private String resourceSnapshotPath;
    @Column(name = "delete_flag")
    private int deleteFlag;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "collectResources")
    private Set<User> collectResourceUsers = new HashSet<User>();
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "downloadResources")
    private Set<User> downloadResourceUsers = new HashSet<User>();
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "shareResources")
    private Set<User> shareResourceUsers = new HashSet<User>();
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "TAG_RESOURCE", joinColumns = @JoinColumn(name = "resource_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> resourceTags = new HashSet<Tag>();
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name = "RESOURCE_COURSE", joinColumns = @JoinColumn(name = "resource_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> resourceCourses = new HashSet<Course>();

    @Formula("(select u.user_id from USER u where u.id=upload_user_id)")
    private String uploadUserId;
    @Formula("(select u.user_name from USER u where u.id=upload_user_id)")
    private String uploadUserName;

    public String getUploadUserId() {
        return uploadUserId;
    }

    public String getUploadUserName() {
        return uploadUserName;
    }

    public Set<Course> getResourceCourses() {
        return resourceCourses;
    }

    public void setResourceCourses(Set<Course> resourceCourses) {
        this.resourceCourses = resourceCourses;
    }

    public String getId() {
        return id;
    }

    public String getUploadTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(uploadTime);
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public int getDownloadTimes() {
        return downloadTimes;
    }

    public void setDownloadTimes(int downloadTimes) {
        this.downloadTimes = downloadTimes;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResourceRealName() {
        return resourceRealName;
    }

    public void setResourceRealName(String resourceRealName) {
        this.resourceRealName = resourceRealName;
    }

    public User getUploadUser() {
        return uploadUser;
    }

    public void setUploadUser(User uploadUser) {
        this.uploadUser = uploadUser;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public int getResourceMark() {
        return resourceMark;
    }

    public void setResourceMark(int resourceMark) {
        this.resourceMark = resourceMark;
    }

    public long getResourceSize() {
        return resourceSize;
    }

    public void setResourceSize(long resourceSize) {
        this.resourceSize = resourceSize;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getShareTimes() {
        return shareTimes;
    }

    public void setShareTimes(int shareTimes) {
        this.shareTimes = shareTimes;
    }

    public int getCollectTimes() {
        return collectTimes;
    }

    public void setCollectTimes(int collectTimes) {
        this.collectTimes = collectTimes;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Set<User> getCollectResourceUsers() {
        return collectResourceUsers;
    }

    public void setCollectResourceUsers(Set<User> collectResourceUsers) {
        this.collectResourceUsers = collectResourceUsers;
    }

    public Set<User> getDownloadResourceUsers() {
        return downloadResourceUsers;
    }

    public void setDownloadResourceUsers(Set<User> downloadResourceUsers) {
        this.downloadResourceUsers = downloadResourceUsers;
    }

    public Set<User> getShareResourceUsers() {
        return shareResourceUsers;
    }

    public void setShareResourceUsers(Set<User> shareResourceUsers) {
        this.shareResourceUsers = shareResourceUsers;
    }

    public Set<Tag> getResourceTags() {
        return resourceTags;
    }

    public void setResourceTags(Set<Tag> resourceTags) {
        this.resourceTags = resourceTags;
    }

    public String getResourceSnapshotPath() {
        return resourceSnapshotPath;
    }

    public void setResourceSnapshotPath(String resourceSnapshotPath) {
        this.resourceSnapshotPath = resourceSnapshotPath;
    }
    public void setUploadUserId(String uploadUserId) {
        this.uploadUserId = uploadUserId;
    }

    public void setUploadUserName(String uploadUserName) {
        this.uploadUserName = uploadUserName;
    }

}
