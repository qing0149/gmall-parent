package com.atguigu.gmall.product.controller;// 直接赋值粘贴，删除CSDN的权限转载中文

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseSaleAttr;
import com.atguigu.gmall.model.product.SpuInfo;
import com.atguigu.gmall.product.service.BaseSaleAttrService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @program: gmall-parent
 * @description:
 * @author: jq
 * @create: 2023-04-06 19:03
 **/
@Api(value = "商品管理")
@RestController
@RequestMapping("/admin/product")
public class BaseSaleAttrController {
    @Autowired
    private BaseSaleAttrService baseSaleAttrService;


    @GetMapping("/spuSaleAttrList/{spuId}")
    public Result spuSaleAttrList(@PathVariable Long spuId) {
        //todo 还需要完成
//        List<Object> list = baseSaleAttrService.spuSaleAttrList(spuId);
        return Result.ok();
//        return Result.ok(list);
    }

    @GetMapping("/baseSaleAttrList")
    public Result baseSaleAttrList() {
        List<BaseSaleAttr> baseSaleAttrs = baseSaleAttrService.list();
        return Result.ok(baseSaleAttrs);
    }


}
