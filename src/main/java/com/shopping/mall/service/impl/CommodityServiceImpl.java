package com.shopping.mall.service.impl;

import com.shopping.mall.entity.Commodity;
import com.shopping.mall.dao.CommodityMapper;
import com.shopping.mall.service.CommodityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhulin
 * @since 2019-11-25
 */
@Service
public class CommodityServiceImpl extends ServiceImpl<CommodityMapper, Commodity> implements CommodityService {

    @Autowired
    private CommodityMapper commodityMapper;

}
