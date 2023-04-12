package com.atguigu.gmall.product.controller;// 直接赋值粘贴，删除CSDN的权限转载中文

import com.atguigu.gmall.result.Result;
import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.model.product.BaseAttrValue;
import com.atguigu.gmall.product.service.BaseAttrValueService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: gmall-parent
 * @description:
 * @author: jq
 * @create: 2023-04-01 11:12
 **/
@Api(tags = "商品属性操作")
@RestController
@RequestMapping("/admin/product")
public class BaseAttrInfoController {
    @Autowired
    private BaseAttrValueService baseAttrValueService;

    /**
     *
     * @param baseAttrInfo
     * @return
     */
    @PostMapping("/saveAttrInfo")
    public Result saveAttrInfo(@RequestBody BaseAttrInfo baseAttrInfo) {
        Boolean isSaved = baseAttrValueService.saveAttrInfo(baseAttrInfo);
        return isSaved ? Result.ok() : Result.fail();
    }

    @GetMapping("/getAttrValueList/{attrId}")
    public Result<List<BaseAttrValue>> getAttrValueList(@PathVariable("attrId") Long attrId) {
        List<BaseAttrValue> attrValueList=baseAttrValueService.selectBaseAttrValueList(attrId);
        return Result.ok(attrValueList);
    }

}
