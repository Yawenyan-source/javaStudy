package com.wen.rfspringboot.Aop;

import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CurrentLimitAop {
    /*
     * 每秒生成1.0个令牌
     * */
    private RateLimiter rateLimiter = RateLimiter.create(1.0);

    /**
     * 只要在方法上有加该自定义的限流注解，就会被aop环绕通知
     *
     * @param joinPoint
     * @return
     */
    @Around(value = "@annotation(com.wen.rfspringboot.annotation.WenCurrentLimit)")
    public Object around(ProceedingJoinPoint joinPoint) {
        try {
            boolean b = rateLimiter.tryAcquire();
            if (!b) {
                return "访问人数过多，请稍后重试";
            }
            System.out.println("环绕通知开始执行");
            Object result = joinPoint.proceed();
            System.out.println("环绕通知结束执行");
            return result;
        } catch (Throwable throwable) {
            return "系统错误";
        }
    }
}
