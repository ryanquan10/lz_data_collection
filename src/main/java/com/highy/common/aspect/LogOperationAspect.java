/**
 * Copyright (c) 2018 high哥工作室 All rights reserved.
 *
 * https://www.jchaoy.io
 *
 * 版权所有，侵权必究！
 */

package com.highy.common.aspect;

import com.alibaba.fastjson.JSON;
import com.highy.common.annotation.LogOperation;
import com.highy.common.utils.HttpContextUtils;
import com.highy.common.utils.IpUtils;
import com.highy.modules.log.entity.SysLogOperationEntity;
import com.highy.modules.log.enums.OperationStatusEnum;
import com.highy.modules.log.service.SysLogOperationService;
import com.highy.modules.security.user.SecurityUser;
import com.highy.modules.security.user.UserDetail;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 操作日志，切面处理类
 *
 * @author jchaoy 453428948@qq.com
 */
@Aspect
@Component
public class LogOperationAspect {
    @Autowired
    private SysLogOperationService sysLogOperationService;

    @Pointcut("@annotation(com.highy.common.annotation.LogOperation)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        try {
            //执行方法
            Object result = point.proceed();

            //执行时长(毫秒)
            long time = System.currentTimeMillis() - beginTime;
            //保存日志
            saveLog(point, time, OperationStatusEnum.SUCCESS.value());

            return result;
        }catch(Exception e) {
            //执行时长(毫秒)
            long time = System.currentTimeMillis() - beginTime;
            //保存日志
            saveLog(point, time, OperationStatusEnum.FAIL.value());

            throw e;
        }
    }

    private void saveLog(ProceedingJoinPoint joinPoint, long time, Integer status) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        SysLogOperationEntity log = new SysLogOperationEntity();
        LogOperation annotation = method.getAnnotation(LogOperation.class);
        if(annotation != null){
            //注解上的描述
            log.setOperation(annotation.value());
        }

        //登录用户信息
        UserDetail user = SecurityUser.getUser();
        if(user != null){
            log.setCreatorName(user.getUsername());
        }

        log.setStatus(status);
        log.setRequestTime((int)time);

        //请求相关信息
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        log.setIp(IpUtils.getIpAddr(request));
        log.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
        log.setRequestUri(request.getRequestURI());
        log.setRequestMethod(request.getMethod());

        //请求参数
        Object[] args = joinPoint.getArgs();
        try{
            String params = JSON.toJSONString(args[0]);
            log.setRequestParams(params);
        }catch (Exception e){

        }

        //保存到DB
        sysLogOperationService.save(log);
    }
}