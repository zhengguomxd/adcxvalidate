package com.x.validate.base.support;

import com.x.validate.base.BaseValidator;
import com.x.validate.base.XValidate;
import com.x.validate.base.imple.SuccessValidator;

import java.util.Stack;

/**
 * @Author:zhenghan
 * @Descripotion:
 * @Date: 0:17
 */
public class ValidatorContextHolder {

    private Stack<XValidate> stack = new Stack<>();

    private BaseValidator successValidator = new SuccessValidator();

    public Stack<XValidate> getStack() {
        return stack;
    }

    public void setStack(Stack<XValidate> stack) {
        this.stack = stack;
    }

    public BaseValidator getSuccessValidator() {
        return successValidator;
    }

    public void setSuccessValidator(BaseValidator successValidator) {
        this.successValidator = successValidator;
    }

    private ValidatorContextHolder(Stack<XValidate> stack, BaseValidator successValidator) {
        this.stack = stack;
        this.successValidator = successValidator;
    }

    public static ValidatorContextHolder generateContextHolder(BaseValidator successValidator){
        return new ValidatorContextHolder(new Stack<>(),successValidator);
    }

    public boolean isStackEmpty() {
        return stack.isEmpty();
    }

    public XValidate stackPop() {
        return stack.pop();
    }

    public XValidate stackPush(XValidate xValidate) {
        return stack.push(xValidate);
    }

    public BaseValidator success() {
        return successValidator;
    }
}
