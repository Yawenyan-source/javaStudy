package com.wen.sqlSession;

import com.wen.pojo.Configuration;
import com.wen.pojo.MappedStatement;
import com.wen.utils.GenericTokenParser;
import com.wen.utils.ParameterMapping;
import com.wen.utils.ParameterMappingTokenHandler;

import javax.sql.DataSource;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wen
 * @date 2022年09月29日 10:07 PM
 * 执行JDBC代码
 */
public class SimpleExecutor implements Executor {
    @Override
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) {
        DataSource dataSource = configuration.getDataSource();
        List<Object> objectList;
        try {
            //获取连接
            Connection connection = dataSource.getConnection();
            //获取sql语句
            String sql = mappedStatement.getSql();
            //转换sql
            BoundSql boundSql = getBoundSql(sql);
            //获取预处理对象 prepareStatement
            PreparedStatement prepareStatement = connection.prepareStatement(boundSql.getSqlText());
            //设置参数
            //获取参数的全路径
            String parameterType = mappedStatement.getParameterType();
            Class<?> parameterTypeClass = getClassType(parameterType);
            List<ParameterMapping> parameterMappings = boundSql.getParameterMappingList();
            for (int i = 0; i < parameterMappings.size(); i++) {
                ParameterMapping parameterMapping = parameterMappings.get(i);
                //sql语句中#{}中的值
                String content = parameterMapping.getContent();
                //反射
                Field declaredField = parameterTypeClass.getDeclaredField(content);
                //设置一下暴力访问
                declaredField.setAccessible(true);
                //拿取到传递过来的值
                Object o = declaredField.get(params[0]);
                prepareStatement.setObject(i + 1, o);
            }
            //执行sql
            ResultSet resultSet = prepareStatement.executeQuery();

            //返回结果的全路径
            String resultType = mappedStatement.getResultType();
            Class<?> resultTypeClass = getClassType(resultType);

            objectList = new ArrayList<>();
            Object o = null;
            //封装返回结果集
            while (resultSet.next()) {
                //获取返回结果集的原数据
                ResultSetMetaData metaData = resultSet.getMetaData();
                //获取返回结果类的具体实现
                o = resultTypeClass.getDeclaredConstructor().newInstance();
                for (int i = 1; i <= metaData.getColumnCount(); i++) {

                    //获取字段名
                    String columnName = metaData.getColumnName(i);
                    //字段的值
                    Object value = resultSet.getObject(columnName);
                    //利用内省，根据数据库表和实体的对应关系，完成封装
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultTypeClass);
                    Method writeMethod = propertyDescriptor.getWriteMethod();
                    //具体的值封装到对象中
                    writeMethod.invoke(o, value);
                }
                objectList.add(o);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return (List<E>) objectList;
    }

    /**
     * 根据某一个类的全路径获取对应的class对象
     *
     * @param parameterType
     * @return
     */
    private Class<?> getClassType(String parameterType) throws ClassNotFoundException {
        if (parameterType != null) {
            Class<?> aClass = Class.forName(parameterType);
            return aClass;
        }
        return null;
    }

    /**
     * 完成对sql语句中#{}的解析工作
     * --1：将#{}使用?代替
     * --2：解析出#{}中的值进行存储
     *
     * @param sql xml文件中编写sql语句
     * @return
     */
    private BoundSql getBoundSql(String sql) {
        //标记处理类：配置标记解析器来完成对占位符的解析处理工作
        ParameterMappingTokenHandler tokenHandler = new ParameterMappingTokenHandler();
        //解析出来的sql
        String parseSql = new GenericTokenParser("#{", "}", tokenHandler).parse(sql);
        //解析出来的参数名称
        List<ParameterMapping> parameterMappings = tokenHandler.getParameterMappings();
        return new BoundSql(parseSql, parameterMappings);
    }
}
