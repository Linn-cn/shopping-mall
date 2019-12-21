package com.shopping.mall.controller;


import com.shopping.mall.constant.ApplicationConstants;
import com.shopping.mall.constant.HttpStatusConstants;
import com.shopping.mall.entity.Comment;
import com.shopping.mall.entity.User;
import com.shopping.mall.entity.dto.CommentDTO;
import com.shopping.mall.service.CommentService;
import com.shopping.mall.util.*;
import com.shopping.mall.validate.CommentAdd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhulin
 * @since 2019-11-25
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private HttpSession session;


    /**
     * 分页显示评论列表
     * @param ajaxPutPage
     * @param commodityId
     * @return com.shopping.mall.util.AjaxResultPage<com.shopping.mall.entity.dto.CommentDTO>
     * @date 2019/11/30 21:47
     */
    @GetMapping("/page")
    public AjaxResultPage<CommentDTO> pageComment(AjaxPutPage ajaxPutPage, Integer commodityId){
        List<CommentDTO> commentDTOS = commentService.pageCommentByCommodityId(ajaxPutPage, commodityId);
        return new AjaxResultPage<CommentDTO>().setCount(commentDTOS.size()).setData(commentDTOS);
    }

    /**
     * 添加评论
     * @param comment
     * @return com.shopping.mall.util.Result<java.lang.String>
     * @date 2019/12/1 22:54
     */
    @PostMapping("/add")
    public Result<String> addComment(@Validated(CommentAdd.class) Comment comment){
        User user = (User)session.getAttribute(ApplicationConstants.MALL_USER_SESSION_KEY);
        comment.setUserId(user.getUserId());
        comment.setCreateTime(DateUtils.getLocalDateTime());
        boolean flag = commentService.save(comment);
        return flag ? ResultGenerator.getResultByHttp(HttpStatusConstants.OK)
                : ResultGenerator.getResultByHttp(HttpStatusConstants.INTERNAL_SERVER_ERROR);
    }

}

