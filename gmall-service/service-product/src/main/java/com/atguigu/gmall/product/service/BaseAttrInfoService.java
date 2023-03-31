package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.BaseAttrInfo;

import java.util.List;

/**
 * @ClassName BaseAttrInfoService
 * @Description TODO
 * @Author qing
 * @Date 2023/3/31 19:29
 * @Version 1.0
 */
public interface BaseAttrInfoService {
    List<BaseAttrInfo> getBaseAttrInfoList(Long category1Id, Long category2Id, Long category3Id);
}
