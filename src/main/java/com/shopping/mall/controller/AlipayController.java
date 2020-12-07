package com.shopping.mall.controller;

import com.alipay.api.AlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopping.mall.config.AlipayConfig;
import com.shopping.mall.constant.OrderStatusEnum;
import com.shopping.mall.constant.PaidMethodEnum;
import com.shopping.mall.entity.*;
import com.shopping.mall.entity.dto.CommodityDTO;
import com.shopping.mall.entity.mapper.CommodityMapper;
import com.shopping.mall.exception.BizException;
import com.shopping.mall.service.CommodityService;
import com.shopping.mall.service.OrderItemService;
import com.shopping.mall.service.OrderService;
import com.shopping.mall.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 支付回调Controller
 * @author Linn-cn
 * @date 2020/12/7
 */
@Controller
@RequestMapping("/alipay")
public class AlipayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private HttpSession session;


    /**
     * @Title: AlipayController.java
     * @Package com.sihai.controller
     * @Description: 前往支付宝第三方网关进行支付
     * @date 2017年8月23日 下午8:50:43
     * @version V1.0
     */
    @RequestMapping(value = "/goAlipay", produces = "text/html; charset=UTF-8")
    @ResponseBody
    public String goAlipay(Integer orderId, HttpServletRequest request, HttpServletRequest response) throws Exception {

        Order order = orderService.getById(orderId);

        List<OrderItem> orderItems = orderItemService.list(new QueryWrapper<OrderItem>().lambda()
                .eq(OrderItem::getOrderId, orderId));

        Optional<Integer> reduce = orderItems.stream().map(OrderItem::getBuyCounts).reduce(Integer::sum);
        if (!reduce.isPresent()) {
            throw new BizException("计算失败！");
        }

        //获得初始化的AlipayClient
        AlipayClient alipayClient = AlipayConfig.getDefaultAlipayClient();

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = AlipayConfig.getAlipayTradePagePayRequest();

		BizContent bizContent = new BizContent()
				//商户订单号，商户网站订单系统中唯一订单号，必填
				.setOut_trade_no(order.getOrderNum())
				//付款金额，必填
				.setTotal_amount(String.valueOf(order.getOrderAmount()))
				//订单名称，必填
				.setSubject(order.getOrderNum())
				//商品描述，可空
				.setBody("用户订购商品个数：" + reduce.get())
				// 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。
				.setTimeout_express("30m")
                .setProduct_code("FAST_INSTANT_TRADE_PAY");
        System.out.println(new ObjectMapper().writeValueAsString(bizContent));
        alipayRequest.setBizContent(new ObjectMapper().writeValueAsString(bizContent));

        //请求

        return alipayClient.pageExecute(alipayRequest).getBody();
    }

    /**
     *
     * @Title: AlipayController.java
     * @Package com.sihai.controller
     * @Description: 支付宝同步通知页面
     * Copyright: Copyright (c) 2017
     * Company:FURUIBOKE.SCIENCE.AND.TECHNOLOGY
     *
     * @author sihai
     * @date 2017年8月23日 下午8:51:01
     * @version V1.0
     */
	@RequestMapping(value = "/alipayReturnNotice")
	public String alipayReturnNotice(HttpServletRequest request,
                                     HttpServletRequest response,
                                     Model model) throws Exception {
        System.out.println(("支付成功, 进入同步通知接口..."));

		//获取支付宝GET过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		requestParams.forEach((key, values) -> {
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
//            try {
//                valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
//            } catch (UnsupportedEncodingException e) {
//                throw new BizException("编码转换异常！");
//            }
            params.put(key, valueStr);
        });
		params.forEach((key,value) -> {
            System.out.println("1key:" + key + ",value:" + value);
        });
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
        if (signVerified) {
            // 商户订单号
            String out_trade_no = params.get("out_trade_no");
            //支付宝交易号
            String trade_no = params.get("trade_no");
            //付款金额
            String total_amount = params.get("total_amount");
            OrderFlow orderFlow = new OrderFlow()
                    .setFlowNum(trade_no)
                    .setOrderNum(out_trade_no)
                    .setCreateTime(DateUtils.getLocalDateTime())
                    .setPaidAmount(total_amount)
                    .setPaidMethod(PaidMethodEnum.ALIPAY.key);
            // 获得对应订单
            Order order = orderService.getOne(new QueryWrapper<Order>().lambda().eq(Order::getOrderNum,out_trade_no));
            // 更新订单状态
            order = order.setOrderStatus(OrderStatusEnum.PAID.key)
                    .setPaidTime(DateUtils.getLocalDateTime());
            boolean flag = orderService.updateStatusOrFlow(order,orderFlow);
            if (flag){
                List<OrderItem> orderItems = orderItemService.list(new QueryWrapper<OrderItem>().lambda()
                        .eq(OrderItem::getOrderId, order.getOrderId()));
                List<CommodityDTO> commodityDTOS = new ArrayList<>();
                for (OrderItem orderItem : orderItems) {
                    Commodity commodity = commodityService.getById(orderItem.getCommodityId());
                    CommodityDTO commodityDTO = CommodityMapper.COMMODITY_MAPPER.commodityToCommodityDTO(commodity);
                    commodityDTOS.add(commodityDTO.setBuyCounts(orderItem.getBuyCounts()));
                }
                session.setAttribute("commodities",commodityDTOS);
                session.setAttribute("order", order);
            }else{
                session.setAttribute("errorMessage","支付失败！");
            }
        }

        return "create_order";
	}

    /**
     *
     * @Title: AlipayController.java
     * @Package com.sihai.controller
     * @Description: 支付宝异步 通知页面
     * Copyright: Copyright (c) 2017
     * Company:FURUIBOKE.SCIENCE.AND.TECHNOLOGY
     *
     * @author sihai
     * @date 2017年8月23日 下午8:51:13
     * @version V1.0
     */
	@RequestMapping(value = "/alipayNotifyNotice")
	@ResponseBody
	public String alipayNotifyNotice(HttpServletRequest request, HttpServletRequest response) throws Exception {

        System.out.println(("支付成功, 进入异步通知接口..."));

        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        requestParams.forEach((key, values) -> {
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
//            try {
//                valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
//            } catch (UnsupportedEncodingException e) {
//                throw new BizException("编码转换异常！");
//            }
            params.put(key, valueStr);
        });

        params.forEach((key,value) -> {
            System.out.println("2key:" + key + ",value:" + value);
        });


        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

		//——请在这里编写您的程序（以下代码仅作参考）——
		
//		 实际验证过程建议商户务必添加以下校验：
//		1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
//		2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
//		3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
//		4、验证app_id是否为该商户本身。

		if(signVerified) {//验证成功
			//商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

			//支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

			//交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

			//付款金额
			String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

			if(trade_status.equals("TRADE_FINISHED")){
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序

				//注意： 尚自习的订单没有退款功能, 这个条件判断是进不来的, 所以此处不必写代码
				//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
			}else if (trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序

				//注意：
				//付款完成后，支付宝系统发送该交易状态通知

				// 修改叮当状态，改为 支付成功，已付款; 同时新增支付流水
//				orderService.updateOrderStatus(out_trade_no, trade_no, total_amount);
//
//				Orders order = orderService.getOrderById(out_trade_no);
//				Product product = productService.getProductById(order.getProductId());

                System.out.println(("********************** 支付成功(支付宝异步通知) **********************"));
//                System.out.println(("* 订单号: {}"), out_trade_no);
//                System.out.println(("* 支付宝交易号: {}"), trade_no);
//                System.out.println(("* 实付金额: {}"), total_amount);
//                System.out.println(("* 购买产品: {}"), product.getName());
                System.out.println(("***************************************************************"));
			}
            System.out.println(("支付成功..."));

		}else {//验证失败
            System.out.println(("支付, 验签失败..."));
		}

		return "success";
	}

}
