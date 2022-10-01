package com.wen.sqlSession;

import com.wen.config.XMLConfigBuilder;
import com.wen.pojo.Configuration;
import org.dom4j.DocumentException;

import java.io.InputStream;

/**
 * @author Wen
 * @date 2022年09月28日 10:11 PM
 * 构建默认的SqlSession工厂
 */
public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(InputStream in) throws DocumentException {
        //    使用dom4j解析配置文件，将解析出来的内容封装到Configuration中
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parseConfig(in);

        //    创建sqlSessionFactory对象 工厂类：生产sqlSession会话对象
        DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(configuration);

        return defaultSqlSessionFactory;
    }
}
