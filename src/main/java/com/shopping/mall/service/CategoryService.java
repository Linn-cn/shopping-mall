package com.shopping.mall.service;

import com.shopping.mall.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shopping.mall.entity.dto.CategoryDTO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhulin
 * @since 2019-11-25
 */
public interface CategoryService extends IService<Category> {

    /**
     * 返回全部类别[包含一二级]
     * @param
     * @return java.util.List<com.shopping.mall.entity.dto.CatrgoryDTO>
     * @date 2019/11/27 14:51
     */
    List<CategoryDTO> listCategory();

}
