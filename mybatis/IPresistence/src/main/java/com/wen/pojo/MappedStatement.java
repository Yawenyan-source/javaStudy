package com.wen.pojo;

/**
 * @author Wen
 * @date 2022年09月27日 10:48 PM
 * 对mapper.xml文件的解析
 */
public class MappedStatement {
    //id标识
    private String id;
    //返回值类型
    private String resultType;
    //参数类型
    private String parameterType;
    //sql语句
    private String sql;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    @Override
    public String toString() {
        return "MappedStatement{" +
                "id='" + id + '\'' +
                ", resultType='" + resultType + '\'' +
                ", parameterType='" + parameterType + '\'' +
                ", sql='" + sql + '\'' +
                '}';
    }
}
