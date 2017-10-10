package com.yekong.im.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by xigua on 2017/9/20.
 */
@Entity
public class SystemNotifyEntity {
    @Id(autoincrement = true)
    public Long id;

    private  String des;
    private  String content;//消息属性，可随意定义
    private  String messageId;
    private  String form;
    private  String to;
    private  String state;
    private  String inviter;
    private  String messageKey;
    private  String startTime;
    private  String endTime;
    @Generated(hash = 210409244)
    public SystemNotifyEntity(Long id, String des, String content, String messageId,
            String form, String to, String state, String inviter, String messageKey,
            String startTime, String endTime) {
        this.id = id;
        this.des = des;
        this.content = content;
        this.messageId = messageId;
        this.form = form;
        this.to = to;
        this.state = state;
        this.inviter = inviter;
        this.messageKey = messageKey;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    @Generated(hash = 1835862813)
    public SystemNotifyEntity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getMessageId() {
        return this.messageId;
    }
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
    public String getForm() {
        return this.form;
    }
    public void setForm(String form) {
        this.form = form;
    }
    public String getTo() {
        return this.to;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public String getState() {
        return this.state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getInviter() {
        return this.inviter;
    }
    public void setInviter(String inviter) {
        this.inviter = inviter;
    }
    public String getMessageKey() {
        return this.messageKey;
    }
    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }
    public String getStartTime() {
        return this.startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getEndTime() {
        return this.endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public String getDes() {
        return this.des;
    }
    public void setDes(String des) {
        this.des = des;
    }
}
