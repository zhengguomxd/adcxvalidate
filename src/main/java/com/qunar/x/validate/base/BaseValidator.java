package com.qunar.x.validate.base;

import java.util.Date;
import java.util.Objects;
import java.util.Stack;
import java.util.function.Predicate;

/**
 * @Author:zhenghan
 * @Descripotion:
 * @Date:
 */
public abstract class BaseValidator implements XValidator{

    private Stack<XValidate> stack =  new Stack<>();

    private final static BaseValidator SUCCESS = new SuccessValidator();

    private static class FailValidator extends BaseValidator{
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
    private static class SuccessValidator extends BaseValidator{
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

    public static BaseValidator generateValidate(){
        return new SuccessValidator();
    }


    public BaseValidator toFail(String err){
        return new FailValidator(XValidate.of(false,err));
    }

    /**
     *
     * @param str
     * @param length
     * @param err
     * @return 是否str 的长度大于Length
     */
    public BaseValidator moreThan(String str,int length,String err){
        if(str == null || str.length() == 0 || length < 0){
            throw new IllegalArgumentException("illegal arguments");
        }
        return trans(XValidate.of(str.length() > length,err));
    }

    /**
     *
     * @param t
     * @param y
     * @param err
     * @return 是否 t 的日期大于 y
     */
    public BaseValidator greaterThan(Date t , Date y, String err){
        if(t == null || y == null ){
            throw new IllegalArgumentException("illegal arguments");
        }
        return trans(XValidate.of(t.after(y),err));
    }

    /**
     *
     * @param y
     * @param t
     * @param err
     * @param <T>
     * @return 判断2个对象是否相等
     */
    public <T> BaseValidator toEquals(T y,T t,String err){
       return trans((XValidate.of(Objects.equals(y,t),err)));
    }

    /**
     *
     * @param t
     * @param y
     * @param state 参数指定大于等于小于 -1,0,1 时返回true -1 小于 0 等于 1 大于
     * @param err
     * @return
     */
    public <T> BaseValidator toCompare(T t,T y,int state,String err){
        if(!(t instanceof Comparable) || !(y instanceof  Comparable)){
            throw new IllegalArgumentException("t,y must be implements Comparable interface");
        }
        return trans(XValidate.of(((Comparable) t).compareTo(y) == state,err));
    }

    /**
     * 不为空
     * @param t
     * @param err
     * @return
     */
    public <T> BaseValidator nonNull(T t,String err){
        return trans(XValidate.of(Objects.nonNull(t),err));
    }

    /**
     * 将上一次执行的记录返回进行修改后再次入栈
     * @return 返回反转逻辑后的BaseValidator
     *  example :
     *  baseValidator.nonNull(null,"not null").reverse();
     *  is equivalent to
     *  baseValidator.isNull(null,"not null")
     */
    public <T> BaseValidator reverse(){
        if(stack.isEmpty()){
            throw new RuntimeException("last record not found");
        }
        XValidate pop = stack.pop();
        pop.setSuccess(!pop.isSuccess());
        return trans(pop);
    }


    public <T> BaseValidator predicate(Predicate<T> predicate, T t ,String error){
        return trans(XValidate.of(predicate.test(t),error));
    }

    protected BaseValidator trans(XValidate xValidate){
        return xValidate != null && stack.push(xValidate) != null && xValidate.isSuccess() ? SUCCESS : new FailValidator(xValidate);
    }


}
