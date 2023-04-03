package com.atguigu.gmall.product.service.impl;// 直接赋值粘贴，删除CSDN的权限转载中文

import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.model.product.BaseAttrValue;
import com.atguigu.gmall.product.mapper.BaseAttrValueMapper;
import com.atguigu.gmall.product.service.BaseAttrInfoService;
import com.atguigu.gmall.product.service.BaseAttrValueService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.jws.Oneway;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: gmall-parent
 * @description:
 * @author: jq
 * @create: 2023-04-01 16:26
 **/
@Service
public class BaseAttrValueServiceImpl extends ServiceImpl<BaseAttrValueMapper, BaseAttrValue> implements BaseAttrValueService {
    @Autowired
    private BaseAttrValueService baseAttrValueService;
    @Autowired
    private BaseAttrInfoService baseAttrInfoService;

    @Override
    public Boolean saveAttrInfo(BaseAttrInfo baseAttrInfo) {
        boolean flag = false;
        ArrayList<BaseAttrInfo> baseAttrInfos = new ArrayList<>();
        if (baseAttrInfo.getId() != null) {
            if (!CollectionUtils.isEmpty(baseAttrInfo.getAttrValueList())) {
                LambdaQueryWrapper<BaseAttrValue> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(BaseAttrValue::getAttrId, baseAttrInfo.getId());
                baseAttrValueService.remove(queryWrapper);
                List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
                attrValueList.stream().forEach(baseAttrValue -> {
                    baseAttrValue.setAttrId(baseAttrInfo.getId());
                });
                flag = baseAttrValueService.saveBatch(attrValueList);
            }
        } else {
            List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
            attrValueList.stream().forEach(baseAttrValue -> {
                baseAttrValue.setAttrId(baseAttrInfo.getCategoryId());
            });
            flag = baseAttrValueService.saveBatch(attrValueList);
            List<String> attr_name = attrValueList.stream().map(BaseAttrValue::getValueName).collect(Collectors.toList());
            attr_name.stream().forEach(a -> {
                BaseAttrInfo attrInfo = new BaseAttrInfo();
                BeanUtils.copyProperties(baseAttrInfo, attrInfo);
                attrInfo.setAttrName(a);
                baseAttrInfos.add(attrInfo);
            });
            baseAttrInfoService.saveBatch(baseAttrInfos);
        }
        return flag;
    }

    @Override
    public List<BaseAttrValue> selectBaseAttrValueList(Long attrId) {
        LambdaQueryWrapper<BaseAttrValue> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BaseAttrValue::getAttrId, attrId);
        List<BaseAttrValue> attrValueList = baseAttrValueService.list(wrapper);
        return attrValueList;
    }
}
