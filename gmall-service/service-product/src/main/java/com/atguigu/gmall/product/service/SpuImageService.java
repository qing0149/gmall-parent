package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.SpuImage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @ClassName SpuImageService
 * @Description TODO
 * @Author qing
 * @Date 2023/4/6 20:28
 * @Version 1.0
 */
public interface SpuImageService extends IService<SpuImage> {
    List<SpuImage> getSpuImageListBy(Long id);
}
