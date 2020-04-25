package com.leeyaonan.mvcframework.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author leeyaonan
 * @Date 2020/4/25 13:52
 */
public class MyDispatcherServlet extends HttpServlet {


    @Override
    public void init(ServletConfig config) throws ServletException {
        // 1. 加载配置文件 springmvc.properties
        String contextConfigLocation = config.getInitParameter("contextConfigLocation");
        doLoadConfig(contextConfigLocation);


        // 2. 扫描相关的类
        doScan("");


        // 3.1 初始化bean对象（实现ioc容器，基于注解）
        doInstance();


        // 4. 实现依赖注入
        doAutowired();


        // 5. 构造一个HandlerMapping处理器映射器，将配置好的url和method建立映射关系
        initHandlerMapping();


        // 6. 等待请求进入，处理请求
    }

    /**
     * 构造一个HandlerMapping处理器映射器，将配置好的url和method建立映射关系
     */
    private void initHandlerMapping() {

    }

    /**
     * 实现依赖注入
     */
    private void doAutowired() {

    }

    /**
     * 初始化bean对象
     */
    private void doInstance() {

    }

    /**
     * 扫描类
     * @param s
     */
    private void doScan(String s) {

    }

    /**
     * 加载配置文件
     * @param contextConfigLocation
     */
    private void doLoadConfig(String contextConfigLocation) {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
