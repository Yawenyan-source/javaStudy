package com.wen;

import com.wen.pojo.Account;
import com.wen.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author wen
 * @version 1.0
 * @project javaStudy
 * @description
 * @since 2022/10/29 22:49:04
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext*.xml"})
public class TestSpringMyBatis {
    @Autowired
    private AccountService accountService;

    @Test
    public void test() {
        List<Account> accounts = accountService.queryAccountList();
        System.out.println(accounts);
    }
}
