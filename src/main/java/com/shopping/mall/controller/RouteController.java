package com.shopping.mall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shopping.mall.entity.Comment;
import com.shopping.mall.entity.Commodity;
import com.shopping.mall.entity.Order;
import com.shopping.mall.entity.dto.CategoryDTO;
import com.shopping.mall.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @program: shopping-mall
 * @classname: RouteController
 * @description: Application页面路由控制器
 * @author: 朱林
 * @create: 2019-11-27 15:17
 **/
@Controller
public class RouteController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ShoppCartService shoppCartService;

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private OrderService orderService;

    /**
     * 跳转登录页面
     *
     * @return java.lang.String
     * @date 2019/11/26 17:35
     */
    @GetMapping("/gotoLogin")
    public String gotoLogin() {
        return "login";
    }

    /**
     * 跳转商品页面
     *
     * @return java.lang.String
     * @date 2019/11/26 17:35
     */
    @GetMapping("/gotoCommodity")
    public String gotoCommodity(Model model, Integer categoryId, String searchCondition) {
        List<CategoryDTO> listCategory = categoryService.listCategory();
        model.addAttribute("listCategory", listCategory);
        if (categoryId != null) model.addAttribute("categoryId", categoryId);
        if (StringUtils.isNotBlank(searchCondition)) model.addAttribute("searchCondition", searchCondition);
        return "commodity";
    }

    /**
     * 刷新导航栏
     *
     * @param
     * @return java.lang.String
     * @date 2019/11/30 12:07
     */
    @GetMapping("/refreshNav")
    public String refreshNav() {
        return "common::navigationBar";
    }

    /**
     * 跳转用户信息修改界面
     *
     * @return java.lang.String
     * @date 2019/11/28 15:05
     */
    @GetMapping("/gotoUpdateUser")
    public String gotoUpdateUser() {
        return "user_update";
    }

    /**
     * 跳转购物车界面
     *
     * @return java.lang.String
     * @date 2019/11/29 15:05
     */
    @GetMapping("/gotoShopCart")
    public String gotoShopCart(Integer userId, Model model) {
        model.addAttribute("shoppCarts", shoppCartService.listShoppCartByUserId(userId));
        return "shop_cart";
    }

    /**
     * 跳转商品详情界面
     *
     * @param commodityId
     * @return
     */
    @GetMapping("/gotoCommodityDetail")
    public String gotoCommodityDetail(Integer commodityId, Integer categoryId, Model model) {
        Commodity commodity = commodityService.getById(commodityId);
        QueryWrapper<Commodity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Commodity::getCategoryId, categoryId)
                .ne(Commodity::getCommodityId, commodityId)
                .orderByDesc(Commodity::getSalesVolume)
                .last("limit 5");
        List<Commodity> commodities = commodityService.list(wrapper);
        model.addAttribute("commentCount", commentService.count(
                new QueryWrapper<Comment>().lambda().eq(Comment::getCommodityId, commodityId)
        ));
        model.addAttribute("commodity", commodity);
        model.addAttribute("commodities", commodities);
        return "details";
    }

    /**
     * 跳转商品订单创建界面
     *
     * @return java.lang.String
     * @date 2019/11/29 15:05
     */
    @GetMapping("/gotoCreateOrder")
    public String gotoCreateOrder() {
        return "create_order";
    }

    /**
     * 跳转订单列表界面
     *
     * @return java.lang.String
     * @date 2019/12/3 14:39
     */
    @GetMapping("/gotoOrderList")
    public String gotoOrderList(Integer userId, Model model) {
        model.addAttribute("orderCount", orderService.count(
                new QueryWrapper<Order>().lambda().eq(Order::getUserId, userId)
        ));
        return "order_list";
    }


}
