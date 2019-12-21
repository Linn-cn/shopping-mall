package com.shopping.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author zhulin
 * @since 2019-11-25
 */
@TableName("shooping_mall_commodity")
public class Commodity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    @TableId(value = "commodity_id", type = IdType.AUTO)
    private Integer commodityId;

    /**
     * 商品名称
     */
    @TableField("commodity_name")
    private String commodityName;

    /**
     * 关联类别id
     */
    @TableField("category_id")
    private Integer categoryId;

    /**
     * 关联类别名称
     */
    @TableField("category_name")
    private String categoryName;

    /**
     * 封面图片
     */
    @TableField("cover_img")
    private String coverImg;

    /**
     * 展示图片
     */
    @TableField("show_img")
    private String showImg;

    /**
     * 商品简介
     */
    @TableField("commodity_intro")
    private String commodityIntro;

    /**
     * 销售数量
     */
    @TableField("sales_volume")
    private Integer salesVolume;

    /**
     * 商品价格
     */
    @TableField("original_price")
    private BigDecimal originalPrice;

    /**
     * 商品实际售价
     */
    @TableField("selling_price")
    private BigDecimal sellingPrice;

    /**
     * 商品详情
     */
    @TableField("detail_content")
    private String detailContent;

    /**
     * 上架状态
     */
    @TableField("commodity_status")
    private Integer commodityStatus;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;


    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public String getShowImg() {
        return showImg;
    }

    public void setShowImg(String showImg) {
        this.showImg = showImg;
    }

    public String getCommodityIntro() {
        return commodityIntro;
    }

    public void setCommodityIntro(String commodityIntro) {
        this.commodityIntro = commodityIntro;
    }

    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getDetailContent() {
        return detailContent;
    }

    public void setDetailContent(String detailContent) {
        this.detailContent = detailContent;
    }

    public Integer getCommodityStatus() {
        return commodityStatus;
    }

    public void setCommodityStatus(Integer commodityStatus) {
        this.commodityStatus = commodityStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "commodityId=" + commodityId +
                ", commodityName='" + commodityName + '\'' +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", coverImg='" + coverImg + '\'' +
                ", showImg='" + showImg + '\'' +
                ", commodityIntro='" + commodityIntro + '\'' +
                ", salesVolume=" + salesVolume +
                ", originalPrice=" + originalPrice +
                ", sellingPrice=" + sellingPrice +
                ", detailContent='" + detailContent + '\'' +
                ", commodityStatus=" + commodityStatus +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
