package com.leeyaonan.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpResponse;

/**
 * @Author leeyaonan
 * @Date 2020/4/25 12:59
 */
@ControllerAdvice
public class GlobalExceptionResolver {

    public ModelAndView handleException(ArithmeticException exception, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("msg", exception.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;

    }
}
