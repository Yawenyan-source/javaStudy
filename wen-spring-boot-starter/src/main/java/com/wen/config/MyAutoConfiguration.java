package com.wen.config;

import com.wen.pojo.SimpleBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wen
 * @version 1.0
 * @project javaStudy
 * @description 自动配置类
 * @since 2023/2/12 21:38:49
 */
@Configuration
@ConditionalOnClass(SimpleBean.class) // 当类路径classpath下有指定的类，就会进行自动配置
public class MyAutoConfiguration {

    static {
        System.out.println("MyAutoConfiguration init!!!!!!");
    }

    @Bean
    public SimpleBean simpleBean() {
        return new SimpleBean();
    }
}
