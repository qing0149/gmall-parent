package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.BaseSaleAttr;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.model.product.SpuSaleAttrValue;
import com.atguigu.gmall.product.mapper.SpuSaleAttrMapper;
import com.atguigu.gmall.product.mapper.SpuSaleAttrValueMapper;
import com.atguigu.gmall.product.service.BaseSaleAttrService;
import com.atguigu.gmall.product.service.SpuSaleAttrService;
import com.atguigu.gmall.product.service.SpuSaleAttrValueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: gmall-parent
 * @description:
 * @author: jq
 * @create: 2023-04-06 20:59
 **/
@Service
public class SpuSaleAttrServiceImpl extends ServiceImpl<SpuSaleAttrMapper, SpuSaleAttr> implements SpuSaleAttrService {
    @Autowired
    private SpuSaleAttrValueService spuSaleAttrValueService;
    @Autowired
    private BaseSaleAttrService baseSaleAttrService;
    @Autowired
    private SpuSaleAttrService spuSaleAttrService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSpuSaleAttrList(List<SpuSaleAttr> spuSaleAttrList) {
        this.saveBatch(spuSaleAttrList);
//        ArrayList<BaseSaleAttr> baseSaleAttrList = new ArrayList<>();
        spuSaleAttrList.stream().forEach(spuSaleAttr -> {
            List<SpuSaleAttrValue> spuSaleAttrValueList = spuSaleAttr.getSpuSaleAttrValueList();
            spuSaleAttrValueList.stream().forEach(spuSaleAttrValue -> {
                spuSaleAttrValue.setSpuId(spuSaleAttr.getSpuId());
                spuSaleAttrValue.setSaleAttrName(spuSaleAttr.getSaleAttrName());
            });
            BaseSaleAttr baseSaleAttr = new BaseSaleAttr();
            baseSaleAttr.setId(spuSaleAttr.getBaseSaleAttrId());
            baseSaleAttr.setName(spuSaleAttr.getSaleAttrName());
//            baseSaleAttrList.add(baseSaleAttr);
        });
        //todo: 保存spu_sale_attr_value
        List<List<SpuSaleAttrValue>> spuSaleAttrValueList = spuSaleAttrList.stream().map(SpuSaleAttr::getSpuSaleAttrValueList).collect(Collectors.toList());
        spuSaleAttrValueList.stream().forEach(spuSaleAttrValues -> spuSaleAttrValueService.saveBatch(spuSaleAttrValues));
        //todo: 保存base_sale_attr
//        baseSaleAttrService.saveBatch(baseSaleAttrList);
    }
    @Autowired
    private SpuSaleAttrMapper spuSaleAttrMapper;
    @Override
    public List<SpuSaleAttr> getSpuSaleAttrList(Long spuId) {
        return spuSaleAttrMapper.selectSpuSaleAttrList(spuId);
    }
}
