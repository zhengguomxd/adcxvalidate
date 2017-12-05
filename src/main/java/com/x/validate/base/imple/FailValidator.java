package com.x.validate.base.imple;

import com.x.validate.base.BaseValidator;
import com.x.validate.base.XValidate;

/**
 * @Author:zhenghan
 * @Descripotion:
 * @Date: 2017/11/26
 */
public class FailValidator extends BaseValidator {
    private XValidate xValidate;

    public FailValidator(XValidate xValidate) {
        this.xValidate = xValidate;
    }

    protected BaseValidator trans(XValidate xValidate){
        return this;
    }

    public XValidate validateWithMsg() {
        return xValidate;
    }

    public boolean validate() {
        return false;
    }
}
