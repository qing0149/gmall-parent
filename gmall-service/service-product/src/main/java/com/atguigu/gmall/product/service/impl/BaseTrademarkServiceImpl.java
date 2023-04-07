package com.atguigu.gmall.product.service.impl;// 直接赋值粘贴，删除CSDN的权限转载中文

import com.atguigu.gmall.model.product.BaseCategoryTrademark;
import com.atguigu.gmall.model.product.BaseTrademark;
import com.atguigu.gmall.product.mapper.BaseTrademarkMapper;
import com.atguigu.gmall.product.service.BaseCategoryTrademarkService;
import com.atguigu.gmall.product.service.BaseTrademarkService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: gmall-parent
 * @description:
 * @author: jq
 * @create: 2023-04-03 21:04
 **/
@Service
public class BaseTrademarkServiceImpl extends ServiceImpl<BaseTrademarkMapper, BaseTrademark> implements BaseTrademarkService {

    @Autowired
    private BaseTrademarkMapper baseTrademarkMapper;
    @Autowired
    private BaseCategoryTrademarkService baseCategoryTrademarkService;
    @Override
    public Page<BaseTrademark> getBaseTrademarkPage(Page<BaseTrademark> pageParam) {
        Page<BaseTrademark> page = baseTrademarkMapper.selectPage(pageParam, null);
        return page;
    }

    @Override
    public BaseTrademark getById(Long id) {
        BaseTrademark baseTrademark = baseTrademarkMapper.selectById(id);
        return baseTrademark;
    }

    @Override
    public void removeById(Long id) {
        int i = baseTrademarkMapper.deleteById(id);
    }

    @Override
    public List<BaseTrademark> selectByCategory3Id(Long category3Id) {
        List<BaseCategoryTrademark> baseCategoryTrademarkList =
                baseCategoryTrademarkService.list(new LambdaQueryWrapper<BaseCategoryTrademark>().eq(BaseCategoryTrademark::getCategory3Id, category3Id));
        List<BaseTrademark> baseTrademarkList = this.list(null);
        if (CollectionUtils.isEmpty(baseCategoryTrademarkList)){
            return baseTrademarkList;
        }else {
            List<Long> trademarkIdList = baseCategoryTrademarkList.stream().map(baseCategoryTrademark -> baseCategoryTrademark.getTrademarkId()).collect(Collectors.toList());
            List<Long> idList = baseTrademarkList.stream().map(BaseTrademark::getId).collect(Collectors.toList());
            List<Long> ids = idList.stream().filter(id -> !CollectionUtils.contains(trademarkIdList.iterator(), id)).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(ids)){
                return null;
            }
            baseTrademarkList = this.listByIds(ids);
            return baseTrademarkList;
        }
    }
}
