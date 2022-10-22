package com.wen.mvcframework.annotations;

import java.lang.annotation.*;

/**
 * @author wen
 * @version 1.0
 * @project javaStudy
 * @description 自定义service注解
 * @since 2022/10/18 21:25:22
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface WenService {
    String value() default "";
}
