package com.x.validate;

import com.x.validate.base.BaseValidator;
import org.junit.Assert;
import org.junit.Test;

/**
 * @Author:zhenghan
 * @Descripotion:
 * @Date: 0:48
 */
public class BaseValidatorTest {
    @Test
    public void validateTest(){
        boolean validate = XValidators.commonValidator()
                .toCompare(11, 10, 1, "")
                .or(XValidators.commonValidator()
                        .toCompare(10, 11, 1, ""))
                .validate();
        System.out.println(validate);
    }
}
