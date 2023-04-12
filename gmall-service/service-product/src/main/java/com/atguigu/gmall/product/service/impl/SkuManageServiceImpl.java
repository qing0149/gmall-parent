package com.atguigu.gmall.product.service.impl;// 直接赋值粘贴，删除CSDN的权限转载中文

import com.atguigu.gmall.cache.GmallCache;
import com.atguigu.gmall.config.RedisConfig;
import com.atguigu.gmall.constant.RedisConst;
import com.atguigu.gmall.model.product.*;
import com.atguigu.gmall.product.mapper.*;
import com.atguigu.gmall.product.service.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.lettuce.core.RedisClient;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import springfox.documentation.annotations.Cacheable;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @program: gmall-parent
 * @description:
 * @author: jq
 * @create: 2023-04-08 00:55
 **/
@Service
public class SkuManageServiceImpl implements SkuManageService {
    @Autowired
    private SkuInfoService skuInfoService;
    @Autowired
    private SkuImageService skuImageService;
    @Autowired
    private BaseCategoryViewMapper baseCategoryViewMapper;
    @Autowired
    private SkuAttrValueMapper skuAttrValueMapper;

    @Autowired
    private SkuSaleAttrValueMapper skuSaleAttrValueMapper;
    @Autowired
    private SpuPosterService spuPosterService;
    @Autowired
    private BaseAttrInfoService baseAttrInfoService;
    @Autowired
    private BaseAttrInfoMapper baseAttrInfoMapper;
    @Autowired
    private SpuSaleAttrValueService spuSaleAttrValueService;
    @Autowired
    private SpuSaleAttrService spuSaleAttrService;
    @Autowired
    private SpuPosterMapper spuPosterMapper;
    @Autowired
    private RedissonClient redissonClient;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    @GmallCache(prefex = "SkuInfo:")
    public SkuInfo getSkuInfo(Long skuId) {
        return getDataByRedisson(skuId);
    }

