package com.wen.sqlSession;

/**
 * @author Wen
 * @date 2022年09月28日 10:14 PM
 * SqlSession工厂，创建SqlSession
 */
public interface SqlSessionFactory {
    SqlSession openSession();
}
