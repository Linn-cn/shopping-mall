package com.shopping.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shopping.mall.validate.ShoppAdd;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.StringJoiner;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhulin
 * @since 2019-11-25
 */
@TableName("shooping_mall_shopp_cart")
public class ShoppCart implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 购物车id
     */
    @TableId(value = "cart_id", type = IdType.AUTO)
    private Integer cartId;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 商品id
     */
    @TableField("commodity_id")
    @NotNull(message = "请选择商品",groups = ShoppAdd.class)
    private Integer commodityId;

    /**
     * 商品数量
     */
    @TableField("commodity_count")
    @Range(min = 1,max = 1000,message = "商品数量选择错误",groups = ShoppAdd.class)
    private Integer commodityCount;

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

    public Integer getCartId() {
        return cartId;
    }

    public ShoppCart setCartId(Integer cartId) {
        this.cartId = cartId;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public ShoppCart setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public ShoppCart setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
        return this;
    }

    public Integer getCommodityCount() {
        return commodityCount;
    }

    public ShoppCart setCommodityCount(Integer commodityCount) {
        this.commodityCount = commodityCount;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public ShoppCart setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public ShoppCart setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ShoppCart.class.getSimpleName() + "[", "]")
                .add("cartId=" + cartId)
                .add("userId=" + userId)
                .add("commodityId=" + commodityId)
                .add("commodityCount=" + commodityCount)
                .add("createTime=" + createTime)
                .add("updateTime=" + updateTime)
                .toString();
    }
}
