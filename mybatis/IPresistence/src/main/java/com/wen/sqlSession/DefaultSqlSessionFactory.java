package com.wen.sqlSession;

import com.wen.pojo.Configuration;

/**
 * @author Wen
 * @date 2022年09月28日 11:23 PM
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public String toString() {
        return "DefaultSqlSessionFactory{" +
                "configuration=" + configuration +
                '}';
    }

    /**
     * 创建默认的SqlSession对象
     * @return
     */
    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
