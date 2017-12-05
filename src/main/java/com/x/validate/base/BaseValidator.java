package com.x.validate.base;

import com.x.validate.base.imple.FailValidator;
import com.x.validate.base.imple.SuccessValidator;
import com.x.validate.base.support.ValidatorContextHolder;

import java.util.Comparator;
import java.util.Date;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * @Author: zhenghan
 * @Description:
 * @Date: 2017/11/26
 */
public abstract class BaseValidator implements XValidator{

    protected ValidatorContextHolder validatorContextHolder;

    public static BaseValidator generateValidator() {
        BaseValidator baseValidator = new SuccessValidator();
        baseValidator.validatorContextHolder = ValidatorContextHolder.generateContextHolder(baseValidator);
        return baseValidator;
    }


    public BaseValidator toFail(String err){
        return new FailValidator(XValidate.of(false,err),validatorContextHolder);
    }

    /**
     *
     * @param str
     * @param length
     * @param err
     * @return 是否str 的长度大于Length
     */
    public BaseValidator moreThan(String str,int length,String err){
        if(str == null || length < 0){
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
     * @return 判断2个对象是否相等 必须实现equals方法哦。
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
        int compareStatus = ((Comparable) t).compareTo(y);
        return trans(XValidate.of(( compareStatus == 0 ? 0 : compareStatus > 0 ? 1 : -1) == state,err));
    }

    public <T> BaseValidator toCompare(T t, T y, Comparator<T> comparator,int state,String err){
        int compareStatus = comparator.compare(t,y);
        return trans(XValidate.of(( compareStatus == 0 ? 0 : compareStatus > 0 ? 1 : -1) == state ,err));
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
     * 不为空白
     * @param t
     * @param err
     * @return
     */
    public BaseValidator nonBlank(String t,String err){
        return trans(XValidate.of(t == null || t.trim().length() == 0,err));
    }


    /**
     * 将上一次执行的记录返回进行修改后再次入栈，但是会影响全部的结果。
     * @return 返回反转逻辑后的BaseValidator
     *  example :
     *  baseValidator.nonNull(null,"not null").reverse();
     *  is equivalent to
     *  baseValidator.isNull(null,"not null")
     */
    public <T> BaseValidator reverseAll(){
        if(validatorContextHolder.isStackEmpty()){
            throw new RuntimeException("调用该方法前请使用其他方法");
        }
        XValidate pop = validatorContextHolder.stackPop();
        pop.setSuccess(!pop.isSuccess());
        return transReverse(pop);
    }

    /**
     * 如果是上一次改变的那么只改变上一次的栈结果,
     * 也就是如果在前一次之前就已经验证失败了那么结果改变是不起作用的
     * 但是如果在前一次之前是验证通过了那么结果改变是起作用的
     * @param <T>
     * @return
     */
    public <T> BaseValidator reverseLast(){
        if(validatorContextHolder.isStackEmpty()){
            throw new RuntimeException("调用该方法前请使用其他方法");
        }
        XValidate pop = validatorContextHolder.stackPop();
        XValidate secPop = validatorContextHolder.stackPeek();
        if(secPop == null || secPop.isSuccess()){
            pop.setSuccess(!pop.isSuccess());
            return transReverse(pop);
        }
        return trans(pop);
    }


    public <T> BaseValidator predicate(Predicate<T> predicate, T t ,String error){
        return trans(XValidate.of(predicate.test(t),error));
    }

    /**
     * @param xValidate
     * @return
     */
    protected BaseValidator trans(XValidate xValidate){
        return xValidate != null && validatorContextHolder.stackPush(xValidate) != null && xValidate.isSuccess() ? validatorContextHolder.success() : new FailValidator(xValidate,validatorContextHolder);
    }

    protected BaseValidator transReverse(XValidate xValidate){
        return xValidate != null && validatorContextHolder.stackPush(xValidate) != null && xValidate.isSuccess() ? validatorContextHolder.success() : new FailValidator(xValidate,validatorContextHolder);
    }
}
