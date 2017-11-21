package com.qunar.x.validate.base;

public class XValidate{
    private String errorMsg;
    private Throwable throwable;
    private boolean success;

    private XValidate(String errorMsg, Throwable throwable, boolean success) {
        this.errorMsg = errorMsg;
        this.throwable = throwable;
        this.success = success;
    }

    public static XValidate of(boolean success, String error){
        return new XValidate(error,null,success);
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
