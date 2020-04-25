package com.leeyaonan.mvcframework.annotations;

import java.lang.annotation.*;

/**
 * @Author leeyaonan
 * @Date 2020/4/25 13:59
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyController {

    String value() default "";
}
