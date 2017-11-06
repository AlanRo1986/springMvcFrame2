package com.mymvc.repository.hibernate.pojo;

import com.mymvc.model.basic.Model;
import com.mymvc.repository.hibernate.basic.Status;
import com.mymvc.system.annotation.ArticleContent;
import com.mymvc.system.annotation.PivotalId;

import javax.persistence.*;

/**
 * Created by alan.luo on 2017/11/1.
 */
@Entity
@Table(name = "lx_comment")
public class Comment extends Model {

    private Integer id;

    @PivotalId
    private Integer articleId;

    private Integer userId;

    @ArticleContent
    private String content;

    private Long createTime;

    private Integer parentId;

    private Status status;

    private String createTimeFormat;

    private User author;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Enumerated
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Transient
    @Column(insertable = false,updatable = false)
    public String getCreateTimeFormat() {
        return createTimeFormat;
    }

    public void setCreateTimeFormat(String createTimeFormat) {
        this.createTimeFormat = createTimeFormat;
    }

    @OneToOne(targetEntity = User.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "userId",insertable = false,updatable = false)
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
