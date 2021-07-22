//package com.highy.common.config;
//
//
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
//import java.util.Collections;
//
// 大坑 会拦截所有请求 包括 不相关的 put get 请求
//@Configuration
//public class CorsConfig {
//
//
//
//    @Bean
//    public FilterRegistrationBean simpleCorsFilter() {
//        System.out.println("----------------simpleCorsFilter---------------");
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        // *** URL below needs to match the Vue client URL and port ***
////        config.setAllowedOrigins(Collections.singletonList("http://192.168.233.135:8888"));
//        config.setAllowedOrigins(Collections.singletonList("http://algaemedical.com"));
////        config.setAllowedMethods(Collections.singletonList("*"));
//        config.setAllowedHeaders(Collections.singletonList("*"));
//        config.addAllowedMethod("get");
//        config.addAllowedMethod("post");
//        config.addAllowedMethod("OPTIONS");
//        source.registerCorsConfiguration("/**", config);
//        FilterRegistrationBean bean = new FilterRegistrationBean<>(new CorsFilter(source));
////        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        return bean;
//    }
//}
