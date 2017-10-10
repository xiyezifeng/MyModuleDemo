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
 * 表情
 * Created by xigua on 2017/9/18.
 */

@SuppressLint("ParcelCreator")
@MessageTag(value = "app:emotion", flag = MessageTag.ISCOUNTED | MessageTag.ISPERSISTED)
public class EmotionMessage extends MessageContent {

    private  String content;//消息属性，可随意定义
    private  String groupId;
    private  String indexId;

    public static EmotionMessage obtain(String content, String groupId, String indexId) {
        EmotionMessage message = new EmotionMessage();
        message.setContent(content);
        message.setGroupId(groupId);
        message.setIndexId(indexId);
        return message;
    }

    public EmotionMessage() {
    }

    public EmotionMessage(byte[] data) {
        super(data);
        String jsonStr = null;

        try {
            jsonStr = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e1) {

        }

        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            if (jsonObj.has("content"))
                content = jsonObj.optString("content");
            if (jsonObj.has("groupId"))
                groupId = jsonObj.optString("groupId");
            if (jsonObj.has("indexId"))
                indexId = jsonObj.optString("indexId");
            Log.e("MessageContent","JSONObject  :   "+jsonObj.toString());
        } catch (JSONException e) {
            RLog.e("MessageContent", "JSONException", e);
        }
    }

    //给消息赋值。
    public EmotionMessage(Parcel in) {
        //这里可继续增加你消息的属性
//        content= ParcelUtils.readFromParcel(in);//该类为工具类，消息属性
//        groupId = ParcelUtils.readFromParcel(in);
//        indexId = ParcelUtils.readFromParcel(in);
        setContent(ParcelUtils.readFromParcel(in));
        setGroupId(ParcelUtils.readFromParcel(in));
        setIndexId(ParcelUtils.readFromParcel(in));
        Log.e("MessageContent", "EmotionMessage: ");

//        ImageMessage
    }

    /**
     * 读取接口，目的是要从Parcel中构造一个实现了Parcelable的类的实例处理。
     */
    public static final Creator<EmotionMessage> CREATOR = new Creator<EmotionMessage>() {

        @Override
        public EmotionMessage createFromParcel(Parcel source) {
            return new EmotionMessage(source);
        }

        @Override
        public EmotionMessage[] newArray(int size) {
            return new EmotionMessage[size];
        }
    };


    @Override
    public byte[] encode() {
        JSONObject jsonObj = new JSONObject();

        try {
            jsonObj.put("content", content);
            jsonObj.put("groupId", groupId);
            jsonObj.put("indexId", indexId);

            Log.e("MessageContent", "EmotionMessage: "+ content );
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
        ParcelUtils.writeToParcel(parcel, getContent());//该类为工具类，对消息中属性进行序列化
        //这里可继续增加你消息的属性
        ParcelUtils.writeToParcel(parcel, getGroupId());//该类为工具类，对消息中属性进行序列化
        ParcelUtils.writeToParcel(parcel, getIndexId());//该类为工具类，对消息中属性进行序列化

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getIndexId() {
        return indexId;
    }

    public void setIndexId(String indexId) {
        this.indexId = indexId;
    }
}
