package com.qunar.x.validate.base;

/**
 * @Author:zhenghan
 * @Descripotion:
 * @Date: ${Time} ${Date}
 */
public abstract class BaseValidator implements XValidator{

    private final static BaseValidator SUCCESS = new SuccessValidator();

    private static class FailValidator extends BaseValidator{
        private XValidate xValidate;

        public FailValidator(String error) {
            this.xValidate = XValidate.of(false,error);
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
    private static class SuccessValidator extends BaseValidator{
        private XValidate xValidate = XValidate.of(true,null);

        public XValidate validateWithMsg() {
            return null;
        }

        public boolean validate() {
            return true;
        }
    }

    public static BaseValidator generateValidate(){
        return SUCCESS;
    }


    public BaseValidator toFail(){
        return null;
    }

    public BaseValidator moreThan(String str,int length,String err){
        if(str == null || str.length() == 0 || length < 0){
             throw new IllegalArgumentException("illegal arguments");
        }
        return trans(XValidate.of(str.length() > length,err));
    }

    protected BaseValidator trans(XValidate xValidate){
        return xValidate.isSuccess() ? SUCCESS : new FailValidator(xValidate.getErrorMsg());
    }


}
