package com.shopping.mall.entity.dto;

import com.shopping.mall.entity.Category;

import java.io.Serializable;
import java.util.List;

/**
 * @program: shopping-mall
 * @classname: CatrgoryDTO
 * @description:
 * @author: 朱林
 * @create: 2019-11-27 14:47
 **/
public class CategoryDTO extends Category implements Serializable {

    private List<Category> twoCategorys;

    public List<Category> getTwoCategorys() {
        return twoCategorys;
    }

    public void setTwoCategorys(List<Category> twoCategorys) {
        this.twoCategorys = twoCategorys;
    }
}
