package com.wen.rfspringboot.Aop;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ThreadAsyncAop {
    /**
     * 环绕通知
     * 拦截方法
     *
     * @param joinPoint
     * @return
     */
    @Around(value = "@annotation(com.wen.rfspringboot.annotation.WenAsync)")
    public void around(ProceedingJoinPoint joinPoint) {
        try {
            log.info("环绕通知开始执行");
            new Thread(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    joinPoint.proceed();//目标方法
                }
            }).start();
            log.info("环绕通知结束执行");
        } catch (Throwable throwable) {

        }
    }
}
