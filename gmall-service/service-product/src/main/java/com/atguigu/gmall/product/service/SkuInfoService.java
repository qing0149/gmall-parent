package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.SkuInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @ClassName SkuInfoService
 * @Description TODO
 * @Author qing
 * @Date 2023/4/7 10:36
 * @Version 1.0
 */
public interface SkuInfoService extends IService<SkuInfo> {
    void saveSkuInfoAll(SkuInfo skuInfo);

    Page<SkuInfo> selectSkuInfoPage(Page<SkuInfo> pageParam, Long category3Id);

    void updateOnSale(Long id);

    void updateSkuInfo(SkuInfo skuInfo);

    SkuInfo selectSkuInfoById(Long id);
}
