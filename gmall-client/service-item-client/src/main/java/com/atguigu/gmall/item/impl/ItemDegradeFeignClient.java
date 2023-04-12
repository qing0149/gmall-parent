package com.atguigu.gmall.item.impl;


import com.atguigu.gmall.item.ItemFeignClient;
import com.atguigu.gmall.result.Result;
import org.springframework.stereotype.Component;

@Component
public class ItemDegradeFeignClient implements ItemFeignClient {
    @Override
    public Result getItem(Long skuId) {
        System.out.println("商品详情熔断");
        return null;
    }
}
