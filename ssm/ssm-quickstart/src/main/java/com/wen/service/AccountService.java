package com.wen.service;

import com.wen.pojo.Account;

import java.util.List;

/**
 * @author wen
 * @version 1.0
 * @project javaStudy
 * @description
 * @since 2022/10/29 22:29:12
 */
public interface AccountService {
    List<Account> queryAccountList();
}
