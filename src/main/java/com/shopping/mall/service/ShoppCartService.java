package com.shopping.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shopping.mall.entity.ShoppCart;
import com.shopping.mall.entity.dto.ShoppCartDTO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhulin
 * @since 2019-11-25
 */
public interface ShoppCartService extends IService<ShoppCart> {

    /**
     * 根据用户id返回对应购物车列表
     * @param userId
     * @return java.util.List<com.shopping.mall.entity.dto.ShoppCartDTO>
     * @date 2019/11/29 15:51
     */
    List<ShoppCartDTO> listShoppCartByUserId(Integer userId);

}
