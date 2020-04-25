package com.leeyaonan.mvcframework.annotations;

import java.lang.annotation.*;

/**
 * @Author leeyaonan
 * @Date 2020/4/25 14:10
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAutowired {

    String value() default "";
}
