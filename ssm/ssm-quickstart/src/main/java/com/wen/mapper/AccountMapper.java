package com.wen.mapper;

import com.wen.pojo.Account;

import java.util.List;

/**
 * @author wen
 * @version 1.0
 * @project javaStudy
 * @description
 * @since 2022/10/29 21:15:17
 */
public interface AccountMapper {
    List<Account> selectAccountList();
}
