package com.shopping.mall.entity.mapper;

import com.alipay.api.domain.GoodsDetail;
import com.shopping.mall.entity.Commodity;
import com.shopping.mall.entity.dto.CommodityDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @program: shopping-mall
 * @classname: CommodityMapper
 * @description: 商品实体类转换
 * @author: 朱林
 * @create: 2019-11-28 10:23
 **/
@Mapper
public interface CommodityMapper {

    CommodityMapper COMMODITY_MAPPER = Mappers.getMapper(CommodityMapper.class);


    CommodityDTO commodityToCommodityDTO(Commodity commodity);
    
    Commodity commodityDTOToCommodity(CommodityDTO commodityDTO);

    @Mappings({
            @Mapping(target = "goodsId",source = "commodityId"),
            @Mapping(target = "goodsName",source = "commodityName"),
            @Mapping(target = "price",source = "sellingPrice")
    })
    GoodsDetail commodityToGoodsDetail(Commodity commodity);
}
