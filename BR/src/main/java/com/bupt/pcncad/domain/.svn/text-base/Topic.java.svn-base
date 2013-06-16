package com.bupt.pcncad.domain;

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
 * Time: 上午8:43
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "TOPIC")
@JsonIgnoreProperties({"community", "author"})
public class Topic implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_id")
    private Community community;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;
    @Column(name = "title")
    private String topicTitle;
    @Column(name = "content")
    private String topicContent;
    @Column(name = "pub_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pubDate;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("pubDate asc")
    @JoinColumn(name = "reply_topic_id")
    private Set<SubTopic> subTopicSet = new HashSet<SubTopic>();
    @Formula("(select user.user_name from USER user where user.id=author_id)")
    private String authorName;
    @Formula("(select user.role from USER user where user.id=author_id)")
    private short role;
    @Formula("(select count(st.id) from SUB_TOPIC st where st.reply_topic_id=id)")
    private int replyCount;
    @Formula("(select pub_date from SUB_TOPIC st where st.reply_topic_id=id order by pub_date desc limit 0,1)")
    private Date latestReplyDate;
    @Column(name="delete_flag",columnDefinition = "int default 1")
    private int deleteFlag=1;
    @Formula("(select c.name from COMMUNITY c where c.id = community_id)")
    private String communityName;
    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public String getLatestReplyDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(this.latestReplyDate==null){
            return "";
        }
        return simpleDateFormat.format(this.latestReplyDate);
    }

    public void setLatestReplyDate(Date latestReplyDate) {

        this.latestReplyDate = latestReplyDate;
    }

    public short getRole() {
        return role;
    }

    public void setRole(short role) {
        this.role = role;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }


    public String getPubDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(this.pubDate);
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getTopicTitle() {
        return topicTitle;
    }

    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }

    public Set<SubTopic> getSubTopicSet() {
        return subTopicSet;
    }

    public void setSubTopicSet(Set<SubTopic> subTopicSet) {
        this.subTopicSet = subTopicSet;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }
}
