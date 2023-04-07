package com.atguigu.gmall.product.controller;// 直接赋值粘贴，删除CSDN的权限转载中文

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseCategoryTrademark;
import com.atguigu.gmall.model.product.BaseTrademark;
import com.atguigu.gmall.model.product.CategoryTrademarkVo;
import com.atguigu.gmall.product.service.BaseCategoryTrademarkService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: gmall-parent
 * @description:
 * @author: jq
 * @create: 2023-04-04 11:04
 **/
@Api(tags = "商标和属性值")
@RestController
@RequestMapping("/admin/product/baseCategoryTrademark")
public class BaseCategoryTradeMarkController {
    @Autowired
    private BaseCategoryTrademarkService baseCategoryTrademarkService;

    /**
     * 根据category3Id获取品牌列表
     * @param category3Id
     * @return
     */

    @GetMapping("findTrademarkList/{category3Id}")
    public Result findTrademarkListById(@PathVariable(value = "category3Id") Long category3Id) {
        List<BaseTrademark> list = baseCategoryTrademarkService.getBaseTrademarkList(category3Id);
        return Result.ok(list);
    }

    /**
     * 根据category3Id获取可选品牌列表
     * @param category3Id
     * @return
     */
    @GetMapping("findCurrentTrademarkList/{category3Id}")
    public Result findCurrentTrademarkList(@PathVariable(value = "category3Id") Long category3Id) {
        List<BaseTrademark> baseCategoryTrademarkList =baseCategoryTrademarkService.findCurrentTrademarkList(category3Id);
        return Result.ok( baseCategoryTrademarkList);
    }

    /**
     * 删除分类品牌关系
     * @param category3Id
     * @param trademarkId
     * @return
     */
    @DeleteMapping("remove/{category3Id}/{trademarkId}")
    public Result remove(@PathVariable(value = "category3Id") Long category3Id,
                         @PathVariable(value = "trademarkId") Long trademarkId) {
        baseCategoryTrademarkService.deleteByCategory3IdAndtrademarkId(category3Id,trademarkId);
        return Result.ok();
    }

    /**
     * 保存分类品牌关联
     * @param categoryTrademarkVo
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody CategoryTrademarkVo categoryTrademarkVo) {
        baseCategoryTrademarkService.savecategoryTrademarkVo(categoryTrademarkVo);
        return Result.ok();
    }

}
