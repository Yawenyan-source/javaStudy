package com.wen.sqlSession;

import java.util.List;

/**
 * @author Wen
 * @date 2022年09月28日 11:28 PM
 * 会话对象
 */
public interface SqlSession {
    /**
     * 返回多条数据
     *
     * @param statementId statement的唯一标识
     * @param params      查询多条数据时的参数，比如模糊查询之类的
     * @param <E>         代表这是一个泛型方法
     * @return
     */
    <E> List<E> selectList(String statementId, Object... params);

    /**
     * 根据结果返回单条数据
     *
     * @param statementId statement的唯一标识
     * @param params      查询单条数据时的参数
     * @param <T>         代表这是一个泛型方法
     * @return
     */
    <T> T selectOne(String statementId, Object... params);

    <T> T getMapper(Class<?> mapperClass);
}
