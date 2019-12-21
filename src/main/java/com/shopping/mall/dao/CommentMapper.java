package com.shopping.mall.dao;

import com.shopping.mall.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shopping.mall.entity.dto.CommentDTO;
import com.shopping.mall.util.AjaxPutPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhulin
 * @since 2019-11-25
 */
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 根据商品id返回评论列表
     * @param ajaxPutPage
     * @return java.util.List<com.shopping.mall.entity.dto.CommentDTO>
     * @date 2019/11/30 21:32
     */
    List<CommentDTO> pageCommentByCommodityId(@Param("page")AjaxPutPage ajaxPutPage,
                                              @Param("commodityId") Integer commodityId);

}
