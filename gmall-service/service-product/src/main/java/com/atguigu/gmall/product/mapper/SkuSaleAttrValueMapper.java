package com.atguigu.gmall.product.mapper;

import com.atguigu.gmall.model.product.SkuSaleAttrValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @ClassName SkuSaleAttrValueMapper
 * @Description TODO
 * @Author qing
 * @Date 2023/4/7 14:41
 * @Version 1.0
 */
@Repository
public interface SkuSaleAttrValueMapper extends BaseMapper<SkuSaleAttrValue> {
    List<Map> selectSkuValueIdsMap(Long spuId);
}
