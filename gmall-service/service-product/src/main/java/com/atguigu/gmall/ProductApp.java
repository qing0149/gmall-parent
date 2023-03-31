package com.atguigu.gmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName ProductApp
 * @Description TODO
 * @Author qing
 * @Date 2023/3/29 15:52
 * @Version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ProductApp {
    public static void main(String[] args) {
        SpringApplication.run(ProductApp.class);
    }
}
