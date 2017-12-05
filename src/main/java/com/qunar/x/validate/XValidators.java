package com.qunar.x.validate;

import com.qunar.x.validate.base.BaseValidator;
import com.qunar.x.validate.base.XValidator;

import java.util.HashMap;

public class XValidators {
    public static XValidator commonValidator(){
        return BaseValidator.generateValidator();
    }
}
