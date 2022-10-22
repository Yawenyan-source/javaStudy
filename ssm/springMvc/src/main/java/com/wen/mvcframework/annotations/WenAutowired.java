package com.wen.mvcframework.annotations;

import java.lang.annotation.*;

/**
 * @author wen
 * @version 1.0
 * @project javaStudy
 * @description 自定义Autowired注解
 * @since 2022/10/18 21:25:22
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WenAutowired {
    String value() default "";
}
