package com.shopping.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.shopping.mall.validate.CommentAdd;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
@TableName("shooping_mall_comment")
public class Comment implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 评论id
     */
    @TableId(value = "comment_id", type = IdType.AUTO)
    private Integer commentId;

    /**
     * 关联商品id
     */
    @TableField("commodity_id")
    @NotNull(message = "商品id不能为空",groups = CommentAdd.class)
    private Integer commodityId;

    /**
     * 关联用户id
     */
    @TableField("user_id")
    private Integer userId;

    /**
     * 评论内容
     */
    @TableField("comment_content")
    @NotBlank(message = "评论内容不能为空",groups = CommentAdd.class)
    private String commentContent;

    /**
     * 创建时间
     */
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy/MM/dd hh:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;


    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
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
        return "Comment{" +
        "commentId=" + commentId +
        ", commodityId=" + commodityId +
        ", userId=" + userId +
        ", commentContent=" + commentContent +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
