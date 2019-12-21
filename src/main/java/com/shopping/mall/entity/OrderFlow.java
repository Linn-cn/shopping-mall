package com.shopping.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 订单流水
 * </p>
 *
 * @author zhulin
 * @since 2019-12-03
 */
@TableName("shooping_mall_order_flow")
public class OrderFlow implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "flow_id", type = IdType.AUTO)
    private Integer flowId;

    /**
     * 流水号
     */
    @TableField("flow_num")
    private String flowNum;

    /**
     * 订单号
     */
    @TableField("order_num")
    private String orderNum;

    /**
     * 支付金额
     */
    @TableField("paid_amount")
    private String paidAmount;

    /**
     * 支付方式
            1：支付宝
            2：微信
     */
    @TableField("paid_method")
    private Integer paidMethod;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getFlowId() {
        return flowId;
    }

    public OrderFlow setFlowId(Integer flowId) {
        this.flowId = flowId;
        return this;
    }

    public String getFlowNum() {
        return flowNum;
    }

    public OrderFlow setFlowNum(String flowNum) {
        this.flowNum = flowNum;
        return this;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public OrderFlow setOrderNum(String orderNum) {
        this.orderNum = orderNum;
        return this;
    }

    public String getPaidAmount() {
        return paidAmount;
    }

    public OrderFlow setPaidAmount(String paidAmount) {
        this.paidAmount = paidAmount;
        return this;
    }

    public Integer getPaidMethod() {
        return paidMethod;
    }

    public OrderFlow setPaidMethod(Integer paidMethod) {
        this.paidMethod = paidMethod;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public OrderFlow setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    @Override
    public String toString() {
        return "OrderFlow{" +
        "flowId=" + flowId +
        ", flowNum=" + flowNum +
        ", orderNum=" + orderNum +
        ", paidAmount=" + paidAmount +
        ", paidMethod=" + paidMethod +
        ", createTime=" + createTime +
        "}";
    }
}
