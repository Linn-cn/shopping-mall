package com.shopping.mall.entity.dto;

import com.shopping.mall.entity.Comment;
import com.shopping.mall.entity.User;

/**
 * @program: shopping-mall
 * @classname: CommentDTO
 * @description:
 * @author: 朱林
 * @create: 2019-11-30 21:04
 **/
public class CommentDTO extends Comment {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
