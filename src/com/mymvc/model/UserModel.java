package com.mymvc.model;

import com.mymvc.model.basic.Model;
import com.mymvc.repository.hibernate.basic.Gender;
import com.mymvc.system.annotation.Mobile;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;



/**
 * Created by alan.luo on 2017/9/20.
 */
public class UserModel extends Model {

    private Integer id;

    private String username;

    private String loginPassword;

    private Integer age;

    @NotEmpty(message = "error_validate_email")
    @Email(message = "error_validate_email")
    private String email;

    @Mobile
    private String mobile;

    private Gender sex;

    private Long createTime;

    private String createTimeFormat;

    public String getCreateTimeFormat() {
        return createTimeFormat;
    }

    public void setCreateTimeFormat(String createTimeFormat) {
        this.createTimeFormat = createTimeFormat;
    }


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

    public Gender getSex() {
        return sex;
    }

    public void setSex(Gender sex) {
        this.sex = sex;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}
