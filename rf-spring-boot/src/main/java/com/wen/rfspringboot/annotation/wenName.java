package com.wen.rfspringboot.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解
 * 元注解：
 * Target: 表示注解只能被运用到什么地方;Method:方法上；FIELD:属性上；TYPE：类上
 * Retention: 指定新注解的信息保留到什么时候，取值有Retention.RUNTIME等
 * Inherited: 指定新注解标注在父类上时可被子类继承
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface wenName {
}
