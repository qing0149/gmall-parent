package com.atguigu.gmall.product.mapper;// 直接赋值粘贴，删除CSDN的权限转载中文

import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.model.product.SkuAttrValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: gmall-parent
 * @description:
 * @author: jq
 * @create: 2023-04-07 14:44
 **/
@Repository
public interface SkuAttrValueMapper extends BaseMapper<SkuAttrValue> {
    List<BaseAttrInfo> getAttrList(@Param("skuId") Long skuId);

}
