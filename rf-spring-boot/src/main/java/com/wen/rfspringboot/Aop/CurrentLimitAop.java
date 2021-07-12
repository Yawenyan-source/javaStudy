package com.wen.rfspringboot.Aop;

import com.google.common.util.concurrent.RateLimiter;
import com.wen.rfspringboot.annotation.WenCurrentLimit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
public class CurrentLimitAop {
    /*
     * 每秒生成1.0个令牌
     * */
    private RateLimiter rateLimiter = RateLimiter.create(1.0);
    private ConcurrentHashMap<String, RateLimiter> rateLimiters = new ConcurrentHashMap<>();

    /**
     * 只要在方法上有加该自定义的限流注解，就会被aop环绕通知
     *
     * @param joinPoint
     * @return
     */
    @Around(value = "@annotation(com.wen.rfspringboot.annotation.WenCurrentLimit)")
    public Object around(ProceedingJoinPoint joinPoint) {
        try {
            //获取拦截的方法名
            Signature signature = joinPoint.getSignature();
            //获取拦截的方法名
            MethodSignature methodSignature = (MethodSignature) signature;
            WenCurrentLimit wenCurrentLimit = methodSignature.getMethod().getDeclaredAnnotation(WenCurrentLimit.class);

            //获取该注解的name
            String name = wenCurrentLimit.name();
            //通过注解上的name，获取到对应的rateLimiter
            RateLimiter rateLimiter = rateLimiters.get(name);
            //获取该注解的token
            double token = wenCurrentLimit.token();
            //如果没有对应的rateLimiter，则创建一个新的rateLimiter
            if (rateLimiter == null) {
                rateLimiter = RateLimiter.create(token);
                //将这个rateLimiter保存起来
                rateLimiters.put(name, rateLimiter);
            }
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
