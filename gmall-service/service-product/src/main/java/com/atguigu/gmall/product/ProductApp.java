package com.atguigu.gmall.product;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName ProductApp
 * @Description TODO
 * @Author qing
 * @Date 2023/3/29 15:52
 * @Version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = "com.atguigu")
public class ProductApp {
    public static void main(String[] args) {
        ConfigurableListableBeanFactory beanFactory = SpringApplication.run(ProductApp.class).getBeanFactory();
//        beanFactory.getBeanNamesIterator().forEachRemaining(System.out::println);
    }
}
