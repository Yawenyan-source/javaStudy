package com.wen.mvcframework.servlet;

import com.wen.mvcframework.annotations.WenAutowired;
import com.wen.mvcframework.annotations.WenController;
import com.wen.mvcframework.annotations.WenRequestMapping;
import com.wen.mvcframework.annotations.WenService;
import com.wen.mvcframework.pojo.Handler;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wen
 * @version 1.0
 * @project javaStudy
 * @description 自定义mvc框架-前端控制器
 * @since 2022/10/18 20:43:37
 */
public class WenDispatchServlet extends HttpServlet {

    private final Properties properties = new Properties();
    private final List<String> classNameList = new ArrayList<>();

    private final Map<String, Object> iocMap = new HashMap<>();
    //private final Map<String, Method> handlerMapping = new HashMap<>();

    private final List<Handler> handlers = new ArrayList<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        //    加载配置资源文件 springmvc.properties
        String contextConfigLocation = config.getInitParameter("contextConfigLocation");
        doLoadConfig(contextConfigLocation);
        //    扫描相关类，扫描注解
        doScan(properties.getProperty("scanPackage"));
        //    初始化bean对象（实现ioc容器，基于注解）
        doInstance();
        //    实现依赖注入
        doAutoWired();
        //    构造一个handlerMapping处理器映射器，将配置好的url和Method建立映射关系
        initHandlerMapping();
        System.out.println("wen MVC初始化完成");
    }

    /**
     * 构造一个handlerMapping映射器 将method和url建立管理
     */
    private void initHandlerMapping() {
        if (iocMap.isEmpty()) return;

        for (Map.Entry<String, Object> entry : iocMap.entrySet()) {
            Class<?> aClass = entry.getValue().getClass();
            //没有WenController直接循环下一个
            if (!aClass.isAnnotationPresent(WenController.class)) {
                continue;
            }
            String baseUrl = "";
            if (aClass.isAnnotationPresent(WenRequestMapping.class)) {
                WenRequestMapping annotation = aClass.getAnnotation(WenRequestMapping.class);
                baseUrl = annotation.value(); // /demo
            }
            //获取类下面的方法
            for (Method method : aClass.getMethods()) {
                //方法没有WenRequestMapping标识，就不处理
                if (!method.isAnnotationPresent(WenRequestMapping.class)) {
                    continue;
                }
                WenRequestMapping annotation = method.getAnnotation(WenRequestMapping.class);
                String methodUrl = annotation.value(); //方法上面的WenRequestMapping的值
                String url = baseUrl + methodUrl; //计算出来的url
                //把method所有信息及url封装成handler
                Handler handler = new Handler(entry.getValue(), method, Pattern.compile(url));
                //计算参数的位置信息
                Map<String, Integer> paramIndexMapping = handler.getParamIndexMapping();

                for (int i = 0; i < method.getParameters().length; i++) {
                    Parameter parameter = method.getParameters()[i];
                    if (parameter.getType() == HttpServletRequest.class || parameter.getType() == HttpServletResponse.class) {
                        paramIndexMapping.put(parameter.getType().getSimpleName(), i);
                    } else {
                        paramIndexMapping.put(parameter.getName(), i);
                    }
                }
                //缓存起来
                handlers.add(handler);

                //建立method 和 url之间的映射关系 （map缓存起来）
                //handlerMapping.put(url, method);
            }

        }
    }

    /**
     * 实现依赖注入
     */
    private void doAutoWired() {
        //容器为空，直接返回
        if (iocMap.isEmpty()) return;
        //    容器不为空，进行依赖注入
        for (Map.Entry<String, Object> entry : iocMap.entrySet()) {
            //    获取bean对象中的字段信息;遍历处理
            for (Field declaredField : entry.getValue().getClass().getDeclaredFields()) {
                if (!declaredField.isAnnotationPresent(WenAutowired.class)) {
                    //没有该注解
                    continue;
                }
                //    存在@WenAutowired注解
                WenAutowired wenAutowired = declaredField.getAnnotation(WenAutowired.class);
                String beanName = wenAutowired.value(); //需要注入的bean的id
                if ("".equals(beanName.trim())) {
                    //    没有配置具体的bean ID,需要根据当前字段的类型注入（接口注入）
                    beanName = declaredField.getType().getName();
                }
                //    开始赋值; 强制访问
                declaredField.setAccessible(true);
                try {
                    /*
                     *  private IDemoService demoService;
                     * entry.getValue(),字段所在的对象
                     * iocMap.get(beanName),赋值的值
                     * */
                    declaredField.set(entry.getValue(), iocMap.get(beanName));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

    /**
     * ioc容器
     */
    private void doInstance() {
        if (classNameList.size() == 0) return;
        for (String clashName : classNameList) {
            try {
                Class<?> aClass = Class.forName(clashName);
                //    区分controller 和 service
                if (aClass.isAnnotationPresent(WenController.class)) {
                    //    controller的Id不做处理，不取value，就拿类的首字母小写作为id,保存到ioc容器
                    String simpleName = aClass.getSimpleName();//类名
                    String lowerFirstName = lowerFirst(simpleName); //首字母小写的类名
                    Object o = aClass.getDeclaredConstructor().newInstance(); //实例化对象
                    iocMap.put(lowerFirstName, o); //保存到ioc容器中
                } else if (aClass.isAnnotationPresent(WenService.class)) {
                    WenService wenServiceAnno = aClass.getAnnotation(WenService.class);
                    //获取注解value值
                    String beanName = wenServiceAnno.value();
                    if ("".equals(beanName.trim())) {
                        //指定了id,就以指定的id为准
                        iocMap.put(beanName, aClass.getDeclaredConstructor().newInstance());
                    } else {
                        //如果没有指定，就以类名首字母小写为准
                        beanName = aClass.getSimpleName();
                        iocMap.put(beanName, aClass.getDeclaredConstructor().newInstance());
                    }
                    //    实例化Service接口,便于后期接口类型注入
                    for (Class<?> anInterface : aClass.getInterfaces()) {
                        //以接口的全限定类名作为Id放入
                        iocMap.put(anInterface.getName(), aClass.getDeclaredConstructor().newInstance());
                    }


                } else {
                    continue;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 首字母小写方法
     *
     * @param str
     * @return
     */
    private String lowerFirst(String str) {
        char[] chars = str.toCharArray();
        if ('A' <= chars[0] && chars[0] <= 'Z') {
            chars[0] += 32;
        }
        return String.valueOf(chars);
    }

    /**
     * 扫描类，扫描注解
     *
     * @param scanPackage 扫描路径
     */
    public void doScan(String scanPackage) {
        String scanPackagePath = Thread.currentThread().getContextClassLoader().getResource("").getPath() + scanPackage.replaceAll("\\.", "/");
        File pack = new File(scanPackagePath);
        File[] files = pack.listFiles();
        for (File file : files) {
            System.out.println(file);
            if (file.isDirectory()) {
                //    子package
                doScan(scanPackage + "." + file.getName());
            } else if (file.getName().endsWith(".class")) {
                //全限定类名
                String className = scanPackage + "." + file.getName().replaceAll(".class", "");
                //将获得的全限定类名缓存起来
                classNameList.add(className);
            }
        }
        System.out.println(classNameList);
    }


    /**
     * 加载配置文件
     *
     * @param contextConfigLocation 配置文件路径
     */
    private void doLoadConfig(String contextConfigLocation) {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理请求
        //获取uri
        //String requestURI = req.getRequestURI();
        //Method method = handlerMapping.get(requestURI);
        //    反射调用

        Handler handler = getHandler(req);
        if (handler == null) {
            resp.getWriter().write("404 NOT FOUND");
            return;
        }
        //参数绑定
        //获取所有参数类型的数组，这个数组的长度就是最后要传入的args的数组长度
        Class<?>[] parameterTypes = handler.getMethod().getParameterTypes();
        //根据上述数组长度创建一个新的数组（参数数组，是要传入反射调用的）
        Object[] paramsValues = new Object[parameterTypes.length];
        //获取前端传过来的参数
        Map<String, String[]> parameterMap = req.getParameterMap();
        for (Map.Entry<String, String[]> param : parameterMap.entrySet()) {
            //如果是数组，就变为 ,分割
            String value = StringUtils.join(param.getValue(), ",");
            //判断传入的参数和方法中的参数实验匹配
            if (!handler.getParamIndexMapping().containsKey(param.getKey())) {
                //不匹配，直接返回
                continue;
            }
            //存在匹配，找到索引位置，把参数放到paramsValues中
            Integer index = handler.getParamIndexMapping().get(param.getKey());
            //把前台传入过来的值，填充到对应的位置
            paramsValues[index] = value;
            //    处理request和response
            Integer requestIndex = handler.getParamIndexMapping().get(HttpServletRequest.class.getSimpleName());
            paramsValues[requestIndex] = req;
            Integer responseIndex = handler.getParamIndexMapping().get(HttpServletResponse.class.getSimpleName());
            paramsValues[responseIndex] = resp;
        }
        //    最终调用handler的method
        try {
            handler.getMethod().invoke(handler.getController(), paramsValues);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Handler getHandler(HttpServletRequest req) {
        if (handlers.isEmpty()) {
            return null;
        }
        String uri = req.getRequestURI();
        for (Handler handler : handlers) {
            Matcher matcher = handler.getPattern().matcher(uri);
            if (matcher.matches()) {
                return handler;
            }
        }
        return null;
    }
}
