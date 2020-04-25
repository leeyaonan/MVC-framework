package com.leeyaonan.demo.controller;

import com.leeyaonan.demo.service.IDemoService;
import com.leeyaonan.mvcframework.annotations.MyAutowired;
import com.leeyaonan.mvcframework.annotations.MyController;
import com.leeyaonan.mvcframework.annotations.MyRequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author leeyaonan
 * @Date 2020/4/25 21:35
 */
@MyController
@MyRequestMapping("/demo")
public class DemoController {

    @MyAutowired
    private IDemoService demoService;

    public String query(HttpServletRequest request, HttpServletResponse response, String name) {
        return demoService.get(name);
    }
}
