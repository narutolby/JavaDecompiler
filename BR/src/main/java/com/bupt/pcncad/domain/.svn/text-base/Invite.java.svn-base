package com.bupt.pcncad.domain;

import javax.persistence.*;
import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: zhang
 * Date: 13-3-26
 * Time: 下午2:58
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "USER_FOLLOW_COURSE")
public class Invite implements Serializable {
    @Column(name = "in_community",columnDefinition="int default 0")
    private int inCommunity;
    @Column(name = "invite",columnDefinition="int default 0")
    private int invite;
    @Column(name = "community_id")
    private  String communityId;
    @EmbeddedId
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @EmbeddedId
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public int getInCommunity() {
        return inCommunity;
    }

    public void setInCommunity(int inCommunity) {
        this.inCommunity = inCommunity;
    }

    public int getInvite() {
        return invite;
    }

    public void setInvite(int invite) {
        this.invite = invite;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
