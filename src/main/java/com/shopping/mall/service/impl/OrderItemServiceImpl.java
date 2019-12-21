package com.shopping.mall.service.impl;

import com.shopping.mall.entity.OrderItem;
import com.shopping.mall.dao.OrderItemMapper;
import com.shopping.mall.service.OrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhulin
 * @since 2019-12-01
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

}
