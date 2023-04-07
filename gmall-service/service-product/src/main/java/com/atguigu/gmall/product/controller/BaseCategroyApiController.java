package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.model.product.BaseCategory1;
import com.atguigu.gmall.model.product.BaseCategory2;
import com.atguigu.gmall.model.product.BaseCategory3;
import com.atguigu.gmall.product.service.BaseAttrInfoService;
import com.atguigu.gmall.product.service.ManagerCategroyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName BaseCategroyApiController
 * @Description TODO
 * @Author qing
 * @Date 2023/3/31 10:14
 * @Version 1.0
 */

@Api(tags = "后台控制器")
@RequestMapping("/admin/product")
@RestController
//@CrossOrigin
public class BaseCategroyApiController {
    @Autowired
    private ManagerCategroyService managerCategroyService;

    @Autowired
    private BaseAttrInfoService baseAttrInfoService;

    /**
     * @Description: 获取一级分类数据
     * @Param:
     * @return:
     * @Author: jq
     */
    @ApiOperation(value = "获取一级分类数据")
    @GetMapping("/getCategory1")
    public Result<List<BaseCategory1>> getCategory1() {
        List<BaseCategory1> category1List = managerCategroyService.getCategory1();
        return Result.ok(category1List);
    }

    /**
     * @Description: 获取二级分类数据
     * @Param:
     * @return:
     * @Author: jq
     */
    @ApiOperation(value = "获取二级分类数据")
    @GetMapping("getCategory2/{category1Id}")
    public Result<List<BaseCategory2>> getCategory2(@PathVariable Long category1Id) {
        List<BaseCategory2> category2List = managerCategroyService.getCategory2(category1Id);
        return Result.ok(category2List);
    }

    /**
     * @Description: 获取三级分类数据
     * @Param:
     * @return:
     * @Author: jq
     */
    @ApiOperation(value = "获取三级分类数据")
    @GetMapping("/getCategory3/{category2Id}")
    public Result<List<BaseCategory3>> getCategory3(@PathVariable Long category2Id) {
        List<BaseCategory3> category3List = managerCategroyService.getCategory3(category2Id);
        return Result.ok(category3List);
    }

    /**
     * @Description: 根据分类id获取平台属性集合
     * @Param:
     * @return:
     * @Author: jq
     */
    @ApiOperation(value = "根据分类id获取平台属性集合")
    @GetMapping("/attrInfoList/{category1Id}/{category2Id}/{category3Id}")
    public Result<List<BaseAttrInfo>> attrInfoList(@PathVariable(required = false) Long category1Id,
                                                   @PathVariable(required = false) Long category2Id,
                                                   @PathVariable(required = false) Long category3Id) {
        List<BaseAttrInfo> baseAttrInfoList = baseAttrInfoService.getBaseAttrInfoList(category1Id, category2Id, category3Id);
        return Result.ok(baseAttrInfoList);
    }


}
