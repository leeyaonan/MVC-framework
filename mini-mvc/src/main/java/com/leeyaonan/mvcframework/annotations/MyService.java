package com.leeyaonan.mvcframework.annotations;

import java.lang.annotation.*;

/**
 * @Author leeyaonan
 * @Date 2020/4/25 14:09
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyService {

    String value() default "";
}
