package com.wen.rfspringboot.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WenCurrentLimit {
    /**
     * name: 限流的名称 默认值为空
     *
     * @return 返回传入的名称
     */
    String name() default "";

    /**
     * token: 每秒访问次数 默认为20.0
     *
     * @return 返回传入的token值
     */
    double token() default 20.0;
}
