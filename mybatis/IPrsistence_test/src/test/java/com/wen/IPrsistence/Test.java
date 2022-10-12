package com.wen.IPrsistence;

import com.wen.dao.IUserDao;
import com.wen.io.Resources;
import com.wen.pojo.User;
import com.wen.sqlSession.SqlSession;
import com.wen.sqlSession.SqlSessionFactory;
import com.wen.sqlSession.SqlSessionFactoryBuilder;
import org.dom4j.DocumentException;

import java.io.InputStream;
import java.util.List;

/**
 * @author Wen
 * @date 2022年09月27日 10:39 PM
 */
public class Test {
    public static void main(String[] args) throws DocumentException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //利用jdk动态代理生成的代理实现类
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        List<User> userList = userDao.findAll();
        System.out.println(userList);
    }
}
