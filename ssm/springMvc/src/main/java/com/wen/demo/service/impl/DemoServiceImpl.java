package com.wen.demo.service.impl;

import com.wen.demo.service.IDemoService;
import com.wen.mvcframework.annotations.WenService;

/**
 * @author wen
 * @version 1.0
 * @project javaStudy
 * @description 测试自定义MVC-Service实现类
 * @since 2022/10/19 20:36:29
 */
@WenService
public class DemoServiceImpl implements IDemoService {
    @Override
    public String get(String name) {
        System.out.println("service实现类中的name:" + name);
        return name;
    }
}
