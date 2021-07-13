package com.wen.rfspringboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Scope(value = "prototype")
public class TestController3 {
    private int count = 0;

    /**
     * spring 默认bean 对象都是单例的
     * springMvc 方法中使用synchronized,实际上是单线程，每个人依次来访问
     *
     * @return
     */
    @GetMapping("/count")
    public synchronized String count() {
        try {
            log.info("<count>" + count++);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {

        }
        return "count";
    }
}
