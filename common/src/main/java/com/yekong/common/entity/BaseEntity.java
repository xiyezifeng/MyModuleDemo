package com.yekong.common.entity;

import android.util.Log;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * Created by xigua on 2017/9/13.
 */

public class BaseEntity {
    private String data;
    private int errorCode;
    private String errorInfo;
    private String sessionId;

    public String getData() {
        return data;
    }

    public <T> T getData( Class<T> clazz) {
        T t = JSON.parseObject(getData(), clazz);
        return t;
    }

    public <T> List<T> getListData(Class<T> clazz) {
        List<T> t = JSON.parseArray(getData(), clazz);
        return t;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        String data = JSON.toJSONString(this);
        Log.e("BaseBean", data);
        return data;
    }
}
