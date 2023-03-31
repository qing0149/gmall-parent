package com.atguigu.gmall;// 直接赋值粘贴，删除CSDN的权限转载中文

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: gmall-parent
 * @description:
 * @author: Mr.Like
 * @create: 2023-03-31 21:14
 **/
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApp {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApp.class, args);
    }

}
