package com.atguigu.gmall.cache;// 直接赋值粘贴，删除CSDN的权限转载中文

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: gmall-parent
 * @description:
 * @author: jq
 * @create: 2023-04-12 18:28
 **/

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GmallCache {
    /*
     * 缓存数据前缀
     * */
    String prefex() default "cache:";
    /*
    缓存数据后缀
     */
    String suffix() default ":info";
}

