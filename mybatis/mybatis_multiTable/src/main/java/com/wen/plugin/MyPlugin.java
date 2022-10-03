package com.wen.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.sql.Connection;
import java.util.Properties;

/**
 * @author Wen
 * @date 2022年10月03日 3:41 PM
 */
@Intercepts(value = {
        @Signature(
                type = StatementHandler.class,
                method = "prepare",
                args = {Connection.class, Integer.class})
})
public class MyPlugin implements Interceptor {
    /**
     * 拦截方法：只要被拦截的目标对象的目标方法被执行时，都会执行此方法
     *
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("对方法进行增强");
        return invocation.proceed();//让原方法执行
    }

    /**
     * 主要为了把当前拦截器生成代理存到拦截器链中
     *
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        return Interceptor.super.plugin(target);
    }

    /**
     * 获取配置文件的参数
     *
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {
        System.out.println("获取到的参数：" + properties);
        Interceptor.super.setProperties(properties);
    }
}
