package com.wen.rfspringboot.controller;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    /*
     * 每秒生成1.0个令牌
     * */
    private RateLimiter rateLimiter = RateLimiter.create(1.0);

    @GetMapping("/get")
    public String get() {
        boolean b = rateLimiter.tryAcquire();
        if (!b) {
            return "访问人数过多";
        }
        return "my is get";
    }
}
