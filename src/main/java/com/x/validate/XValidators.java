package com.x.validate;


import com.x.validate.base.BaseValidator;

public class XValidators {
    public static BaseValidator commonValidator(){
        return BaseValidator.generateValidator();
    }

}
