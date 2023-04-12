package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SkuManageService
 * @Description TODO
 * @Author qing
 * @Date 2023/4/8 0:55
 * @Version 1.0
 */
public interface SkuManageService {
    SkuInfo getSkuInfo(Long skuId);

    BaseCategoryView getCategoryView(Long category3Id);

    BigDecimal getSkuPrice(Long skuId);

    List<SpuPoster> findSpuPosterBySpuId(Long spuId);

    List<BaseAttrInfo> getAttrList(Long skuId);

    Map getSkuValueIdsMap(Long spuId);

    List<SpuSaleAttr> getSpuSaleAttrListCheckBySku(Long skuId, Long spuId);

    List<SpuPoster> getSpuPosterBySpuId(Long spuId);
}
