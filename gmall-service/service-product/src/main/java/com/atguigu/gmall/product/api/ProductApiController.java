package com.atguigu.gmall.product.api;// 直接赋值粘贴，删除CSDN的权限转载中文

import com.atguigu.gmall.model.product.*;
import com.atguigu.gmall.product.service.SkuManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @program: gmall-parent
 * @description:
 * @author: jq
 * @create: 2023-04-07 23:55
 **/
@RestController
@RequestMapping("/api/product")
public class ProductApiController {
    @Autowired
    private SkuManageService manageService;

    /**
     * 根据skuId 获取到skuInfo 基本信息+图片信息
     * @param skuId
     * @return
     */
    //ok
    @GetMapping("/inner/getSkuInfo/{skuId}")
    public SkuInfo getSkuInfo(@PathVariable Long skuId){
        //  select category3_id from sku_info where id = 22;
        //  调用服务层方法
        SkuInfo skuInfo = manageService.getSkuInfo(skuId);
        //  返回。
        return skuInfo;
    }

    /**
     * 根据三级分类Id 查询分类数据
     * @param category3Id
     * @return
     */
    @GetMapping("/inner/getCategoryView/{category3Id}")
    public BaseCategoryView getCategoryView(@PathVariable Long category3Id){
        //  调用服务层方法.
        return manageService.getCategoryView(category3Id);
    }


    /**
     * 根据skuId 获取到商品的最新价格
     * @param skuId
     * @return
     */
    @GetMapping("/inner/getSkuPrice/{skuId}")
    public BigDecimal getSkuPrice(@PathVariable Long skuId){
        //  调用服务层方法.
        return manageService.getSkuPrice(skuId);
    }

    /**
     * 根据spuId 获取海报信息
     * @param spuId
     * @return
     */
    @GetMapping("/inner/findSpuPosterBySpuId/{spuId}")
    public List<SpuPoster> getSpuPosterBySpuId(@PathVariable Long spuId){
        //  调用服务层方法.
        return manageService.getSpuPosterBySpuId(spuId);
    }

    /**
     * 根据skuId 获取平台属性集合数据
     * 返回值思路： 1.vo 2.map  3.
     */
    @GetMapping("/inner/getAttrList/{skuId}")
    public List<BaseAttrInfo> getAttrList(@PathVariable Long skuId){
        //  调用服务层方法.
        return manageService.getAttrList(skuId);
    }

    /**
     * 根据spuId-skuId 查询销售属性数据
     * @param skuId
     * @param spuId
     * @return
     */
    @GetMapping("/inner/getSpuSaleAttrListCheckBySku/{skuId}/{spuId}")
    public List<SpuSaleAttr> getSpuSaleAttrListCheckBySku(@PathVariable Long skuId,
                                                          @PathVariable Long spuId){
        //  调用服务层方法.
        return manageService.getSpuSaleAttrListCheckBySku(skuId,spuId);
    }

    /**
     * 根据spuId 获取数据 List<Map>
     * [
     *   {
     *     "values_ids": "3728|3727",
     *     "sku_id": 21
     *   },
     *   {
     *     "values_ids": "3729|3727",
     *     "sku_id": 22
     *   }
     * ]
     *
     * 需要将 3728|3727 当做key  当做 21 value; 页面在获取到销售属性值Id 的时候， 由不同的销售属性值Id 进行组合，找 sku_id;
     *
     * {
     *   "3729|3727": 22,
     *   "3728|3727": 21
     * }
     * {"3727|3728":21,"3727|3729":22}
     *
     * @param spuId
     * @return
     */
    @GetMapping("/inner/getSkuValueIdsMap/{spuId}")
    public Map getSkuValueIdsMap(@PathVariable Long spuId){
        //  什么样的数据才能变为Json！ map--实体类
        //  调用服务层方法.
        return manageService.getSkuValueIdsMap(spuId);
    }

}
