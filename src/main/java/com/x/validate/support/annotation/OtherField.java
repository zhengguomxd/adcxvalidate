package com.x.validate.support.annotation;

import java.lang.annotation.*;

/**
 * @Author:zhenghan
 * @Descripotion:
 * 将该其他属性进行比较,value指定该属性的名称,该注解必须联合其他进行比较的注解进行联合使用
 * @Date: 2:42
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
@Documented
public @interface OtherField {
    public String value();
}
