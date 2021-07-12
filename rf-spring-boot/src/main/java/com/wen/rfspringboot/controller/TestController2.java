package com.wen.rfspringboot.controller;

import com.wen.rfspringboot.manage.OrderManage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController2 {
    @Autowired
    OrderManage orderManage;

    @GetMapping("addOrder")
    public String addOrder() {
        log.info("<1>");
        orderManage.asyncLog();
        log.info("<3>");
        return "3";
    }
}
