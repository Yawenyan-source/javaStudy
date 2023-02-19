package com.wen.rfspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;

/**
 * @author wen
 * @version 1.0
 * @project javaStudy
 * @description springboot测试thymeleaf
 * @since 2023/2/15 23:01:32
 */
@Controller
public class LoginController {

    @RequestMapping("/toLoginPage")
    public String toLoginPage(Model model) {
        model.addAttribute("currentYear", Calendar.getInstance().get(Calendar.YEAR));
        return "login";
    }
}
