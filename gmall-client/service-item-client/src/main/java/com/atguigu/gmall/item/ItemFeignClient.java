package com.atguigu.gmall.item;

import com.atguigu.gmall.item.impl.ItemDegradeFeignClient;
import com.atguigu.gmall.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-item", fallback = ItemDegradeFeignClient.class)
public interface ItemFeignClient {

    /**
     * 根据skuId 调用商品详情
     *
     * @param skuId
     * @return
     */
    @GetMapping("/api/item/{skuId}")
    Result getItem(@PathVariable Long skuId);

}
