package com.shopping.mall.service.impl;

import com.shopping.mall.entity.Category;
import com.shopping.mall.dao.CategoryMapper;
import com.shopping.mall.entity.dto.CategoryDTO;
import com.shopping.mall.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryDTO> listCategory() {
        List<CategoryDTO> categoryDTOS = categoryMapper.listCategory();
        if (CollectionUtils.isEmpty(categoryDTOS) || categoryDTOS.get(0) == null){
            return Collections.emptyList();
        }
        return categoryDTOS;
    }
}
