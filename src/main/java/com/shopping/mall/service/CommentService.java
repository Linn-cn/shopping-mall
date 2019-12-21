package com.shopping.mall.service;

import com.shopping.mall.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shopping.mall.entity.dto.CommentDTO;
import com.shopping.mall.util.AjaxPutPage;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhulin
 * @since 2019-11-25
 */
public interface CommentService extends IService<Comment> {

    /**
     * 根据商品id返回评论列表
     * @param ajaxPutPage
     * @return java.util.List<com.shopping.mall.entity.dto.CommentDTO>
     * @date 2019/11/30 21:32
     */
    List<CommentDTO> pageCommentByCommodityId(AjaxPutPage ajaxPutPage, Integer commodityId);

}
