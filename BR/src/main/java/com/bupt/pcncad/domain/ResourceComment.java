package com.bupt.pcncad.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: naruto
 * Date: 13-6-13
 * Time: 上午10:27
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name="RESOURCE_COMMENT")
public class ResourceComment {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @Column(name="content")
    private String content;
    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "pub_user_id")
    private User pubUser;
    @ManyToOne(cascade = {CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "resource_id")
    private Resource resource;

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Column(name="mark")
    private int mark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getPubUser() {
        return pubUser;
    }

    public void setPubUser(User pubUser) {
        this.pubUser = pubUser;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Date getPutDate() {
        return putDate;
    }

    public void setPutDate(Date putDate) {
        this.putDate = putDate;
    }

    @Column(name="pub_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date putDate;

}
