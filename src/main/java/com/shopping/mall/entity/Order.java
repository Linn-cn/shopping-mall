package com.shopping.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhulin
 * @since 2019-12-01
 */
@TableName("shooping_mall_order")
public class Order implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "order_id", type = IdType.AUTO)
    private Integer orderId;

    /**
     * 关联用户id
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 订单号
     */
    @TableField("order_num")
    private String orderNum;

    /**
     * 订单状态
            10：待付款
            20：已付款
     */
    @TableField("order_status")
    private String orderStatus;

    /**
     * 订单金额
     */
    @TableField("order_amount")
    private BigDecimal orderAmount;

    /**
     * 收货地址
     */
    @TableField("user_address")
    private String userAddress;

    /**
     * 订单创建时间
     */
    @JsonFormat(pattern = "yyyy/MM/dd hh:mm:ss", timezone = "GMT+8")
    @TableField("create_time")
    private Date createTime;

    /**
     * 支付时间
     */
    @TableField("paid_time")
    private Date paidTime;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public Order setOrderId(Integer orderId) {
        this.orderId = orderId;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public Order setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public Order setOrderNum(String orderNum) {
        this.orderNum = orderNum;
        return this;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public Order setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public Order setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
        return this;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public Order setUserAddress(String userAddress) {
        this.userAddress = userAddress;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Order setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getPaidTime() {
        return paidTime;
    }

    public Order setPaidTime(Date paidTime) {
        this.paidTime = paidTime;
        return this;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", orderNum='" + orderNum + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderAmount='" + orderAmount + '\'' +
                ", createTime=" + createTime +
                ", paidTime=" + paidTime +
                '}';
    }
}
