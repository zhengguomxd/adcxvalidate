package com.x.validate.support.annotation;

import java.lang.annotation.*;

/**
 * @Author:zhenghan
 * @Descripotion:
 * @Date: 2:38
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
@Documented
@Comparation
public @interface TimeComparation{
    String after() default "";
    String before() default "";
    String pattern() default "yyyy-MM-dd hh:mm:ss";
}
