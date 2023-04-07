package com.atguigu.gmall.product.service.impl;// 直接赋值粘贴，删除CSDN的权限转载中文

import com.atguigu.gmall.model.product.SpuImage;
import com.atguigu.gmall.model.product.SpuInfo;
import com.atguigu.gmall.product.mapper.SpuImageMapper;
import com.atguigu.gmall.product.service.SpuImageService;
import com.atguigu.gmall.product.service.SpuInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: gmall-parent
 * @description:
 * @author: jq
 * @create: 2023-04-06 20:28
 **/
@Service
public class SpuImageServiceImpl extends ServiceImpl<SpuImageMapper, SpuImage> implements SpuImageService {
    @Autowired
    private SpuImageMapper spuImageMapper;
    @Autowired
    private SpuInfoService spuInfoService;

    @Override
    public List<SpuImage> getSpuImageListBy(Long id) {
        LambdaQueryWrapper<SpuImage> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SpuImage::getSpuId, id);
        List<SpuImage> imageList = this.list(lambdaQueryWrapper);
        return imageList;
    }
}
