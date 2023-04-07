package com.atguigu.gmall.product.service.impl;// 直接赋值粘贴，删除CSDN的权限转载中文

import com.atguigu.gmall.model.product.BaseCategoryTrademark;
import com.atguigu.gmall.model.product.BaseTrademark;
import com.atguigu.gmall.model.product.CategoryTrademarkVo;
import com.atguigu.gmall.product.mapper.BaseCategoryTrademarkMapper;
import com.atguigu.gmall.product.service.BaseCategoryTrademarkService;
import com.atguigu.gmall.product.service.BaseTrademarkService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import springfox.documentation.schema.Collections;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: gmall-parent
 * @description:
 * @author: jq
 * @create: 2023-04-04 15:07
 **/
@Service
public class BaseCategoryTrademarkServiceImpl extends ServiceImpl<BaseCategoryTrademarkMapper, BaseCategoryTrademark> implements BaseCategoryTrademarkService {
    @Autowired
    private BaseTrademarkService baseTrademarkService;


    @Override
    public List<BaseTrademark> getBaseTrademarkList(Long category3Id) {
        LambdaQueryWrapper<BaseCategoryTrademark> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(BaseCategoryTrademark::getCategory3Id,category3Id);
        List<Long> trademarkIds = this.list(lambdaQueryWrapper).stream().map(BaseCategoryTrademark::getTrademarkId).collect(Collectors.toList());
        List<BaseTrademark> baseTrademarks=null;
        if (!CollectionUtils.isEmpty(trademarkIds)){
            baseTrademarks = baseTrademarkService.listByIds(trademarkIds);
        }
        return baseTrademarks;
    }

    @Override
    public void savecategoryTrademarkVo(CategoryTrademarkVo categoryTrademarkVo) {
        Long category3Id = categoryTrademarkVo.getCategory3Id();
        List<Long> trademarkIdList = categoryTrademarkVo.getTrademarkIdList();
        ArrayList<BaseCategoryTrademark> baseCategoryTrademarks = new ArrayList<>();
        trademarkIdList.stream().forEach(trademarkId->{
            BaseCategoryTrademark baseCategoryTrademark = new BaseCategoryTrademark();
            baseCategoryTrademark.setCategory3Id(category3Id);
            baseCategoryTrademark.setTrademarkId(trademarkId);
            baseCategoryTrademarks.add(baseCategoryTrademark);
        });
        this.saveBatch(baseCategoryTrademarks);
    }

    @Override
    public void deleteByCategory3IdAndtrademarkId(Long category3Id, Long trademarkId) {
        LambdaQueryWrapper<BaseCategoryTrademark> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(BaseCategoryTrademark::getCategory3Id,category3Id);
        lambdaQueryWrapper.eq(BaseCategoryTrademark::getTrademarkId,trademarkId);
        this.remove(lambdaQueryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<BaseTrademark> findCurrentTrademarkList(Long category3Id) {
        List<BaseTrademark> baseTrademarks = baseTrademarkService.selectByCategory3Id(category3Id);
        return baseTrademarks;
    }
}
