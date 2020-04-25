package com.leeyaonan.mvcframework.annotations;

import java.lang.annotation.*;

/**
 * @Author leeyaonan
 * @Date 2020/4/25 14:08
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyRequestMapping {

    String value() default "";
}
