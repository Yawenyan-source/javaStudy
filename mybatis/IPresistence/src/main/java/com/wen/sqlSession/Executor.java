package com.wen.sqlSession;

import com.wen.pojo.Configuration;
import com.wen.pojo.MappedStatement;

import java.util.List;

/**
 * @author Wen
 * @date 2022年09月29日 10:05 PM
 */
public interface Executor {
    /**
     * 使用JDBC封装查询方法
     *
     * @param configuration
     * @param mappedStatement
     * @param params
     * @param <E>
     * @return
     */
    <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params);
}
