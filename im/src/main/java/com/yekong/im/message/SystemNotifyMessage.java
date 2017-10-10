package com.yekong.im.message;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import io.rong.common.ParcelUtils;
import io.rong.common.RLog;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;

/**
 * 自定义系统消息,处理用户群组之间的请求
 * Created by xigua on 2017/9/20.
 */
@SuppressLint("ParcelCreator")
@MessageTag(value = "app:sysNotify", flag = MessageTag.ISCOUNTED)
public class SystemNotifyMessage extends MessageContent {
    /*
     {"messageId":"1","content":"您有一条新未读通知","form":"00004","to":"00005","state":"0","inviter":"","messageKey":"999","startTime":"10","endTime":"11"}
     */
    private  String des;//列表上显示的文字
    private  String content;//item上显示的文字描述
    private  String messageId;
    private  String form;
    private  String to;
    private  String state;
    private  String inviter;
    private  String messageKey;
    private  String startTime;
    private  String endTime;

    public static SystemNotifyMessage obtain(String des, String content,String messageId, String form,String to,String state,String inviter,String messageKey,String startTime,String endTime) {
        SystemNotifyMessage message = new SystemNotifyMessage();
        message.setDes(des);
        message.setContent(content);
        message.setMessageId(messageId);
        message.setForm(form);
        message.setTo(to);
        message.setState(state);
        message.setInviter(inviter);
        message.setMessageKey(messageKey);
        message.setStartTime(startTime);
        message.setEndTime(endTime);
        return message;
    }

    public SystemNotifyMessage() {
    }

    public SystemNotifyMessage(byte[] data) {
        super(data);
        String jsonStr = null;

        try {
            jsonStr = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e1) {

        }

        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            if (jsonObj.has("des"))
                des = jsonObj.optString("des");
            if (jsonObj.has("content"))
                content = jsonObj.optString("content");
            if (jsonObj.has("messageId"))
                messageId = jsonObj.optString("messageId");
            if (jsonObj.has("form"))
                form = jsonObj.optString("form");
            if (jsonObj.has("to"))
                to = jsonObj.optString("to");
            if (jsonObj.has("state"))
                state = jsonObj.optString("state");
            if (jsonObj.has("inviter"))
                inviter = jsonObj.optString("inviter");
            if (jsonObj.has("messageKey"))
                messageKey = jsonObj.optString("messageKey");
            if (jsonObj.has("startTime"))
                startTime = jsonObj.optString("startTime");
            if (jsonObj.has("endTime"))
                endTime = jsonObj.optString("endTime");

            Log.e("SystemNotifyMessage","JSONObject  :   "+jsonObj.toString());
        } catch (JSONException e) {
            RLog.e("SystemNotifyMessage", "JSONException", e);
        }
    }

    //给消息赋值。
    public SystemNotifyMessage(Parcel in) {
        //这里可继续增加你消息的属性
        setDes(ParcelUtils.readFromParcel(in));
        setContent(ParcelUtils.readFromParcel(in));
        setMessageId(ParcelUtils.readFromParcel(in));
        setForm(ParcelUtils.readFromParcel(in));
        setTo(ParcelUtils.readFromParcel(in));
        setState(ParcelUtils.readFromParcel(in));
        setInviter(ParcelUtils.readFromParcel(in));
        setMessageKey(ParcelUtils.readFromParcel(in));
        setStartTime(ParcelUtils.readFromParcel(in));
        setEndTime(ParcelUtils.readFromParcel(in));
        Log.e("SystemNotifyMessage", "SystemNotifyMessage: ");
    }

    /**
     * 读取接口，目的是要从Parcel中构造一个实现了Parcelable的类的实例处理。
     */
    public static final Creator<SystemNotifyMessage> CREATOR = new Creator<SystemNotifyMessage>() {

        @Override
        public SystemNotifyMessage createFromParcel(Parcel source) {
            return new SystemNotifyMessage(source);
        }

        @Override
        public SystemNotifyMessage[] newArray(int size) {
            return new SystemNotifyMessage[size];
        }
    };


    @Override
    public byte[] encode() {
        JSONObject jsonObj = new JSONObject();

        try {
            jsonObj.put("des", des);
            jsonObj.put("content", content);
            jsonObj.put("messageId", messageId);
            jsonObj.put("form", form);
            jsonObj.put("to", to);
            jsonObj.put("state", state);
            jsonObj.put("inviter", inviter);
            jsonObj.put("messageKey", messageKey);
            jsonObj.put("startTime", startTime);
            jsonObj.put("endTime", endTime);
            Log.e("SystemNotifyMessage", "EmotionMessage: "+ content );
        } catch (JSONException e) {
            Log.e("JSONException", e.getMessage());
        }
        try {
            return jsonObj.toString().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtils.writeToParcel(parcel, getDes());//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(parcel, getContent());//该类为工具类，对消息中属性进行序列化
        //这里可继续增加你消息的属性
        ParcelUtils.writeToParcel(parcel, getMessageId());//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(parcel, getForm());//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(parcel, getTo());//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(parcel, getState());//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(parcel, getInviter());//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(parcel, getMessageKey());//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(parcel, getStartTime());//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(parcel, getEndTime());//该类为工具类，对消息中属性进行序列化
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getInviter() {
        return inviter;
    }

    public void setInviter(String inviter) {
        this.inviter = inviter;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
