package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.product.mapper.BaseAttrInfoMapper;
import com.atguigu.gmall.product.service.BaseAttrInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName BaseAttrInfoServiceImpl
 * @Description TODO
 * @Author qing
 * @Date 2023/3/31 19:31
 * @Version 1.0
 */
@Service
public class BaseAttrInfoServiceImpl implements BaseAttrInfoService {
    @Resource
    private BaseAttrInfoMapper baseAttrInfoMapper;

    @Override
    public List<BaseAttrInfo> getBaseAttrInfoList(Long category1Id, Long category2Id, Long category3Id) {
        List<BaseAttrInfo> baseAttrInfoList=baseAttrInfoMapper.selectAttrInfoList(category1Id,category2Id,category3Id);
        return baseAttrInfoList;
    }
}
