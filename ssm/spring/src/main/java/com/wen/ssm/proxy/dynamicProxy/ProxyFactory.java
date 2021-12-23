package com.wen.ssm.proxy.dynamicProxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.lang.reflect.Proxy;

/**
 * @author Wen
 * @date 2021年12月22日 10:05 PM
 * 代理对象工厂：生成代理对象
 */
public class ProxyFactory {

    private ProxyFactory() {
    }

    private static ProxyFactory proxyFactory = new ProxyFactory();

    public static ProxyFactory getInstance() {
        return proxyFactory;
    }

    /**
     * 使用jdk动态代理生成代理对象
     *
     * @param obj 委托对象
     * @return 代理对象
     */
    public Object getJdkProxy(Object obj) {
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    Object result = null;
                    //写增强逻辑
                    System.out.println("111");
                    //调用原有业务逻辑
                    result = method.invoke(obj, args);
                    System.out.println("222");
                    return result;
                });
    }

    /**
     * 使用cglib动态代理生成代理对象
     *
     * @param obj 委托对象
     * @return 代理对象
     */
    public Object getCglibProxy(Object obj) {
        return Enhancer.create(obj.getClass(),
                (MethodInterceptor) (o, method, objects, methodProxy) -> {
                    Object result = null;
                    System.out.println("111");
                    result = method.invoke(obj, objects);
                    System.out.println("222");
                    return result;
                });
    }
}