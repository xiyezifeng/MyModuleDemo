package com.yekong.im.entity;

/**
 * Created by xigua on 2017/9/18.
 */

public class MotioncEntity {
    private int resId;//需要显示的资源ID
    private String des;//外部描述

    public MotioncEntity() {
    }

    public MotioncEntity(int resId, String des) {
        this.resId = resId;
        this.des = des;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
