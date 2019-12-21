package com.shopping.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhulin
 * @since 2019-12-01
 */
@TableName("shooping_mall_order_item")
public class OrderItem implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 订单关联购物项主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单id
     */
    @TableField("order_id")
    private Integer orderId;

    /**
     * 关联商品id
     */
    @TableField("commodity_id")
    private Integer commodityId;

    /**
     * 购买数量
     */
    @TableField("buy_counts")
    private Integer buyCounts;

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

    public Integer getId() {
        return id;
    }

    public OrderItem setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public OrderItem setOrderId(Integer orderId) {
        this.orderId = orderId;
        return this;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public OrderItem setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
        return this;
    }

    public Integer getBuyCounts() {
        return buyCounts;
    }

    public OrderItem setBuyCounts(Integer buyCounts) {
        this.buyCounts = buyCounts;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public OrderItem setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public OrderItem setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
        "id=" + id +
        ", orderId=" + orderId +
        ", commodityId=" + commodityId +
        ", buyCounts=" + buyCounts +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
