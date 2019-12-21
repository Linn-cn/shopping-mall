package com.shopping.mall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shopping.mall.entity.ShoppCart;
import com.shopping.mall.entity.dto.ShoppCartDTO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhulin
 * @since 2019-11-25
 */
public interface ShoppCartMapper extends BaseMapper<ShoppCart> {

    /**
     * 根据用户id返回对应的购物车列表
     * @param userId
     * @return java.util.List<com.shopping.mall.entity.dto.ShoppCartDTO>
     * @date 2019/11/29 15:43
     */
    List<ShoppCartDTO> listShoppCartByUserId(Integer userId);

}
