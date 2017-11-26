package com.qunar.x.validate.base.imple;

import com.qunar.x.validate.base.BaseValidator;
import com.qunar.x.validate.base.XValidate;

/**
 * @Author:zhenghan
 * @Descripotion:
 * @Date: 2017/11/26
 */
public class SuccessValidator extends BaseValidator {
    private XValidate xValidate = XValidate.of(true,null);

    public SuccessValidator() {
    }

    public XValidate validateWithMsg() {
        return null;
    }

    public boolean validate() {
        return true;
    }
}
