package com.x.validate.support.annotation;

import java.lang.annotation.*;

/**
 * @Author:zhenghan
 * @Descripotion:
 * @Date: 2:28
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
@Documented
public @interface Length {
    int max() default Integer.MAX_VALUE;
    int min() default Integer.MIN_VALUE;
    String error() default "${value} bewteen ${min},${max}";
}
