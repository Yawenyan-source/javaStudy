package com.wen.rfspringboot.controller;

import com.wen.rfspringboot.annotation.WenCurrentLimit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @GetMapping("/get")
    @WenCurrentLimit(name = "get", token = 1.0)
    public String get() {
        return "my is get";
    }

    @GetMapping("/add")
    @WenCurrentLimit(name = "add", token = 2.0)
    public String add() {
        return "my is add";
    }
}
