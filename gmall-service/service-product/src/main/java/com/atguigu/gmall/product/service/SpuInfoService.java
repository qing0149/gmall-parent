package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.SpuInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @ClassName SpuInfoService
 * @Description TODO
 * @Author qing
 * @Date 2023/4/6 19:35
 * @Version 1.0
 */
public interface SpuInfoService extends IService<SpuInfo> {
    SpuInfo selectAllById(Long id);
}
