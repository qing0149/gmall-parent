package com.atguigu.gmall.product.controller;// 直接赋值粘贴，删除CSDN的权限转载中文

import com.atguigu.gmall.result.Result;
import com.atguigu.gmall.model.product.SpuImage;
import com.atguigu.gmall.model.product.SpuInfo;
import com.atguigu.gmall.product.service.SpuImageService;
import com.atguigu.gmall.product.service.SpuManageService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: gmall-parent
 * @description: 商品品牌
 * @author: jq
 * @create: 2023-04-06 19:22
 **/
@Api(tags = "商品详细管理")
@RestController
@RequestMapping("/admin/product")
public class SpuManagerController {
    @Autowired
    private SpuManageService spuManageService;
    @Autowired
    private SpuImageService spuImageService;

    @GetMapping("/{page}/{limit}")
    public Result getSpuInfoList(@PathVariable Long page, @PathVariable Long limit,
                                 @RequestParam(value = "category3Id") Long category3Id) {
        Page<SpuInfo> pageParam = new Page<>(page, limit);
        Page<SpuInfo> pageInfo = spuManageService.selectSpuInfoPage(pageParam, category3Id);
        return Result.ok(pageInfo);
    }

    @PostMapping("/saveSpuInfo")
    public Result saveSpuInfo(@RequestBody SpuInfo spuInfo) {
        spuManageService.saveSpuInfo(spuInfo);
        return Result.ok();
    }

    @GetMapping("/getSpuInfo/{id}")
    public Result getSpuInfoById(@PathVariable Long id) {
        SpuInfo spuInfo = spuManageService.getSpuInfoById(id);
        return Result.ok(spuInfo);
    }

    @GetMapping("/spuImageList/{id}")
    public Result spuImageList(@PathVariable(value = "id") Long id) {
        List<SpuImage> spuImageList = spuImageService.getSpuImageListBy(id);
        return Result.ok(spuImageList);
    }
//    修改spk
    @PostMapping("/updateSpuInfo")
    public Result updateSpuInfo(@RequestBody SpuInfo spuInfo){
        spuManageService.updateSpuInfo(spuInfo);
        return Result.ok();
    }

}
