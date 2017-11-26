package com.qunar.x.validate.base;

/**
 * @Author: zhenghan
 * @Description:
 * @Date: 2017/11/26
 */
public interface XValidator<T> {
    /**
     * @return xValidate
     */
    XValidate validateWithMsg();

    /**
     * @return 这次的验证是否成功
     */
    boolean validate();

    /**
     * 2个validator取或逻辑
     * @param xValidator
     * @return
     */
    default XValidator or(XValidator xValidator){
        if(xValidator == null){
            throw  new IllegalArgumentException("xValidator is null");
        }
        return GroupValidator.orGroup(this,xValidator);
    }

    /**
     * 2个validator取与逻辑
     * @param xValidator
     * @return
     */
    default XValidator and(XValidator xValidator){
        if(xValidator == null){
            throw  new IllegalArgumentException("xValidator is null");
        }
        return GroupValidator.andGroup(this,xValidator);
    }

}
