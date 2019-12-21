package com.shopping.mall.entity.dto;

import com.shopping.mall.entity.Commodity;
import com.shopping.mall.entity.ShoppCart;

/**
 * @program: shopping-mall
 * @classname: ShoppCartDTO
 * @description: 购物车传输对象
 * @author: 朱林
 * @create: 2019-11-29 15:40
 **/
public class ShoppCartDTO extends ShoppCart {

    private Commodity commodity;

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }
}
