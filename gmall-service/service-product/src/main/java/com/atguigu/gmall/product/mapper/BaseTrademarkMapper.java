package com.atguigu.gmall.product.mapper;

import com.atguigu.gmall.model.product.BaseTrademark;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName BaseTrademarkMapper
 * @Description TODO
 * @Author qing
 * @Date 2023/4/3 21:05
 * @Version 1.0
 */
@Repository
public interface BaseTrademarkMapper extends BaseMapper<BaseTrademark> {

//    List<BaseTrademark> selectByCategory3Id(Long category3Id);
}
