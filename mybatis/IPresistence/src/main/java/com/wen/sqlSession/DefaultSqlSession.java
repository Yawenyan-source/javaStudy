package com.wen.sqlSession;

import com.wen.pojo.Configuration;
import com.wen.pojo.MappedStatement;

import java.lang.reflect.*;
import java.util.List;

/**
 * @author Wen
 * @date 2022年09月28日 11:29 PM
 * 默认SqlSession实现类
 */
public class DefaultSqlSession implements SqlSession {
    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <E> List<E> selectList(String statementId, Object... params) {
        SimpleExecutor simpleExecutor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementId);
        List<Object> list = simpleExecutor.query(
                configuration,
                mappedStatement,
                params);
        return (List<E>) list;
    }

    @Override
    public <T> T selectOne(String statementId, Object... params) {
        List<Object> objects = selectList(statementId, params);
        if (objects.size() == 1) {
            return (T) objects.get(0);
        } else {
            throw new RuntimeException("查询结果为空或者返回结果过多");
        }
    }

    @Override
    public <T> T getMapper(Class<?> mapperClass) {
        //使用Jdk动态代理为Dao接口生成代理实现类
        Object newProxyInstance = Proxy.newProxyInstance(
                SqlSessionFactory.class.getClassLoader(),
                new Class[]{mapperClass},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //底层还是去执行JDBC代码，根据不同情况去调用selectList或者selectOne
                        //准备参数：1、statementId: sql语句的唯一标识：namespace.id = 接口全限定类名+方法名
                        //方法名
                        String methodName = method.getName();
                        //接口全限定类名
                        String className = method.getDeclaringClass().getName();
                        String statementId = className + "." + methodName;
                        //参数2、params = args
                        //获取被调用方法的返回值对象
                        Type genericReturnType = method.getGenericReturnType();
                        //判断是否进行了泛型类型参数化(参数化类型)
                        if (genericReturnType instanceof ParameterizedType) {
                            List<Object> objects = selectList(statementId, args);
                            return objects;
                        }
                        return selectOne(statementId, args);
                    }
                });
        return (T) newProxyInstance;
    }
}
