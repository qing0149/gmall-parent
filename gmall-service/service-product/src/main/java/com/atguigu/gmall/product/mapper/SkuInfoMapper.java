package com.atguigu.gmall.product.mapper;

import com.atguigu.gmall.model.product.SkuInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @ClassName SkuInfoMapper
 * @Description TODO
 * @Author qing
 * @Date 2023/4/7 10:38
 * @Version 1.0
 */
@Repository
public interface SkuInfoMapper extends BaseMapper<SkuInfo> {
    SkuInfo selectSkuInfoById(@Param("id") Long id);
}
