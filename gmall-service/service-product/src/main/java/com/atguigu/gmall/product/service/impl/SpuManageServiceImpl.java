package com.atguigu.gmall.product.service.impl;// 直接赋值粘贴，删除CSDN的权限转载中文

import com.atguigu.gmall.model.product.*;
import com.atguigu.gmall.product.service.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: gmall-parent
 * @description:
 * @author: jq
 * @create: 2023-04-06 19:26
 **/
@Service
public class SpuManageServiceImpl implements SpuManageService {

    @Autowired
    private SpuInfoService spuInfoService;
    @Autowired
    private SpuSaleAttrService spuSaleAttrService;
    @Autowired
    private SpuImageService spuImageService;
    @Autowired
    private SpuPosterService spuPosterService;
    @Autowired
    private SpuSaleAttrValueService spuSaleAttrValueService;

    @Override
    public Page<SpuInfo> selectSpuInfoPage(Page<SpuInfo> pageParam, Long category3Id) {
        LambdaQueryWrapper<SpuInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SpuInfo::getCategory3Id, category3Id);
        Page<SpuInfo> page = spuInfoService.page(pageParam, lambdaQueryWrapper);
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSpuInfo(SpuInfo spuInfo) {
        if (spuInfo.getId() == null) {
            spuInfoService.save(spuInfo);
            System.out.println(spuInfo);
//      把spuImage存入相关表中
            List<SpuImage> spuImageList = spuInfo.getSpuImageList();
            if (!CollectionUtils.isEmpty(spuImageList)) {
                spuImageList.stream().forEach(spuImage -> spuImage.setSpuId(spuInfo.getId()));
                spuImageService.saveBatch(spuImageList);
            }

//      把海报图片存入相关表中
            List<SpuPoster> spuPosterList = spuInfo.getSpuPosterList();
            if (!CollectionUtils.isEmpty(spuPosterList)) {
                spuPosterList.stream().forEach(spuPoster -> spuPoster.setSpuId(spuInfo.getId()));
                spuPosterService.saveBatch(spuPosterList);
            }

//       把销售属性
            List<SpuSaleAttr> spuSaleAttrList = spuInfo.getSpuSaleAttrList();
            if (!CollectionUtils.isEmpty(spuSaleAttrList)) {
                spuSaleAttrList.stream().forEach(spuSaleAttr -> spuSaleAttr.setSpuId(spuInfo.getId()));
                spuSaleAttrService.saveSpuSaleAttrList(spuSaleAttrList);
            }
        }
    }

    @Override
    public SpuInfo getSpuInfoById(Long id) {
//        SpuInfo spuInfo = spuInfoService.getById(id);
        SpuInfo spuInfo = spuInfoService.selectAllById(id);

        return spuInfo;
    }

    //todo:更新spu的信息
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSpuInfo(SpuInfo spuInfo) {
        Long id = spuInfo.getId();
        spuInfoService.removeById(id);
//       todo:删除海报id 获取海报
        LambdaQueryWrapper<SpuPoster> spuPosterLambdaQueryWrapper = new LambdaQueryWrapper<>();
        spuPosterLambdaQueryWrapper.eq(SpuPoster::getSpuId, spuInfo.getId());
        spuPosterService.remove(spuPosterLambdaQueryWrapper);
//        todo:删除封面
        LambdaQueryWrapper<SpuImage> spuImageLambdaQueryWrapper = new LambdaQueryWrapper<>();
        spuImageLambdaQueryWrapper.eq(SpuImage::getSpuId, spuInfo.getId());
        spuImageService.remove(spuImageLambdaQueryWrapper);
//        todo:删除销售属性获取销售属性id
        LambdaQueryWrapper<SpuSaleAttr> spuSaleAttrLambdaQueryWrapper = new LambdaQueryWrapper<>();
        spuSaleAttrLambdaQueryWrapper.eq(SpuSaleAttr::getSpuId, spuInfo.getId());
        spuSaleAttrService.remove(spuSaleAttrLambdaQueryWrapper);
//        删除相关销售属性的值
        LambdaQueryWrapper<SpuSaleAttrValue> spuSaleAttrValueLambdaQueryWrapper = new LambdaQueryWrapper<>();
        spuSaleAttrValueLambdaQueryWrapper.eq(SpuSaleAttrValue::getSpuId, spuInfo.getId());
        spuSaleAttrValueService.remove(spuSaleAttrValueLambdaQueryWrapper);
        //进行保存
//        this.saveSpuInfo(spuInfo);
        spuInfoService.updateById(spuInfo);
        System.out.println(spuInfo);
//      把spuImage存入相关表中
        List<SpuImage> spuImageList = spuInfo.getSpuImageList();
        if (!CollectionUtils.isEmpty(spuImageList)) {
            spuImageList.stream().forEach(spuImage -> spuImage.setSpuId(spuInfo.getId()));
            spuImageService.updateBatchById(spuImageList);
        }

//      把海报图片存入相关表中
        List<SpuPoster> spuPosterList = spuInfo.getSpuPosterList();
        if (!CollectionUtils.isEmpty(spuPosterList)) {
            spuPosterList.stream().forEach(spuPoster -> spuPoster.setSpuId(spuInfo.getId()));
            spuPosterService.updateBatchById(spuPosterList);
        }

//       把销售属性
        List<SpuSaleAttr> spuSaleAttrList = spuInfo.getSpuSaleAttrList();
        if (!CollectionUtils.isEmpty(spuSaleAttrList)) {
            spuSaleAttrList.stream().forEach(spuSaleAttr -> spuSaleAttr.setSpuId(spuInfo.getId()));
            spuSaleAttrService.updateBatchById(spuSaleAttrList);
        }
    }
}
