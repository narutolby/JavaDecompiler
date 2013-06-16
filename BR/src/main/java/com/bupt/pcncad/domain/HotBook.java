package com.bupt.pcncad.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: naruto
 * Date: 13-4-23
 * Time: 下午4:13
 * To change this template use File | Settings | File Templates.
 * type 0:借阅排行 1：检索排行推荐书 2：收藏排行 3:察看排行
 */
@Entity
@Table(name = "HOT_BOOK")
public class HotBook implements Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @ManyToOne(cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "english_name")
    private String englishName;
    @Column(name = "chinese_name")
    private String chineseName;
    @Column(name = "description")
    private String description;
    @Column(name = "author")
    private String author;
    @Column(name = "publisher")
    private String publisher;
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "img_url")
    private String imgUrl;
    @Column(name = "douban_href")
    private String doubanHref;
    @Column(name = "lab_href")
    private String labHref;
    @Column(name = "price")
    private String price;
    @Column(name = "pages")
    private String pages;
    @Column(name = "pub_date")
    private String pubDate;
    @Column(name = "douban_id")
    private String doubanId;
    @Column(name = "flag")
    private int flag;
    @Column(name = "binding")
    private String binding;
    @Column(name = "tag")
    private String tag;
    @Column(name = "rating")
    private String rating;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }


    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }


    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }


    public String getDoubanId() {
        return doubanId;
    }

    public void setDoubanId(String doubanId) {
        this.doubanId = doubanId;
    }


    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDoubanHref() {
        return doubanHref;
    }

    public void setDoubanHref(String doubanHref) {
        this.doubanHref = doubanHref;
    }

    public String getLabHref() {
        return labHref;
    }

    public void setLabHref(String labHref) {
        this.labHref = labHref;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object obj) {
        HotBook obj1 = (HotBook) obj;
        if (this.doubanId != null && obj1.doubanId != null) {
            return this.doubanId.equals(obj1.getDoubanId());
        } else if (this.getChineseName() != null && obj1.getChineseName() != null) {
            return this.getChineseName().equals(obj1.getChineseName());
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        if (this.doubanId != null) {
            return this.doubanId.hashCode();
        } else if (this.getChineseName() != null) {
            return this.getChineseName().hashCode();
        }
        return super.hashCode();
    }
}
