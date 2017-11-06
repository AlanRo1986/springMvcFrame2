package com.mymvc.repository.hibernate.pojo;

import com.mymvc.model.basic.Model;
import com.mymvc.repository.hibernate.basic.Delete;
import com.mymvc.repository.hibernate.basic.Status;
import com.mymvc.repository.hibernate.basic.UserLogType;

import javax.persistence.*;

/**
 * Created by alan.luo on 2017/10/22.
 */
@Entity
@Table(name = "lx_user_log")
public class UserLog extends Model {

    private int id;

    private int userId;

    private String content;

    private UserLogType logType;

    private Status status;

    private Delete isDelete;

    private Long createTime;

    private String ipAddr;

    private String createTimeFormat;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Enumerated(value = EnumType.STRING)
    public UserLogType getLogType() {
        return logType;
    }

    public void setLogType(UserLogType logType) {
        this.logType = logType;
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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    @Transient
    @Column(insertable = false,updatable = false)
    public String getCreateTimeFormat() {
        return createTimeFormat;
    }

    public void setCreateTimeFormat(String createTimeFormat) {
        this.createTimeFormat = createTimeFormat;
    }
}
