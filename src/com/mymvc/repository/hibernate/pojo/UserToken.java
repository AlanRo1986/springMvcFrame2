package com.mymvc.repository.hibernate.pojo;

import com.mymvc.model.basic.Model;
import com.mymvc.repository.hibernate.basic.Status;

import javax.persistence.*;

/**
 * Created by alan.luo on 2017/11/1.
 */
@Entity
@Table(name = "lx_user_token")
public class UserToken extends Model{

    private Integer id;

    private Integer userId;

    private String token;

    private Long expireIn;

    private Double lat;

    private Double lnt;

    private Long loginTime;

    private Status status;

    private String expireInFormat;

    private String loginTimeFormat;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(Long expireIn) {
        this.expireIn = expireIn;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLnt() {
        return lnt;
    }

    public void setLnt(Double lnt) {
        this.lnt = lnt;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    @Enumerated(value = EnumType.ORDINAL)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Transient
    @Column(insertable = false,updatable = false)
    public String getExpireInFormat() {
        return expireInFormat;
    }

    public void setExpireInFormat(String expireInFormat) {
        this.expireInFormat = expireInFormat;
    }

    @Transient
    @Column(insertable = false,updatable = false)
    public String getLoginTimeFormat() {
        return loginTimeFormat;
    }

    public void setLoginTimeFormat(String loginTimeFormat) {
        this.loginTimeFormat = loginTimeFormat;
    }
}
