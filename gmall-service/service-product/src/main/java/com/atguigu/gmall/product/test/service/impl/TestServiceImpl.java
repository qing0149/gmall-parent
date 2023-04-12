package com.atguigu.gmall.product.test.service.impl;// 直接赋值粘贴，删除CSDN的权限转载中文

import com.atguigu.gmall.product.test.service.TestService;
import org.apache.commons.lang.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @program: gmall-parent
 * @description:
 * @author: jq
 * @create: 2023-04-11 16:27
 **/
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redissonClient;

//    @Override
//    public void testLock() {
////        redissonClient.getLock("lock");
//        String uuid = UUID.randomUUID().toString().replace("-", "");
//        Boolean flag = redisTemplate.opsForValue().setIfAbsent("lock", uuid, 3, TimeUnit.SECONDS);
//        if (flag) {
//            String value = this.redisTemplate.opsForValue().get("num");
//            if (StringUtils.isEmpty(value)) {
//                return;
//            }
//            int num = Integer.parseInt(value);
//            this.redisTemplate.opsForValue().set("num", String.valueOf(++num));
//
////            Thread thread = new Thread(() -> {
////                this.redisTemplate.expire("lock", 3, TimeUnit.SECONDS);
////            });
////            thread.setDaemon(true);
////            thread.start();
////            while (uuid.equals(this.redisTemplate.opsForValue().get("lock"))) {
////                redisTemplate.delete("lock");
////            }
//            DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
//            String script = "if redis.call(\"get\",KEYS[1]) == ARGV[1]\n" +
//                    "then\n" +
//                    "    return redis.call(\"del\",KEYS[1])\n" +
//                    "else\n" +
//                    "    return 0\n" +
//                    "end";
//
//            redisScript.setScriptText(script);
//            //设置响应类型
//            redisScript.setResultType(Long.class);
//            redisTemplate.execute(redisScript, Arrays.asList("lock"), uuid);
//
//
//        } else {
//            try {
//                Thread.sleep(10);
//                this.testLock();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }

//
    @Override
    public void testLock() {
        //获取到锁对象
        RLock lock = redissonClient.getLock("lock");
        //上锁
        lock.lock();
        try {
            String value = this.redisTemplate.opsForValue().get("num");
            if (StringUtils.isEmpty(value)) {
                return;
            }
            int num = Integer.parseInt(value);
            this.redisTemplate.opsForValue().set("num", String.valueOf(++num));
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        } finally {
            //解锁
            lock.unlock();
        }
    }
}
