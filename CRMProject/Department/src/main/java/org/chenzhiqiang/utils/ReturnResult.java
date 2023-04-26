package org.chenzhiqiang.utils;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
public class ReturnResult {
    private Boolean isSuccess = true;
    private String msg = "执行成功";
    private Object resultObj;

    @Override
    public String toString() {
        return "ReturnResult{" +
                "isSuccess=" + isSuccess +
                ", msg='" + msg + '\'' +
                ", resultObj=" + resultObj +
                '}';
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResultObj() {
        return resultObj;
    }

    public void setResultObj(Object resultObj) {
        this.resultObj = resultObj;
    }

    public ReturnResult(Boolean isSuccess, String msg, Object resultObj) {
        this.isSuccess = isSuccess;
        this.msg = msg;
        this.resultObj = resultObj;
    }

    public ReturnResult() {
    }
}
