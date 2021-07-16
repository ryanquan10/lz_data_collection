package com.highy.modules.security.filter;

import org.apache.shiro.SecurityUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//ajax不能应对Filter,所以  用一个Filter 来对应 专用的 ajax 请求
//参考 https://blog.csdn.net/yb546822612/article/details/102950981

//"http://192.168.233.142:8888/dataAnalysis/downloadimg/download?fileName="

public class A1axCheckTimeOutFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        System.out.println("here are CheckTimeOutFilter doFilter()");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        System.out.println(request.getRequestURL());
        String basePath = request.getContextPath();
        request.setAttribute("basePath", basePath);
        if (!SecurityUtils.getSubject().isAuthenticated()) {
            //判断session里是否有用户信息
            if (request.getHeader("x-requested-with") != null
                    && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
                //如果是ajax请求响应头会有，x-requested-with
                System.out.println("request.getHeader(\"x-requested-with\")  =>" + request.getHeader("x-requested-with") );
                response.setHeader("session-status", "timeout");//在响应头设置session状态

                System.out.println("cut down");
                return;
            }
        }
        filterChain.doFilter(request, servletResponse);
    }
}
