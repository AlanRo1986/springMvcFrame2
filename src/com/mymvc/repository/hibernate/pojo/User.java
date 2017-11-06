package com.mymvc.repository.hibernate.pojo;

import com.mymvc.model.basic.Model;
import com.mymvc.repository.hibernate.basic.Gender;
import com.mymvc.system.annotation.Mobile;
import com.mymvc.system.annotation.Password;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * Created by alan.luo on 2017/9/20.
 */
@Entity
@Table(name = "lx_user")
public class User extends Model {

    private Integer id;

    @NotEmpty(message = "error_validate_username")
    private String username;

    @Password
    private String loginPassword;

    @NotEmpty(message = "error_validate_nickname")
    private String nickname;

    private String realName;

    @NotEmpty(message = "error_validate_email")
    @Email(message = "error_validate_email")
    private String email;

    @Mobile
    private String mobile;

    private Gender sex;

    private Integer age;

    private Long createTime;

    private String createTimeFormat;

    private String token;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Enumerated(value = EnumType.STRING)
    public Gender getSex() {
        return sex;
    }

    public void setSex(Gender sex) {
        this.sex = sex;
    }

    //@Temporal(value = TemporalType.TIMESTAMP)
    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
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
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
