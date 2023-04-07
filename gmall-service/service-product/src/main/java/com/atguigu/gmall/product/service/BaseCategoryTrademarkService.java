package com.atguigu.gmall.product.service;

import com.atguigu.gmall.model.product.BaseCategoryTrademark;
import com.atguigu.gmall.model.product.BaseTrademark;
import com.atguigu.gmall.model.product.CategoryTrademarkVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @ClassName BaseCategoryTrademarkService
 * @Description TODO
 * @Author qing
 * @Date 2023/4/4 15:06
 * @Version 1.0
 */
public interface BaseCategoryTrademarkService extends IService<BaseCategoryTrademark> {
    List<BaseTrademark> getBaseTrademarkList(Long category3Id);

    void savecategoryTrademarkVo(CategoryTrademarkVo categoryTrademarkVo);

    void deleteByCategory3IdAndtrademarkId(Long category3Id, Long trademarkId);

    List<BaseTrademark> findCurrentTrademarkList(Long category3Id);
}
