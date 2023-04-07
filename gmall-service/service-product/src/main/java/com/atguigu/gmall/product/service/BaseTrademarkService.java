package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.BaseTrademark;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @ClassName BaseTrademarkService
 * @Description TODO
 * @Author qing
 * @Date 2023/4/3 21:03
 * @Version 1.0
 */
public interface BaseTrademarkService  extends IService<BaseTrademark> {
    Page<BaseTrademark> getBaseTrademarkPage(Page<BaseTrademark> pageParam);

    BaseTrademark getById(Long id);

    void removeById(Long id);

    List<BaseTrademark> selectByCategory3Id(Long category3Id);
}
