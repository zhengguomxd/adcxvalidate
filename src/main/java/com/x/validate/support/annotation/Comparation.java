package com.x.validate.support.annotation;

import java.lang.annotation.*;

/**
 * @Author:zhenghan
 * @Descripotion:
 * @Date: 2:44
 * 所有被该注解定义的注解可以进行属性之间的比较也就是可以使用OtherField
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE })
@Documented
public @interface Comparation {
}
