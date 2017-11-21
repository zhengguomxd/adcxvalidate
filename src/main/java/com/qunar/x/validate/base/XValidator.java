package com.qunar.x.validate.base;


public interface XValidator<T> {
    /**
     * @return xValidate
     */
    XValidate validateWithMsg();

    /**
     * @return 这次的验证是否成功
     */
    boolean validate();


    default XValidator or(XValidator xValidator){
        if(xValidator == null){
            throw  new IllegalArgumentException("xValidator is null");
        }
        return GroupValidator.orGroup(this,xValidator);
    }

    default XValidator and(XValidator xValidator){
        if(xValidator == null){
            throw  new IllegalArgumentException("xValidator is null");
        }
        return GroupValidator.andGroup(this,xValidator);
    }

}
