package com.wen.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author wen
 * @version 1.0
 * @project javaStudy
 * @description 简单的javaBean
 * @since 2023/2/12 21:32:27
 */
@EnableConfigurationProperties(SimpleBean.class) //开启ConfigurationProperties
@ConfigurationProperties(prefix = "simplebean")
public class SimpleBean {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SimpleBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
