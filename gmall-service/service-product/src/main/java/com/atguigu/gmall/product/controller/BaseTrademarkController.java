package com.atguigu.gmall.product.controller;// 直接赋值粘贴，删除CSDN的权限转载中文

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseTrademark;
import com.atguigu.gmall.product.service.BaseTrademarkService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: gmall-parent
 * @description:
 * @author: jq
 * @create: 2023-04-03 20:23
 **/
@Api(tags = "品牌列表")
@RestController
@RequestMapping("/admin/product")
public class BaseTrademarkController {
    @Autowired
    private BaseTrademarkService baseTrademarkService;

    @GetMapping("/baseTrademark/{page}/{limit}")
    public Result<Page> getBaseTrademarkPage(@PathVariable(value = "page") Long page, @PathVariable(value = "limit") Long limit) {
        Page<BaseTrademark> pageParam = new Page<>(page, limit);
        pageParam = baseTrademarkService.getBaseTrademarkPage(pageParam);
        return Result.ok(pageParam);
    }

    @GetMapping("baseTrademark/get/{id}")
    public Result getbaseTrademark(@PathVariable Long id) {
        BaseTrademark baseTrademark = baseTrademarkService.getById(id);
        return Result.ok(baseTrademark);
    }

    @DeleteMapping("baseTrademark/remove/{id}")
    public Result removebaseTrademark(@PathVariable Long id) {
        baseTrademarkService.removeById(id);
        return Result.ok();
    }

    @PostMapping("baseTrademark/save")
    public Result savebaseTrademark(@RequestBody BaseTrademark baseTrademark) {
        boolean save = baseTrademarkService.save(baseTrademark);
        return save ? Result.ok() : Result.fail();
    }

}
