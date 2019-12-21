package com.shopping.mall.entity.vo;

import com.shopping.mall.entity.OrderItem;

import java.util.List;

/**
 * @program: shopping-mall
 * @classname: OrderItemVO
 * @description:
 * @author: 朱林
 * @create: 2019-12-04 12:11
 **/
public class OrderItemVO {

    private String userAddress;

    private List<OrderItem> orderItems;

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
