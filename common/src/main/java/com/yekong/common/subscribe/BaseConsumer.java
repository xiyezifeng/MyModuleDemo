package com.yekong.common.subscribe;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.yekong.common.entity.BaseEntity;
import com.yekong.common.utils.ToastUtils;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by xigua on 2017/7/27.
 */

public class BaseConsumer implements Consumer<String> {
    private OnResponseListenter listenter;
    private Context context;

    public BaseConsumer(Context context , OnResponseListenter listenter) {
        this.listenter = listenter;
        this.context = context;
    }

    @Override
    public void accept(@NonNull String responseBody) throws Exception {
        String body = responseBody;
        if (body.equals("123")) {
//            listenter.onError(Config.ErrorCode.NET_ERROR, "未能访问到服务器，请检查您的网络");
            ToastUtils.showToast(context, "未能访问到服务器，请检查您的网络");
            return;
        } else if (body.equals("456")) {
//            listenter.onError(Config.ErrorCode.NET_ERROR, "调试中，不联网络");
            ToastUtils.showToast(context, "未能访问到服务器，请检查您的网络");
            return;
        }
        BaseEntity baseBean = JSON.parseObject(body, BaseEntity.class);
        if (baseBean == null) {
            listenter.onError(0,"error");
        }else{
            listenter.onSuccess(baseBean);
        }
       /*if (null == baseBean) {
            listenter.onError(Config.ErrorCode.ERROR, "响应文本为空");
            ToastUtils.showToast(context, "响应文本为空");
        }else if(baseBean.getErrorCode() == Config.ErrorCode.TOKEN_ERROR){
            SharedTools.getInstance().cleanLoginInfo();
            ToastUtils.showToast(context, "登录失效，请从新登录");
            listenter.onError(Config.ErrorCode.TOKEN_ERROR, "登录失效，请从新登录");
            EventBus.getDefault().post(new LoginLostMessage());
        }else if(baseBean.getErrorCode() == Config.ErrorCode.DATA_EMPTY){
            //无更多数据
            listenter.onSuccess(baseBean);
        } else if (baseBean.getErrorCode() == 3001) {
            listenter.onSuccess(null);
        }else if(baseBean.getErrorCode() == 3002){
            baseBean.setData(new String());
            listenter.onSuccess(baseBean);
        }else if(!StringUtils.ValueNONull(baseBean.getData())){
            if (baseBean.getErrorInfo().contains("没有更多信息")) {
                listenter.onSuccess(baseBean);
            }else{
                listenter.onError(Config.ErrorCode.ERROR, "响应文本为空");
            }
            ToastUtils.showToast(context, baseBean.getErrorInfo());
            try {
                if (Config.APP_DEBUG_LOG)
                    baseBean.toString();
            } catch (Exception e) {

            }
        } else{
            try {
                if (Config.APP_DEBUG_LOG)
                    baseBean.toString();
            } catch (Exception e) {

            }
            listenter.onSuccess(baseBean);
        }*/


    }
    public interface OnResponseListenter{
        void onSuccess(BaseEntity baseEntity);
        void onError(int errorCode, String message);
    }
}
