package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.model.product.BaseAttrValue;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @ClassName BaseAttrInfoService
 * @Description TODO
 * @Author qing
 * @Date 2023/3/31 19:29
 * @Version 1.0
 */
public interface BaseAttrInfoService  extends IService<BaseAttrInfo> {
    /**
     *
     * @param category1Id
     * @param category2Id
     * @param category3Id
     * @return
     */
    List<BaseAttrInfo> getBaseAttrInfoList(Long category1Id, Long category2Id, Long category3Id);

}
