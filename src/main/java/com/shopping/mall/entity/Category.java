package com.shopping.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhulin
 * @since 2019-11-25
 */
@TableName("shooping_mall_category")
public class Category implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 类别id
     */
    @TableId(value = "category_id", type = IdType.AUTO)
    private Integer categoryId;

    /**
     * 父类别id
     */
    @TableField("parent_id")
    private Integer parentId;

    /**
     * 类别名字
     */
    @TableField("category_name")
    private String categoryName;

    /**
     * 类别级别
     */
    @TableField("category_level")
    private Integer categoryLevel;

    /**
     * 排序
     */
    @TableField("category_rank")
    private Integer categoryRank;

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


    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCategoryLevel() {
        return categoryLevel;
    }

    public void setCategoryLevel(Integer categoryLevel) {
        this.categoryLevel = categoryLevel;
    }

    public Integer getCategoryRank() {
        return categoryRank;
    }

    public void setCategoryRank(Integer categoryRank) {
        this.categoryRank = categoryRank;
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
        return "Category{" +
        "categoryId=" + categoryId +
        ", parentId=" + parentId +
        ", categoryName=" + categoryName +
        ", categoryLevel=" + categoryLevel +
        ", categoryRank=" + categoryRank +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