    private SkuInfo getDataByRedisson(Long skuId) {
        //  redisson 做分布式锁！
        //  声明一个对象
        SkuInfo skuInfo = null;
        try {
            //  缓存存储数据 --- 1. 数据类型 String Hash Set List ZSet 数据类型使用场景！
            //  先判断缓存中是否有数据！ 组成缓存的key！ key 名不能重复！ sku:skuId:info
            String skuKey = RedisConst.SKUKEY_PREFIX + skuId + RedisConst.SKUKEY_SUFFIX;
            //  从缓存获取数据
            skuInfo = (SkuInfo) this.redisTemplate.opsForValue().get(skuKey);
            if (skuInfo == null) {
                //  redisson --- key
                //  考虑高并发，缓存击穿: 分布式锁！ sku:skuId:lock
                String skuLocKey = RedisConst.SKUKEY_PREFIX + skuId + RedisConst.SKULOCK_SUFFIX;
                RLock lock = redissonClient.getLock(skuLocKey);
                //  上锁：
                lock.lock();
                try {
                    //  业务逻辑
                    //  缓存中没有数据。
                    skuInfo = getSkuInfoDB(skuId);
                    if (skuInfo == null) {
                        //  设置一个空对象
                        SkuInfo skuInfo1 = new SkuInfo();
                        //  放入缓存
                        this.redisTemplate.opsForValue().set(skuKey, skuInfo1, RedisConst.SKUKEY_TEMPORARY_TIMEOUT, TimeUnit.SECONDS);
                        //  返回空对象
                        return skuInfo1;
                    }
                    //  放入缓存
                    this.redisTemplate.opsForValue().set(skuKey, skuInfo, RedisConst.SKUKEY_TIMEOUT, TimeUnit.SECONDS);
                    return skuInfo;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    //  释放锁！
                    lock.unlock();
                }
            } else {
                //  返回缓存数据.
                return skuInfo;
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return getSkuInfoDB(skuId);
    }

    private SkuInfo getDataByRedis(Long skuId) {
        //  声明一个对象
        SkuInfo skuInfo = null;
        try {
            //  缓存存储数据 --- 1. 数据类型 String Hash Set List ZSet 数据类型使用场景！
            //  先判断缓存中是否有数据！ 组成缓存的key！ key 名不能重复！ sku:skuId:info
            String skuKey = RedisConst.SKUKEY_PREFIX + skuId + RedisConst.SKUKEY_SUFFIX;
            //  从缓存获取数据
            skuInfo = (SkuInfo) this.redisTemplate.opsForValue().get(skuKey);
            if (skuInfo == null) {
                //  考虑高并发，缓存击穿: 分布式锁！ sku:skuId:lock
                String skuLocKey = RedisConst.SKUKEY_PREFIX + skuId + RedisConst.SKULOCK_SUFFIX;
                //  生成一个随机的uuid
                String uuid = UUID.randomUUID().toString();
                //  set key value ex timeout nx;
                Boolean result = this.redisTemplate.opsForValue().setIfAbsent(skuLocKey, uuid, RedisConst.SKULOCK_EXPIRE_PX2, TimeUnit.SECONDS);
                //  判断获取锁结果：
                if (result) {
                    //  获取成功！
                    //  缓存中没有数据。
                    skuInfo = getSkuInfoDB(skuId);
                    if (skuInfo == null) {
                        //  设置一个空对象
                        SkuInfo skuInfo1 = new SkuInfo();
                        //  放入缓存
                        this.redisTemplate.opsForValue().set(skuKey, skuInfo1, RedisConst.SKUKEY_TEMPORARY_TIMEOUT, TimeUnit.SECONDS);
                        //  调用释放资源
                        this.expireKey(skuLocKey, uuid);
                        //  返回空对象
                        return skuInfo1;
                    }
                    //  放入缓存
                    this.redisTemplate.opsForValue().set(skuKey, skuInfo, RedisConst.SKUKEY_TIMEOUT, TimeUnit.SECONDS);
                    //  调用释放资源
                    this.expireKey(skuLocKey, uuid);
                    return skuInfo;
                } else {
                    //  获取失败
                    Thread.sleep(300);
                    return getSkuInfo(skuId);
                }
            } else {
                //  返回缓存中的数据
                return skuInfo;
            }
        } catch (InterruptedException e) {
            //  日志输出.
            e.printStackTrace();
        }
        //  如果有异常直接访问数据库！
        return getSkuInfoDB(skuId);
    }

    private void expireKey(String skuLocKey, String uuid) {
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        String script = "if redis.call(\"get\",KEYS[1]) == ARGV[1]\n" +
                "then\n" +
                "    return redis.call(\"del\",KEYS[1])\n" +
                "else\n" +
                "    return 0\n" +
                "end";

        redisScript.setScriptText(script);
        //设置响应类型
        redisScript.setResultType(Long.class);
        redisTemplate.execute(redisScript, Arrays.asList(skuLocKey), uuid);
    }

    private SkuInfo getSkuInfoDB(Long skuId) {
        SkuInfo skuInfo = skuInfoService.selectSkuInfoById(skuId);
        LambdaQueryWrapper<SkuImage> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SkuImage::getSkuId, skuId);
        List<SkuImage> skuImageList = skuImageService.list(lambdaQueryWrapper);
        if (!CollectionUtils.isEmpty(skuImageList)) {
            skuInfo.setSkuImageList(skuImageList);
        }
        return skuInfo;
    }

    @Override
    @GmallCache(prefex = "categoryView:")
    public BaseCategoryView getCategoryView(Long category3Id) {
        return baseCategoryViewMapper.selectById(category3Id);
    }

    //    @Autowired
//    private RedissonClient redissonClient;
    @Override
    @GmallCache(prefex = "SkuPrice:")
    public BigDecimal getSkuPrice(Long skuId) {
//        声明分布式锁获取对象
        String lockKey = skuId + ":lock";
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock();
        try {
            LambdaQueryWrapper<SkuInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(SkuInfo::getId, skuId).select(SkuInfo::getPrice);
            SkuInfo skuInfo = skuInfoService.getOne(lambdaQueryWrapper);
            if (skuInfo != null) {
                return skuInfo.getPrice();
            }
            return new BigDecimal(0);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    @Override
    @GmallCache(prefex = "attrList:")
    public List<SpuPoster> findSpuPosterBySpuId(Long spuId) {
        LambdaQueryWrapper<SpuPoster> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SpuPoster::getSpuId, spuId);
        return spuPosterService.list(lambdaQueryWrapper);
    }

    @Override
    @GmallCache(prefex = "AttrList:")
    public List<BaseAttrInfo> getAttrList(Long skuId) {

        return baseAttrInfoMapper.selectAttrList(skuId);
    }

    @Override
    @GmallCache(prefex = "SkuValueIdsMap:")
    public Map getSkuValueIdsMap(Long spuId) {
        //  创建map 集合
        HashMap<Object, Object> map = new HashMap<>();
        //  获取到数据：
        List<Map> mapList = skuSaleAttrValueMapper.selectSkuValueIdsMap(spuId);
        mapList.forEach(map1 -> map.put(map1.get("values_ids"), map1.get("sku_id")));
        //  返回数据
        return map;
    }

    @Autowired
    private SpuSaleAttrMapper spuSaleAttrMapper;

    @Override
    @GmallCache(prefex = "SpuSaleAttrListCheckBySku:")
    public List<SpuSaleAttr> getSpuSaleAttrListCheckBySku(Long skuId, Long spuId) {

        return spuSaleAttrMapper.selectSpuSaleAttrListCheckBySku(skuId, spuId);
    }

    @Override
    @GmallCache(prefex = "SpuPoster:")
    public List<SpuPoster> getSpuPosterBySpuId(Long spuId) {
        return spuPosterMapper.selectList(new LambdaQueryWrapper<SpuPoster>().eq(SpuPoster::getSpuId, spuId));
    }

}
