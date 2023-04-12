package com.atguigu.gmall.controller;

import com.alibaba.fastjson.JSON;
import com.atguigu.gmall.item.ItemFeignClient;
import com.atguigu.gmall.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author: atguigu
 * @create: 2023-02-25 14:18
 */
@Controller
//@SuppressWarnings("all")
public class ItemController {

    @Resource
    private ItemFeignClient itemFeignClient;

    /**
     * 渲染商品详情页面
     *
     * @param skuId
     * @return
     */
    @GetMapping("/{skuId}.html")
    public String getItem(@PathVariable("skuId") Long skuId, Model model) {
        //调用详情微服务获取渲染详情页所有的数据
        Result<Map> result = itemFeignClient.getItem(skuId);
        model.addAllAttributes(result.getData());
        return "item/item";
    }


}