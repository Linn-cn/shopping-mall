package com.shopping.mall.service.impl;

import com.shopping.mall.entity.Comment;
import com.shopping.mall.dao.CommentMapper;
import com.shopping.mall.entity.dto.CommentDTO;
import com.shopping.mall.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shopping.mall.util.AjaxPutPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhulin
 * @since 2019-11-25
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<CommentDTO> pageCommentByCommodityId(AjaxPutPage ajaxPutPage, Integer commodityId) {
        List<CommentDTO> commentDTOS = commentMapper.pageCommentByCommodityId(ajaxPutPage, commodityId);
        if (CollectionUtils.isEmpty(commentDTOS) || commentDTOS.get(0) == null){
            return Collections.emptyList();
        }
        return commentDTOS;
    }
}
