package com.shopping.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shopping.mall.validate.UserLogin;
import com.shopping.mall.validate.UserRegister;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhulin
 * @since 2019-11-25
 */
@TableName("shooping_mall_user")
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 用户id
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 用户手机号
     */
    @TableField("user_phone")
    @NotBlank(message = "用户名不能为空",groups = {
            UserLogin.class, UserRegister.class
    })
    @Size(max = 13,message = "手机号不能超过13位",groups = UserRegister.class)
    private String userPhone;

    /**
     * 用户头像
     */
    @TableField("user_img")
    private String userImg;

    /**
     * 用户密码
     */
    @TableField("user_password")
    @NotBlank(message = "密码不能为空",groups = {
            UserLogin.class, UserRegister.class
    })
    private String userPassword;

    /**
     * 收货地址
     */
    @TableField("user_address")
    private String userAddress;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getUserId() {
        return userId;
    }

    public User setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public User setUserPhone(String userPhone) {
        this.userPhone = userPhone;
        return this;
    }

    public String getUserImg() {
        return userImg;
    }

    public User setUserImg(String userImg) {
        this.userImg = userImg;
        return this;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public User setUserPassword(String userPassword) {
        this.userPassword = userPassword;
        return this;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public User setUserAddress(String userAddress) {
        this.userAddress = userAddress;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public User setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public User setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
        "userId=" + userId +
        ", userName=" + userName +
        ", userPhone=" + userPhone +
        ", userImg=" + userImg +
        ", userPassword=" + userPassword +
        ", userAddress=" + userAddress +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
