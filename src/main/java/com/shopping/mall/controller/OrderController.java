package com.shopping.mall.controller;


import com.shopping.mall.constant.ApplicationConstants;
import com.shopping.mall.constant.HttpStatusConstants;
import com.shopping.mall.constant.OrderStatusEnum;
import com.shopping.mall.entity.Commodity;
import com.shopping.mall.entity.Order;
import com.shopping.mall.entity.OrderItem;
import com.shopping.mall.entity.User;
import com.shopping.mall.entity.dto.CommodityDTO;
import com.shopping.mall.entity.dto.OrderDTO;
import com.shopping.mall.entity.mapper.CommodityMapper;
import com.shopping.mall.entity.vo.OrderItemVO;
import com.shopping.mall.exception.BizException;
import com.shopping.mall.service.CommodityService;
import com.shopping.mall.service.OrderService;
import com.shopping.mall.service.ShoppCartService;
import com.shopping.mall.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhulin
 * @since 2019-12-01
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private ShoppCartService shoppCartService;

    @Autowired
    private HttpSession session;

    /**
     * 创建订单
     *
     * @return com.shopping.mall.util.Result<java.lang.String>
     * @date 2019/12/1 14:30
     */
    @PostMapping("/createOrder")
    public Result<Order> createOrder(@RequestBody OrderItemVO orderItemVO) {
        List<OrderItem> orderItems = orderItemVO.getOrderItems();
        orderItems.forEach(System.out::println);
        User user = (User) session.getAttribute(ApplicationConstants.MALL_USER_SESSION_KEY);
        List<CommodityDTO> commodityDTOS = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            orderItem.setCreateTime(DateUtils.getLocalDateTime());
            Commodity commodity = commodityService.getById(orderItem.getCommodityId());
            CommodityDTO commodityDTO = CommodityMapper.COMMODITY_MAPPER.commodityToCommodityDTO(commodity);
            commodityDTOS.add(commodityDTO.setBuyCounts(orderItem.getBuyCounts()));
        }
        Optional<BigDecimal> orderAmount = commodityDTOS.stream().map(commodityDTO ->
                commodityDTO.getSellingPrice().multiply(new BigDecimal(commodityDTO.getBuyCounts().toString()))
        ).reduce(BigDecimal::add);
        Order order = new Order();
        try {
            order.setUserId(user.getUserId())
                    .setOrderNum(DateUtils.getRandomNumber())
                    .setOrderAmount(orderAmount.get())
                    .setUserAddress(orderItemVO.getUserAddress())
                    .setOrderStatus(OrderStatusEnum.WAIT_PAY.key)
                    .setCreateTime(DateUtils.getLocalDateTime());
        } catch (RuntimeException e) {
            throw new BizException("生成订单失败！");
        }
        List<Integer> commodityIds = commodityDTOS.stream().map(CommodityDTO::getCommodityId).collect(Collectors.toList());
        orderService.createOneOrder(order, orderItems,commodityIds,user.getUserId());
        return ResultGenerator.getResultByHttp(HttpStatusConstants.OK, order);
    }

    /**
     * 跳转单商品订单创建界面
     *
     * @return java.lang.String
     * @date 2019/11/29 15:05
     */
    @PostMapping("/addCommodityOrder")
    public Result<String> addCommodityOrder(Integer commodityId, Integer buyCounts) {
        User user = (User) session.getAttribute(ApplicationConstants.MALL_USER_SESSION_KEY);
        Commodity commodity = commodityService.getById(commodityId);
        CommodityDTO commodityDTO = CommodityMapper.COMMODITY_MAPPER.commodityToCommodityDTO(commodity);
        commodityDTO.setBuyCounts(buyCounts);
        List<CommodityDTO> commodities = Collections.singletonList(commodityDTO);
        BigDecimal orderAmount = commodity.getSellingPrice().multiply(new BigDecimal(buyCounts.toString()));
        Order order = new Order()
                .setOrderAmount(orderAmount)
                .setOrderStatus(OrderStatusEnum.WAIT_PAY.key)
                .setUserAddress(user.getUserAddress());
        session.setAttribute("commodities", commodities);
        session.setAttribute("order", order);
        return ResultGenerator.getResultByHttp(HttpStatusConstants.OK);
    }

    /**
     * 跳转多商品订单创建界面
     *
     * @return java.lang.String
     * @date 2019/11/29 15:05
     */
    @PostMapping("/addCartToOrder")
    public Result<String> addCartToOrder(@RequestBody List<OrderItem> orderItems) {
        orderItems.forEach(orderItem -> System.out.println("1：" + orderItem.toString()));
        User user = (User) session.getAttribute(ApplicationConstants.MALL_USER_SESSION_KEY);
        List<CommodityDTO> commodityDTOS = new ArrayList<>();
        for (OrderItem orderItem : orderItems) {
            Commodity commodity = commodityService.getById(orderItem.getCommodityId());
            CommodityDTO commodityDTO = CommodityMapper.COMMODITY_MAPPER.commodityToCommodityDTO(commodity);
            commodityDTOS.add(commodityDTO.setBuyCounts(orderItem.getBuyCounts()));
        }
        Optional<BigDecimal> orderAmount = commodityDTOS.stream().map(commodityDTO ->
                commodityDTO.getSellingPrice().multiply(new BigDecimal(commodityDTO.getBuyCounts().toString()))
        ).reduce(BigDecimal::add);
        Order order = new Order();
        try {
            order.setOrderAmount(orderAmount.get()).setOrderStatus(OrderStatusEnum.WAIT_PAY.key)
                    .setUserAddress(user.getUserAddress());
        } catch (RuntimeException e) {
            throw new BizException("生成订单失败！");
        }
        session.setAttribute("commodities", commodityDTOS);
        session.setAttribute("order", order);
        return ResultGenerator.getResultByHttp(HttpStatusConstants.OK);
    }


    /**
     * 订单分页
     *
     * @param ajaxPutPage
     * @param userId
     * @return com.shopping.mall.util.AjaxResultPage<com.shopping.mall.entity.dto.OrderDTO>
     * @date 2019/12/3 10:26
     */
    @GetMapping("/page")
    public AjaxResultPage<OrderDTO> pageOrder(AjaxPutPage ajaxPutPage, Integer userId) {
        List<OrderDTO> orderDTOS = orderService.pageOrderByUserId(ajaxPutPage, userId);
        return new AjaxResultPage<OrderDTO>().setCount(orderDTOS.size()).setData(orderDTOS);
    }

}

