package com.wen;

import com.lagou.edu.dao.AccountDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Wen
 * @date 2022年10月07日 4:33 PM
 */
public class IocTest {
    @Test
    public void testIoc() {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        AccountDao accountDao = (AccountDao) context.getBean("accountDao");
        System.out.println(accountDao);
    }
}
