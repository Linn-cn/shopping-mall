package com.shopping.mall.entity.dto;

import com.shopping.mall.entity.Commodity;

import java.io.Serializable;

/**
 * @program: shopping-mall
 * @classname: CommodityDTO
 * @description: 商品筛选条件
 * @author: 朱林
 * @create: 2019-11-27 21:52
 **/
public class CommodityDTO extends Commodity implements Serializable {

    private Integer isSalesVolume;

    private Integer isCreateTime;

    private Integer isSellingPrice;

    private String searchCondition;

    private Integer buyCounts;

    public Integer getBuyCounts() {
        return buyCounts;
    }

    public CommodityDTO setBuyCounts(Integer buyCounts) {
        this.buyCounts = buyCounts;
        return this;
    }

    public Integer getIsSalesVolume() {
        return isSalesVolume;
    }

    public CommodityDTO setIsSalesVolume(Integer isSalesVolume) {
        this.isSalesVolume = isSalesVolume;
        return this;
    }

    public Integer getIsCreateTime() {
        return isCreateTime;
    }

    public CommodityDTO setIsCreateTime(Integer isCreateTime) {
        this.isCreateTime = isCreateTime;
        return this;
    }

    public Integer getIsSellingPrice() {
        return isSellingPrice;
    }

    public CommodityDTO setIsSellingPrice(Integer isSellingPrice) {
        this.isSellingPrice = isSellingPrice;
        return this;
    }

    public String getSearchCondition() {
        return searchCondition;
    }

    public CommodityDTO setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition;
        return this;
    }
}
