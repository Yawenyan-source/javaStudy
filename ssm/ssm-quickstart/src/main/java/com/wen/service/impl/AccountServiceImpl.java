package com.wen.service.impl;

import com.wen.mapper.AccountMapper;
import com.wen.pojo.Account;
import com.wen.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wen
 * @version 1.0
 * @project javaStudy
 * @description
 * @since 2022/10/29 22:29:33
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public List<Account> queryAccountList() {
        return accountMapper.selectAccountList();
    }
}
