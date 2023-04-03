package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.model.product.BaseAttrValue;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @ClassName BaseAttrValueService
 * @Description TODO
 * @Author qing
 * @Date 2023/4/1 16:26
 * @Version 1.0
 */
public interface BaseAttrValueService extends IService<BaseAttrValue> {
    Boolean saveAttrInfo(BaseAttrInfo baseAttrInfo);

    List<BaseAttrValue> selectBaseAttrValueList(Long attrId);
}
