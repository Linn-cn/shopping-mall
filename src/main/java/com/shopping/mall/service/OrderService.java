package com.shopping.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shopping.mall.entity.Order;
import com.shopping.mall.entity.OrderFlow;
import com.shopping.mall.entity.OrderItem;
import com.shopping.mall.entity.dto.OrderDTO;
import com.shopping.mall.util.AjaxPutPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhulin
 * @since 2019-12-01
 */
public interface OrderService extends IService<Order> {

    public boolean createOneOrder(Order order, List<OrderItem> orderItems, List<Integer> commodityIds, Integer userId);

    public boolean updateStatusOrFlow(Order order, OrderFlow orderFlow);

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
