package com.atguigu.gmall.product.service.impl;// 直接赋值粘贴，删除CSDN的权限转载中文

import com.atguigu.gmall.model.product.*;
import com.atguigu.gmall.product.mapper.SkuInfoMapper;
import com.atguigu.gmall.product.service.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: gmall-parent
 * @description:
 * @author: jq
 * @create: 2023-04-07 10:36
 **/
@Service
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoMapper, SkuInfo> implements SkuInfoService {
    @Autowired
    private SkuInfoMapper skuInfoMapper;
    @Autowired
    private SkuImageService skuImageService;
    @Autowired
    private SkuSaleAttrValueService skuSaleAttrValueService;
    @Autowired
    private SkuAttrValueService skuAttrValueService;
    @Autowired
    private SpuSaleAttrValueService spuSaleAttrValueService;

    @Override
//    @Transactional(rollbackFor = Exception.class)
    public void saveSkuInfoAll(SkuInfo skuInfo) {
        /*
        sku_attr_value
        sku_image
        sku_info
        sku_sale_attr_value
         */
        //  sku_info
        skuInfoMapper.insert(skuInfo);

        //  sku_image
        List<SkuImage> skuImageList = skuInfo.getSkuImageList();
        if (!CollectionUtils.isEmpty(skuImageList)) {
            skuImageList.forEach(skuImage -> {
                skuImage.setSkuId(skuInfo.getId());
            });
            //  批量保存
            this.skuImageService.saveBatch(skuImageList);
        }
        //   sku_attr_value
        List<SkuAttrValue> skuAttrValueList = skuInfo.getSkuAttrValueList();
        if (!CollectionUtils.isEmpty(skuAttrValueList)) {
            skuAttrValueList.forEach(skuAttrValue -> {
                skuAttrValue.setSkuId(skuInfo.getId());
            });
            //  批量保存
            this.skuAttrValueService.saveBatch(skuAttrValueList);
        }

        //  sku_sale_attr_value
        List<SkuSaleAttrValue> skuSaleAttrValueList = skuInfo.getSkuSaleAttrValueList();
        if (!CollectionUtils.isEmpty(skuSaleAttrValueList)) {
            skuSaleAttrValueList.forEach(skuSaleAttrValue -> {
                skuSaleAttrValue.setSkuId(skuInfo.getId());
                skuSaleAttrValue.setSpuId(skuInfo.getSpuId());
            });
            //  批量保存
            this.skuSaleAttrValueService.saveBatch(skuSaleAttrValueList);
        }
    }

    @Override
    public Page<SkuInfo> selectSkuInfoPage(Page<SkuInfo> pageParam, Long category3Id) {
        LambdaQueryWrapper<SkuInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SkuInfo::getCategory3Id, category3Id);
        pageParam = skuInfoMapper.selectPage(pageParam, lambdaQueryWrapper);
        return pageParam;
    }

    @Override
    public void updateOnSale(Long id) {
        SkuInfo skuInfo = this.getById(id);
        switch (skuInfo.getIsSale()) {
            case 0:
                skuInfo.setIsSale(1);
                break;
            case 1:
                skuInfo.setIsSale(0);
                break;
        }
        LambdaQueryWrapper<SkuInfo> skuInfoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        skuInfoLambdaQueryWrapper.eq(SkuInfo::getId, id);
        this.update(skuInfo, skuInfoLambdaQueryWrapper);
//        this.updateById(skuInfo);
    }

    @Override
    public void updateSkuInfo(SkuInfo skuInfo) {


    }

    @Override
    public SkuInfo selectSkuInfoById(Long id) {
//        SkuInfo skuInfo = skuInfoMapper.selectSkuInfoById(id);
        SkuInfo skuInfo = skuInfoMapper.selectById(id);
        return skuInfo;
    }
}
