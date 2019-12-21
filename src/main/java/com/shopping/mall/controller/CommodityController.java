package com.shopping.mall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shopping.mall.constant.HttpStatusConstants;
import com.shopping.mall.entity.Commodity;
import com.shopping.mall.entity.dto.CommodityDTO;
import com.shopping.mall.entity.mapper.CommodityMapper;
import com.shopping.mall.service.CommodityService;
import com.shopping.mall.util.AjaxPutPage;
import com.shopping.mall.util.AjaxResultPage;
import com.shopping.mall.util.Result;
import com.shopping.mall.util.ResultGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhulin
 * @since 2019-11-25
 */
@RestController
@RequestMapping("/commodity")
@Validated
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    /**
     * 分页条件显示商品
     * 条件：支持排序、模糊查询
     * @param ajaxPutPage
     * @param condition
     * @return com.shopping.mall.util.AjaxResultPage<com.shopping.mall.entity.Commodity>
     * @date 2019/11/27 20:26
     */
    @GetMapping("/page")
    public AjaxResultPage<Commodity> pageCommodity(AjaxPutPage ajaxPutPage,
                                                   CommodityDTO condition) {
        Page<Commodity> page = ajaxPutPage.putPageToPage();
        Commodity commodity = CommodityMapper.COMMODITY_MAPPER.commodityDTOToCommodity(condition);
        QueryWrapper<Commodity> wrapper = new QueryWrapper<>();
        if (condition.getIsSalesVolume() != null && condition.getIsSalesVolume() == 1) {
            wrapper.lambda().orderByAsc(Commodity::getSalesVolume);
        }
        if (condition.getIsCreateTime() != null && condition.getIsCreateTime() == 1) {
            wrapper.lambda().orderByAsc(Commodity::getCreateTime);
        }
        if (condition.getIsSellingPrice() != null && condition.getIsSellingPrice() == 1) {
            wrapper.lambda().orderByAsc(Commodity::getSellingPrice);
        }
        if (StringUtils.isNotBlank(condition.getSearchCondition())) {
            wrapper.lambda().like(Commodity::getCommodityName, condition.getSearchCondition())
                    .or().like(Commodity::getCommodityIntro, condition.getSearchCondition());
        }
        wrapper.setEntity(commodity);
        commodityService.page(page, wrapper);
        AjaxResultPage<Commodity> ajaxResultPage = new AjaxResultPage<>();
        ajaxResultPage.setCount(page.getTotal());
        ajaxResultPage.setData(page.getRecords());
        return ajaxResultPage;
    }

    /**
     * 多条件返回对应商品的总数
     * @param categoryId
     * @return com.shopping.mall.util.Result<java.lang.Integer>
     * @date 2019/11/27 20:45
     */
    @GetMapping("/pageCount")
    public Result<Integer> getPageCommodityCount(Integer categoryId,String searchCondition) {
        QueryWrapper<Commodity> wrapper = new QueryWrapper<>();
        if (categoryId != null) {
            wrapper.lambda().eq(Commodity::getCategoryId, categoryId);
        }
        if (StringUtils.isNotBlank(searchCondition)) {
            wrapper.lambda().like(Commodity::getCommodityName, searchCondition)
                    .or((i) -> i.like(Commodity::getCommodityIntro, searchCondition));
        }
        return ResultGenerator.getResultByHttp(HttpStatusConstants.OK, commodityService.count(wrapper));
    }

}

