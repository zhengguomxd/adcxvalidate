package com.x.validate.base.imple;

import com.x.validate.base.BaseValidator;
import com.x.validate.base.XValidate;
import com.x.validate.base.support.ValidatorContextHolder;

/**
 * @Author:zhenghan
 * @Descripotion:
 * @Date: 2017/11/26
 */
public class FailValidator extends BaseValidator {
    private XValidate xValidate;

    public FailValidator(XValidate xValidate,ValidatorContextHolder validatorContextHolder) {
        this.xValidate = xValidate;
        this.validatorContextHolder = validatorContextHolder;
    }

    protected BaseValidator trans(XValidate xValidate){
        if(xValidate != null){
            validatorContextHolder.stackPush(xValidate);
        }
        return this;
    }

    public XValidate validateWithMsg() {
        return xValidate;
    }

    public boolean validate() {
        return false;
    }
}
