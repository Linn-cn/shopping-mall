package com.shopping.mall.entity.dto;

import com.shopping.mall.entity.Commodity;
import com.shopping.mall.entity.OrderItem;

import java.io.Serializable;

/**
 * @program: shopping-mall
 * @classname: OrderItemDTO
 * @description: 订单关联项
 * @author: 朱林
 * @create: 2019-12-03 09:56
 **/
public class OrderItemDTO extends OrderItem implements Serializable {

    /**
     * 商品信息
     */
    private Commodity commodity;


    public Commodity getCommodity() {
        return commodity;
    }

    public OrderItemDTO setCommodity(Commodity commodity) {
        this.commodity = commodity;
        return this;
    }
}
