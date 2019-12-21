package com.shopping.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shopping.mall.dao.ShoppCartMapper;
import com.shopping.mall.entity.ShoppCart;
import com.shopping.mall.entity.dto.ShoppCartDTO;
import com.shopping.mall.service.ShoppCartService;
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
public class ShoppCartServiceImpl extends ServiceImpl<ShoppCartMapper, ShoppCart> implements ShoppCartService {

    @Autowired
    private ShoppCartMapper shoppCartMapper;

    @Override
    public List<ShoppCartDTO> listShoppCartByUserId(Integer userId) {
        List<ShoppCartDTO> shoppCartDTOS = shoppCartMapper.listShoppCartByUserId(userId);
        if (CollectionUtils.isEmpty(shoppCartDTOS) || shoppCartDTOS.get(0) == null){
            return Collections.emptyList();
        }
        return shoppCartDTOS;
    }
}
