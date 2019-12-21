package com.shopping.mall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shopping.mall.constant.ApplicationConstants;
import com.shopping.mall.constant.HttpStatusConstants;
import com.shopping.mall.entity.ShoppCart;
import com.shopping.mall.entity.User;
import com.shopping.mall.service.CommodityService;
import com.shopping.mall.service.ShoppCartService;
import com.shopping.mall.util.DateUtils;
import com.shopping.mall.util.Result;
import com.shopping.mall.util.ResultGenerator;
import com.shopping.mall.validate.ShoppAdd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhulin
 * @since 2019-11-25
 */
@RestController
@RequestMapping("/shoppCart")
@Validated
public class ShoppCartController {

    @Autowired
    private ShoppCartService shoppCartService;

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private HttpSession session;

    /**
     * 修改购物车商品数量
     * @param cartId
     * @param commodityCount
     * @return com.shopping.mall.util.Result<java.lang.String>
     * @date 2019/11/29 17:02
     */
    @PostMapping("/updateCount")
    public Result<String> updateCommodityCount(Integer cartId,
                                               Integer commodityCount) {

        boolean flag = shoppCartService.updateById(new ShoppCart()
                .setCartId(cartId)
                .setCommodityCount(commodityCount));
        return flag ? ResultGenerator.getResultByHttp(HttpStatusConstants.OK)
                : ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR);
    }

    /**
     * 删除购物车商品
     *
     * @param cartId
     * @return com.shopping.mall.util.Result<java.lang.String>
     * @date 2019/11/29 16:38
     */
    @PostMapping("/delete")
    public Result<String> deleteShoppCart(Integer cartId) {
        return shoppCartService.removeById(cartId)
                ? ResultGenerator.getResultByHttp(HttpStatusConstants.OK)
                : ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR);
    }

    /**
     * 批量删除购物车商品
     * @param cartIds
     * @return com.shopping.mall.util.Result<java.lang.String>
     * @date 2019/11/29 23:31
     */
    @PostMapping("/deleteBatch")
    public Result<String> deleteBatchCart(@RequestParam("cartIds[]") List<Integer> cartIds){
        return shoppCartService.removeByIds(cartIds)
                ? ResultGenerator.getResultByHttp(HttpStatusConstants.OK)
                : ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR);
    }


    /**
     * 添加购物车
     * @param shoppCart
     * @return com.shopping.mall.util.Result<java.lang.String>
     * @date 2019/11/30 16:41
     */
    @PostMapping("/add")
    public Result<String> addShoppCart(@Validated(ShoppAdd.class) ShoppCart shoppCart){
        User user = (User) session.getAttribute(ApplicationConstants.MALL_USER_SESSION_KEY);
        shoppCart.setUserId(user.getUserId());
        QueryWrapper<ShoppCart> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(ShoppCart::getCommodityId,shoppCart.getCommodityId())
                        .eq(ShoppCart::getUserId,user.getUserId());
        ShoppCart existCart = shoppCartService.getOne(wrapper);
        if (existCart != null){
            shoppCart.setCartId(existCart.getCartId());
            shoppCart.setCommodityCount(existCart.getCommodityCount() + shoppCart.getCommodityCount());
        }else {
            shoppCart.setCreateTime(DateUtils.getLocalDateTime());
        }
        return shoppCartService.saveOrUpdate(shoppCart)
                ? ResultGenerator.getResultByHttp(HttpStatusConstants.OK)
                : ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR);
    }

}

