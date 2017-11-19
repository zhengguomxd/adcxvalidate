package com.qunar.x.validate.base;


public interface XValidator {
    /**
     * @return xValidate
     */
    XValidate validateWithMsg();

    /**
     * @return 这次的验证是否成功
     */
    boolean validate();


}
