package com.wen;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wen.mapper.IOrderMapper;
import com.wen.mapper.IUserMapper;
import com.wen.pojo.Order;
import com.wen.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
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

    private IUserMapper userMapper;

    @Before
    public void before() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        userMapper = sqlSession.getMapper(IUserMapper.class);
    }

    @Test
    public void testAddUser() {
        User user = new User();
        user.setId(10);
        user.setUsername("wen");
        userMapper.addUser(user);
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setId(10);
        user.setUsername("gsl");
        user.setPassword("gsl666");
        userMapper.updateUser(user);
    }

    @Test
    public void testSelectUser() {
        for (User user : userMapper.selectUser()) {
            System.out.println(user);
        }
    }

    @Test
    public void testDeleteUser() {
        userMapper.deleteUser(10);
    }

    @Test
    public void oneToMany() {
        List<User> users = userMapper.findUserAndOrder();
        for (User user : users) {
            System.out.println(user);
        }

    }

    @Test
    public void pageHelperTest() {
        PageHelper.startPage(2, 1);
        List<User> userList = userMapper.selectUser();
        for (User user : userList) {
            System.out.println(user);
        }
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        System.out.println(pageInfo.getTotal());
    }

}
