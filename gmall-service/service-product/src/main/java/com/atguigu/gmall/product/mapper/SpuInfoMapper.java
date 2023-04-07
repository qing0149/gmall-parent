package com.atguigu.gmall.product.mapper;

import com.atguigu.gmall.model.product.SpuInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @ClassName SpuInfoMapper
 * @Description TODO
 * @Author qing
 * @Date 2023/4/6 19:38
 * @Version 1.0
 */
@Repository
public interface SpuInfoMapper extends BaseMapper<SpuInfo> {
    SpuInfo selectAllById(@Param("id") Long id);
}
