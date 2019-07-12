package com.yk.mvpframe.base;

import java.io.Serializable;

/**
 * @FileName BaseModel
 * @Author alan
 * @Date 2019/7/11 9:38
 * @Describe TODO
 * @Mark
 **/
public class BaseModel<T> implements Serializable {
    private int errCode;
    private String errMsg;
    private T result;

    public BaseModel(){}
    public BaseModel(int errCode,String errMsg){
        this.errCode=errCode;
        this.errMsg=errMsg;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
