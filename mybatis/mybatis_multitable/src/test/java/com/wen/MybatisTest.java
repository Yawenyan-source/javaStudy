package com.wen;

import com.wen.mapper.IOrderMapper;
import com.wen.mapper.IUserMapper;
import com.wen.pojo.Order;
import com.wen.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author Wen
 * @date 2022年10月01日 4:10 PM
 */
public class MybatisTest {
    @Test
    public void test1() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IOrderMapper userMapper = sqlSession.getMapper(IOrderMapper.class);
        List<Order> orderList = userMapper.findOrderAndUser();
        for (Order order : orderList) {
            System.out.println(order);
        }
    }
    @Test
    public void test2() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserMapper userMapper = sqlSession.getMapper(IUserMapper.class);
        for (User user : userMapper.findUserAndOrder()) {
            System.out.println(user);
        }
    }
    @Test
    public void test3() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserMapper userMapper = sqlSession.getMapper(IUserMapper.class);
        for (User user : userMapper.findAllUserAndRole()) {
            System.out.println(user);
        }

    }
}
