package com.leeyaonan.demo.service.impl;

import com.leeyaonan.demo.service.IDemoService;
import com.leeyaonan.mvcframework.annotations.MyService;

/**
 * @Author leeyaonan
 * @Date 2020/4/25 21:38
 */
@MyService("demoService")
public class DemoServiceImpl implements IDemoService {


    @Override
    public String get(String name) {
        System.out.println("service impl 中name参数：" + name);
        return name;
    }
}
