package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @ClassName SpuSaleAttrService
 * @Description TODO
 * @Author qing
 * @Date 2023/4/6 20:58
 * @Version 1.0
 */
public interface SpuSaleAttrService extends IService<SpuSaleAttr> {
    void saveSpuSaleAttrList(List<SpuSaleAttr> spuSaleAttrList);

    List<SpuSaleAttr> getSpuSaleAttrList(Long spuId);
}
