package com.atguigu.gmall.product.service.impl;// 直接赋值粘贴，删除CSDN的权限转载中文

import com.atguigu.gmall.model.product.SpuInfo;
import com.atguigu.gmall.product.mapper.SpuInfoMapper;
import com.atguigu.gmall.product.service.SpuInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: gmall-parent
 * @description:
 * @author: jq
 * @create: 2023-04-06 19:36
 **/
@Service
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoMapper,SpuInfo> implements SpuInfoService {
    @Autowired
    private SpuInfoMapper spuInfoMapper;

    @Override
    public SpuInfo selectAllById(Long id) {
        SpuInfo spuInfo= spuInfoMapper.selectAllById(id);
        return spuInfo;
    }
}
