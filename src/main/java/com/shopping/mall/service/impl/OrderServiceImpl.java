package com.shopping.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.shopping.mall.dao.OrderFlowMapper;
import com.shopping.mall.dao.OrderItemMapper;
import com.shopping.mall.dao.OrderMapper;
import com.shopping.mall.dao.ShoppCartMapper;
import com.shopping.mall.entity.Order;
import com.shopping.mall.entity.OrderFlow;
import com.shopping.mall.entity.OrderItem;
import com.shopping.mall.entity.ShoppCart;
import com.shopping.mall.entity.dto.OrderDTO;
import com.shopping.mall.exception.BizException;
import com.shopping.mall.service.OrderService;
import com.shopping.mall.util.AjaxPutPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhulin
 * @since 2019-12-01
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderFlowMapper orderFlowMapper;

    @Autowired
    private ShoppCartMapper shoppCartMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean createOneOrder(Order order, List<OrderItem> orderItems, List<Integer> commodityIds,Integer userId) throws BizException {
        try {
            boolean flag = SqlHelper.retBool(orderMapper.insert(order));
            if (flag){
                for (OrderItem orderItem : orderItems){
                    orderItem.setOrderId(order.getOrderId());
                    flag = SqlHelper.retBool(orderItemMapper.insert(orderItem));
                }
                flag = SqlHelper.retBool(shoppCartMapper.delete(new UpdateWrapper<ShoppCart>().lambda().eq(ShoppCart::getUserId, userId)
                        .in(ShoppCart::getCommodityId, commodityIds)
                ));
                return flag;
            }
        } catch (DataAccessException e) {
            if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
                throw new BizException("当前订单已存在!");
            }
        }
        throw new BizException("生成订单失败！");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateStatusOrFlow(Order order, OrderFlow orderFlow) {
        try {
            return SqlHelper.retBool(orderMapper.updateById(order))
                    && SqlHelper.retBool(orderFlowMapper.insert(orderFlow));
        } catch (Exception e) {
            throw new BizException("生成订单失败！");
        }
    }

    @Override
    public List<OrderDTO> pageOrderByUserId(AjaxPutPage ajaxPutPage, Integer userId) {
        List<OrderDTO> orderDTOS = orderMapper.pageOrderByUserId(ajaxPutPage, userId);
        if (CollectionUtils.isEmpty(orderDTOS) || orderDTOS.get(0) == null){
            return Collections.emptyList();
        }
        return orderDTOS;
    }
}
