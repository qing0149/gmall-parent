package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.SpuInfo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

/**
 * @ClassName SpuManageService
 * @Description TODO
 * @Author qing
 * @Date 2023/4/6 19:25
 * @Version 1.0
 */
public interface SpuManageService {
    Page<SpuInfo> selectSpuInfoPage(Page<SpuInfo> pageParam, Long category3Id);

    void saveSpuInfo(SpuInfo spuInfo);

    SpuInfo getSpuInfoById(Long id);
}
