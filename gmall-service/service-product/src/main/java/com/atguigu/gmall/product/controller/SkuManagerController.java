package com.atguigu.gmall.product.controller;// 直接赋值粘贴，删除CSDN的权限转载中文

import com.atguigu.gmall.result.Result;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.product.service.SkuInfoService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: gmall-parent
 * @description: 商品库存
 * @author: jq
 * @create: 2023-04-06 20:21
 **/
@RestController
@RequestMapping("/admin/product")
public class SkuManagerController {
    @Autowired
    private SkuInfoService skuInfoService;

    @PostMapping("/saveSkuInfo")
    public Result saveSkuInfo(@RequestBody SkuInfo skuInfo) {
        skuInfoService.saveSkuInfoAll(skuInfo);
        return Result.ok();
    }

    @GetMapping("/list/{page}/{limit}")
    public Result list(@PathVariable(value = "page") Long page,
                       @PathVariable(value = "limit") Long limit,
                       @RequestParam(value = "category3Id") Long category3Id) {
        Page<SkuInfo> pageParam = new Page<>(page, limit);
        pageParam = skuInfoService.selectSkuInfoPage(pageParam, category3Id);
        return Result.ok(pageParam);
    }

    /**
     * 修改sku的信息
     *
     * @param id
     * @return
     */
    @GetMapping("/getSkuInfo/{id}")
    public Result getSkuInfo(Long id) {
        SkuInfo skuInfo = skuInfoService.selectSkuInfoById(id);
        return Result.ok(skuInfo);
    }

    @PostMapping("/updateSkuInfo")
    public Result updateSkuInfo(@RequestBody SkuInfo skuInfo) {
        skuInfoService.updateSkuInfo(skuInfo);
        return Result.ok();
    }

    /**
     * 修改是否上架
     *
     * @param id
     * @return
     */
    @GetMapping("/onSale/{id}")
    public Result onSale(@PathVariable Long id) {
        skuInfoService.updateOnSale(id);
        return Result.ok();
    }
    /**
     * 取消上架
     *
     * @param id
     * @return
     */
    @GetMapping("/cancelSale/{id}")
    public Result cancelSale(@PathVariable Long id) {
        skuInfoService.updateOnSale(id);
        return Result.ok();
    }


}

