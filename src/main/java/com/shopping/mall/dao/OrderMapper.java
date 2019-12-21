package com.shopping.mall.dao;

import com.shopping.mall.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shopping.mall.entity.dto.OrderDTO;
import com.shopping.mall.util.AjaxPutPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhulin
 * @since 2019-12-01
 */
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 订单分页
     * @param ajaxPutPage
     * @param userId
     * @return java.util.List<com.shopping.mall.entity.dto.OrderDTO>
     * @date 2019/12/3 10:17
     */
    List<OrderDTO> pageOrderByUserId(@Param("page") AjaxPutPage ajaxPutPage,
                                     @Param("userId") Integer userId);

}
