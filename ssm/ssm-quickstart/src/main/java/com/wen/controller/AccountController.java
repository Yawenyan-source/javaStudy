package com.wen.controller;

import com.wen.pojo.Account;
import com.wen.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author wen
 * @version 1.0
 * @project javaStudy
 * @description
 * @since 2022/10/29 23:04:03
 */
@RequestMapping("/account")
@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/query")
    @ResponseBody
    public List<Account> queryAccount() {
        return accountService.queryAccountList();
    }
}

