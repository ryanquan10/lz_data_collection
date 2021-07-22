/**
 * Copyright (c) 2018 high哥工作室 All rights reserved.
 *
 * https://www.jchaoy.io
 *
 * 版权所有，侵权必究！
 */

package com.highy.modules.security.config;

import com.highy.common.xss.XssFilter;
//import com.highy.modules.security.filter.A1axCheckTimeOutFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.DispatcherType;

/**
 * Filter配置
 *
 * @author jchaoy 453428948@qq.com
 */
@Configuration
public class FilterConfig {


//    @Bean
//
//    public FilterRegistrationBean AjaxCheckTimeOutFilter(){
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(new A1axCheckTimeOutFilter());
//        registration.addUrlPatterns("/downloadimg/*");
//        registration.setName("ajaxTimeOutFilter");
//        return registration;
//    }


    @Bean
    public FilterRegistrationBean shiroFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        //该值缺省为false，表示生命周期由SpringApplicationContext管理，设置为true则表示由ServletContainer管理
        registration.addInitParameter("targetFilterLifecycle", "true");
        registration.setEnabled(true);
        registration.setOrder(Integer.MAX_VALUE - 1);
        registration.addUrlPatterns("/*");
        return registration;
    }

    @Bean
    public FilterRegistrationBean xssFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setOrder(Integer.MAX_VALUE);
        registration.setFilter(new XssFilter());
        registration.addUrlPatterns("/*");
        registration.setName("xssFilter");
        return registration;
    }


    /**
     * "http://8.129.59.18:8888/dataAnalysis/downloadimg/download?fileName="
     */

}
