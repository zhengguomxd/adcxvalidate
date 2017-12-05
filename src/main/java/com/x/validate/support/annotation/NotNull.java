package com.x.validate.support.annotation;

import java.lang.annotation.*;

/**
 * @Author:zhenghan
 * @Descripotion:
 * @Date: 2:24
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
@Documented
public @interface NotNull {
    String error() default "is null";
}
