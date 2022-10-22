package com.wen.demo.controller;

import com.wen.demo.service.IDemoService;
import com.wen.mvcframework.annotations.WenAutowired;
import com.wen.mvcframework.annotations.WenController;
import com.wen.mvcframework.annotations.WenRequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author wen
 * @version 1.0
 * @project javaStudy
 * @description 测试自定义MVC-Controller
 * @since 2022/10/19 20:33:42
 */
@WenController
@WenRequestMapping("/demo")
public class DemoController {
    @WenAutowired
    private IDemoService demoService;

    @WenRequestMapping("/query")
    public String query(HttpServletRequest request, HttpServletResponse response, String name) {
        String s = demoService.get(name);
        return s;
    }
}
