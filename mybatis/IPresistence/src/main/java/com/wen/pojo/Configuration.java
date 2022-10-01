package com.wen.pojo;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Wen
 * @date 2022年09月27日 10:57 PM
 * 解析出来的sqlMapConfig.xml对象
 */
public class Configuration {
    /**
     * 数据源信息
     */
    private DataSource dataSource;
    /**
     * 会解析出多个MappedStatement对象，封装到Map中传递
     * key:     statementId
     * value:   封装好的MappedStatement对象
     */
    private Map<String, MappedStatement> mappedStatementMap = new HashMap<>();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, MappedStatement> getMappedStatementMap() {
        return mappedStatementMap;
    }

    public void setMappedStatementMap(Map<String, MappedStatement> mappedStatementMap) {
        this.mappedStatementMap = mappedStatementMap;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "dataSource=" + dataSource +
                ", mappedStatementMap=" + mappedStatementMap +
                '}';
    }
}
