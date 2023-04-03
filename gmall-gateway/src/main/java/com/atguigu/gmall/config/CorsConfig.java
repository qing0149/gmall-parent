package com.atguigu.gmall.config;// 直接赋值粘贴，删除CSDN的权限转载中文

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.server.WebFilter;

/**
 * @program: gmall-parent
 * @description:
 * @author: jq
 * @create: 2023-04-01 10:05
 **/
@Configuration
public class CorsConfig {
    //编写一个webFilter,讲这个对象直接注入Spring容器中
    @Bean
    public WebFilter webFilter() {
        //CorsConfiguration
        CorsConfiguration corsConfiguration = new CorsConfiguration();
//       1. 配置允许访问的请求头
        corsConfiguration.addAllowedHeader("*");
//        2.配置允许访问的方式
        corsConfiguration.addAllowedMethod("*");
//        3.配置允许访问的域名
        corsConfiguration.addAllowedOrigin("*");
//        4.配置是否允许提交cookie
        corsConfiguration.setAllowCredentials(true);
//        5.配置预检有效时间
        corsConfiguration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsWebFilter(urlBasedCorsConfigurationSource);
    }

}
