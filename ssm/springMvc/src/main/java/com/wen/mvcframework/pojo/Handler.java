package com.wen.mvcframework.pojo;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author wen
 * @version 1.0
 * @project javaStudy
 * @description 封装handler相关的信息
 * @since 2022/10/19 22:14:35
 */
public class Handler {
    private Object controller;                      //method反射调用时的对象
    private Method method;                          //方法
    private Pattern pattern;                        //url springMVC中的url是支持正则的
    private Map<String, Integer> paramIndexMapping; //参数顺序，是为了在放射调用的时候参数绑定 key 是参数名，value代表的是第几个参数

    public Handler(Object controller, Method method, Pattern pattern) {
        this.controller = controller;
        this.method = method;
        this.pattern = pattern;
        this.paramIndexMapping = new HashMap<>();
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public Map<String, Integer> getParamIndexMapping() {
        return paramIndexMapping;
    }

    public void setParamIndexMapping(Map<String, Integer> paramIndexMapping) {
        this.paramIndexMapping = paramIndexMapping;
    }
}
