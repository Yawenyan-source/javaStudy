package com.wen.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Wen
 * @since 2022年10月15日 6:49 PM
 * 自定义拦截器
 */
public class MyInterceptor02 implements HandlerInterceptor {
    /**
     * 会在handle业务逻辑执行之前执行
     * 往往在这里完成权限校验工作
     * @param request
     * @param response
     * @param handler
     * @return 返回值代表是否放行，true代表放行，false代表终止
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("MyInterceptor02 preHandle...");
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    /**
     * 会在handler业务逻辑处理之后执行
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView 封装了视图和数据，可以在这里针对返回的数据和试图信息进行修改
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("MyInterceptor02 postHandle...");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 页面已经跳转渲染完毕之后执行
     *
     * @param request
     * @param response
     * @param handler
     * @param ex 可以在这里捕获一些异常
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("MyInterceptor02 afterCompletion...");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
