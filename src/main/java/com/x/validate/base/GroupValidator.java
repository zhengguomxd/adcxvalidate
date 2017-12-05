package com.x.validate.base;

/**
 * @Author:zhenghan
 * @Descripotion:
 * @Date:
 */
public abstract class GroupValidator implements XValidator {
    enum GroupState{
        OR,AND
    }
    private XValidator bX,aX;
    private GroupState state;
    private GroupValidator(XValidator aX,XValidator bX,GroupState state){
        this.aX = aX;
        this.bX = bX;
        this.state = state;
    }

    public static XValidator orGroup(XValidator aX, XValidator bX){
        if(aX == null || bX == null || aX == bX){
            throw new IllegalArgumentException("aX,bX param is illegal");
        }
        return new OrGroupValidator(aX,bX,GroupState.OR);
    }

    public static XValidator andGroup(XValidator aX,XValidator bX) {
        if (aX == null || bX == null || aX == bX) {
            throw new IllegalArgumentException("aX,bX param is illegal");
        }
        return new AndGroupValidator(aX, bX, GroupState.AND);
    }


    private static class OrGroupValidator extends GroupValidator {
        public OrGroupValidator(XValidator aX, XValidator bX, GroupState or) {
            super(aX,bX,or);
        }

        public XValidate validateWithMsg() {
            XValidate axValidate = getaX().validateWithMsg();
           if(axValidate.isSuccess()){
               return axValidate;
           }
            XValidate bxValidate = getbX().validateWithMsg();
           if(bxValidate.isSuccess()){
               return bxValidate;
           }
           return axValidate;
        }

        public boolean validate() {
            return  getaX().validateWithMsg().isSuccess() || getbX().validateWithMsg().isSuccess();
        }
    }

    private static class AndGroupValidator extends GroupValidator {
        public AndGroupValidator(XValidator aX, XValidator bX, GroupState and) {
            super(aX,bX,and);
        }

        public XValidate validateWithMsg() {
            XValidate axValidate = getaX().validateWithMsg();
            if(!axValidate.isSuccess()){
                return axValidate;
            }
            XValidate bxValidate = getbX().validateWithMsg();
            if(!bxValidate.isSuccess()){
                return bxValidate;
            }
            return axValidate;
        }

        public boolean validate() {
            return getaX().validateWithMsg().isSuccess() && getbX().validateWithMsg().isSuccess();
        }
    }


    public XValidator getbX() {
        return bX;
    }

    public void setbX(XValidator bX) {
        this.bX = bX;
    }

    public XValidator getaX() {
        return aX;
    }

    public void setaX(XValidator aX) {
        this.aX = aX;
    }

    public GroupState getState() {
        return state;
    }

    public void setState(GroupState state) {
        this.state = state;
    }

}
