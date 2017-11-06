package com.mymvc.repository.hibernate.pojo;

import com.mymvc.model.basic.Model;
import com.mymvc.repository.hibernate.basic.Delete;
import com.mymvc.repository.hibernate.basic.Status;
import com.mymvc.system.annotation.ArticleContent;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by alan.luo on 2017/11/1.
 */
@Entity
@Table(name = "lx_article")
public class Article extends Model {

    private Integer id;

    private Integer userId;

    @Length(min = 5,max = 150,message = "error_validate_title")
    @NotEmpty(message = "error_validate_title")
    private String title;

    @ArticleContent
    private String content;

    private Long createTime;

    private Long updateTime;

    private Status status;

    private Delete isDelete;

    private Integer viewCount;

    private Integer commentCount;

    private String createTimeFormat;

    private String updateTimeFormat;

    private User author;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    @Enumerated
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Enumerated
    public Delete getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Delete isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    @Transient
    @Column(insertable = false,updatable = false)
    public String getCreateTimeFormat() {
        return createTimeFormat;
    }

    public void setCreateTimeFormat(String createTimeFormat) {
        this.createTimeFormat = createTimeFormat;
    }

    @Transient
    @Column(insertable = false,updatable = false)
    public String getUpdateTimeFormat() {
        return updateTimeFormat;
    }

    public void setUpdateTimeFormat(String updateTimeFormat) {
        this.updateTimeFormat = updateTimeFormat;
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
