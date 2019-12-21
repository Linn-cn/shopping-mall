package com.shopping.mall.entity;

import java.math.BigDecimal;

/**
 * @program: shopping-mall
 * @classname: GoodsDetail
 * @description:
 * @author: 朱林
 * @create: 2019-12-02 11:00
 **/
public class GoodsDetail {

    /**
     * 商品编号
     */
    private String goods_id;

    /**
     * 商品名称
     */
    private String goods_name;

    /**
     * 商品数量
     */
    private Integer quantity;

    /**
     * 商品单价
     */
    private BigDecimal price;
}
