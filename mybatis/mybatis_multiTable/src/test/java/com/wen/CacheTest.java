package com.wen;

import com.wen.mapper.IUserMapper;
import com.wen.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Wen
 * @date 2022年10月01日 10:33 PM
 * 测试mybatis 缓存
 */
public class CacheTest {
    private IUserMapper userMapper;
    private SqlSession sqlSession;

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void before() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession(true);
        userMapper = sqlSession.getMapper(IUserMapper.class);
    }

    /**
     * 测试一级缓存
     */
    @Test()
    public void firstLevelCache() {
        //第一次查询
        User user1 = userMapper.findUserById(1);
        User user2 = userMapper.findUserById(1);
        System.out.println(user1 == user2);
    }

    /**
     * 测试二级缓存
     * 二级缓存缓存的是数据，不是对象
     */
    @Test()
    public void secondLevelCache() {
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        IUserMapper userMapper1 = sqlSession1.getMapper(IUserMapper.class);
        User user1 = userMapper1.findUserById(1);
        sqlSession1.close();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        IUserMapper userMapper2 = sqlSession2.getMapper(IUserMapper.class);
        User user2 = userMapper2.findUserById(1);
        SqlSession sqlSession3 = sqlSessionFactory.openSession();
        IUserMapper userMapper3 = sqlSession3.getMapper(IUserMapper.class);
        User user3 = userMapper3.findUserById(1);
        System.out.println(user1 == user2);
    }
}
