package com.atguigu.gmall.product.service.impl;// 直接赋值粘贴，删除CSDN的权限转载中文

import com.atguigu.gmall.model.product.BaseTrademark;
import com.atguigu.gmall.product.mapper.BaseTrademarkMapper;
import com.atguigu.gmall.product.service.BaseTrademarkService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: gmall-parent
 * @description:
 * @author: jq
 * @create: 2023-04-03 21:04
 **/
@Service
public class BaseTrademarkServiceImpl extends ServiceImpl<BaseTrademarkMapper,BaseTrademark> implements BaseTrademarkService {

    @Autowired
    BaseTrademarkMapper baseTrademarkMapper;

    @Override
    public Page<BaseTrademark> getBaseTrademarkPage(Page<BaseTrademark> pageParam) {
        Page<BaseTrademark> page = baseTrademarkMapper.selectPage(pageParam, null);
        return page;
    }

    @Override
    public BaseTrademark getById(Long id) {
        BaseTrademark baseTrademark = baseTrademarkMapper.selectById(id);
        return  baseTrademark;
    }

    @Override
    public void removeById(Long id) {
        int i = baseTrademarkMapper.deleteById(id);
    }
}
