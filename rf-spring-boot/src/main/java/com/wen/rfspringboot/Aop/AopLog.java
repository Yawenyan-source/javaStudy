package com.wen.rfspringboot.Aop;

import com.alibaba.fastjson.JSON;
import com.wen.rfspringboot.manage.LogManage;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Aspect
@Component
@Slf4j
public class AopLog {
    private static final String START_TIME = "request-start";
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
    @Autowired
    private LogManage logManage;

    /**
     * 切入点
     */
    @Pointcut("execution(public * com.wen.rfspringboot.controller.*Controller.*(..))")
    public void log() {
    }

    /**
     * 前置操作
     *
     * @param point 切入点
     */
    @Before("log()")
    public void beforeLog(JoinPoint point) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();

        logManage.info("【请求 时间】：" + sdf.format(new Date()));
        logManage.info("【请求 URL】：" + request.getRequestURL());
        logManage.info("【请求 IP】：" + request.getRemoteAddr());
        logManage.info("【类名 Class】：" + point.getSignature().getDeclaringTypeName());
        logManage.info("【方法名 Method】：" + point.getSignature().getName());
        logManage.info("【请求参数 Args】：" +
                JSON.toJSONString(point.getArgs())
        );
//        log.info("【请求 时间】：{}", System.currentTimeMillis());
//        log.info("【请求 URL】：{}", request.getRequestURL());
//        log.info("【请求 IP】：{}", request.getRemoteAddr());
//        log.info("【类名 Class】：{}，【方法名 Method】：{}", point.getSignature().getDeclaringTypeName(), point.getSignature().getName());
//        log.info("【请求参数 Args】：{}，", point.getArgs());
    }

    @After("log()")
    public void afterLog(JoinPoint point) {
        logManage.info("结束时间：" + sdf.format(new Date()));
    }

}