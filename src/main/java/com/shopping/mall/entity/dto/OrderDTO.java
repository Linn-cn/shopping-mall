package com.shopping.mall.entity.dto;

import com.shopping.mall.entity.Order;

import java.io.Serializable;
import java.util.List;

/**
 * @program: shopping-mall
 * @classname: OrderDTO
 * @description:
 * @author: 朱林
 * @create: 2019-12-03 09:55
 **/
public class OrderDTO extends Order implements Serializable {

    List<OrderItemDTO> orderItems;

    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public OrderDTO setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
        return this;
    }
}
