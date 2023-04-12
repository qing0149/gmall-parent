package com.atguigu.gmall.cache.aspect;// 直接赋值粘贴，删除CSDN的权限转载中文

import com.atguigu.gmall.cache.GmallCache;
import com.atguigu.gmall.constant.RedisConst;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @program: gmall-parent
 * @description:
 * @author: jq
 * @create: 2023-04-12 18:49
 **/
@Component
@Aspect
public class GmallCacheAspect {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    /**
     * @param proceedingJoinPoint 能够获取请求之前的参数，请求的方法体，返回值等信息
     * @return
     */

    @Around("@annotation(com.atguigu.gmall.cache.GmallCache)")
    @SneakyThrows
    public Object cacheAspect(ProceedingJoinPoint proceedingJoinPoint) {
//        声明一个对象
        Object obj = new Object();
        /*
         * 1.实现分布式锁的逻辑
         * 获取到缓存的key;注解前缀+参数+注解后缀
         * 获取到方法签名
         * */
        //获取签名
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        GmallCache annotation = signature.getMethod().getAnnotation(GmallCache.class);
        //获取参数
        Object[] args = proceedingJoinPoint.getArgs();
        //获取前缀
        String prefex = annotation.prefex();
        //获取后缀
        String suffix = annotation.suffix();
//        组成缓存的key
        String skuKey = prefex + Arrays.asList(args) + suffix;
        try {
            obj = this.redisTemplate.opsForValue().get(skuKey);
            if (obj == null) {
//                查询数据库,加一把锁
                String lockKey = prefex + ":lock";
                RLock lock = this.redissonClient.getLock(lockKey);
                lock.lock();
                try {
//                    查询数据库
                    obj = proceedingJoinPoint.proceed(args);
                    if (obj == null) {
                        Object o = new Object();
                        //                    放入缓存
                        this.redisTemplate.opsForValue().set(skuKey, o, RedisConst.SKUKEY_TEMPORARY_TIMEOUT, TimeUnit.SECONDS);
                        return o;
                    }
                    this.redisTemplate.opsForValue().set(skuKey, obj, RedisConst.SKUKEY_TIMEOUT, TimeUnit.SECONDS);
                    return obj;
                } catch (Throwable e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            } else {
                return obj;
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return proceedingJoinPoint.proceed(args);
    }
}
