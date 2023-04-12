package com.atguigu.gmall.item.service.impl;// 直接赋值粘贴，删除CSDN的权限转载中文

import com.alibaba.fastjson.JSON;
import com.atguigu.gmall.item.service.ItemService;
import com.atguigu.gmall.model.product.*;
import com.atguigu.gmall.product.client.ProductFeignClient;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: gmall-parent
 * @description:
 * @author: jq
 * @create: 2023-04-10 01:09
 **/
@Service
public class ItemServiceImpl implements ItemService {
    @Resource
    private ProductFeignClient productFeignClient;

    @Override
    public Map getItem(Long skuId) {
        //  创建map 集合来存储商品详情页面需要的数据
        Map map = new HashMap();
        //  远程调用 获取skuInfo 数据
        SkuInfo skuInfo = productFeignClient.getSkuInfo(skuId);
        //  查询分类数据
        BaseCategoryView categoryView = productFeignClient.getCategoryView(skuInfo.getCategory3Id());
        //  查询最新价格：
        BigDecimal skuPrice = productFeignClient.getSkuPrice(skuId);
        //  获取海报信息
        List<SpuPoster> spuPosterList = productFeignClient.getSpuPosterBySpuId(skuInfo.getSpuId());
        //  根据skuId 获取平台属性数据
        List<BaseAttrInfo> attrList = productFeignClient.getAttrList(skuId);
        //  只需要attrName, attrValue; 不能直接存储.
        //  第一种方式：修改页面
        //  第二种方式：修改后台代码 skuAttr.attrName; skuAttr.attrValue ----> skuAttr 是实体类 / 或者是map
        List<HashMap<String, String>> mapList = attrList.stream().map(baseAttrInfo -> {
            //  声明一个map 集合
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("attrName", baseAttrInfo.getAttrName());
            hashMap.put("attrValue", baseAttrInfo.getAttrValueList().get(0).getValueName());
            return hashMap;
        }).collect(Collectors.toList());

        //  获取销售属性
        List<SpuSaleAttr> spuSaleAttrList = productFeignClient.getSpuSaleAttrListCheckBySku(skuId, skuInfo.getSpuId());

        //  获取切换功能数据.
        Map skuValueIdsMap = this.productFeignClient.getSkuValueIdsMap(skuInfo.getSpuId());
        String jsonStr = JSON.toJSONString(skuValueIdsMap);
        //  存储数据
        map.put("skuInfo",skuInfo);
        map.put("categoryView",categoryView);
        map.put("price",skuPrice);
        map.put("spuPosterList",spuPosterList);
        map.put("skuAttrList",mapList);
        map.put("spuSaleAttrList",spuSaleAttrList);
        map.put("valuesSkuJson",jsonStr);
        //  返回map 集合数据
        return map;
    }
}
