package com.wen.mvcframework.annotations;

import java.lang.annotation.*;

/**
 * @author wen
 * @version 1.0
 * @project javaStudy
 * @description 自定义requestMapping注解
 * @since 2022/10/18 21:25:22
 */
@Documented
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface WenRequestMapping {
    String value() default "";
}
