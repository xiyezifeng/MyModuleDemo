package com.yekong.common;

import android.content.Context;

import com.yekong.common.api.Api;
import com.yekong.common.constant.Constant;
import com.yekong.common.entity.BaseEntity;
import com.yekong.common.subscribe.BaseConsumer;
import com.yekong.common.subscribe.BaseObserve;

/**
 * 方便module初始化数据
 * Created by xigua on 2017/9/14.
 */

public class DataInit {
    /**
     * 模拟登陆,并存储数据
     * @param id
     * @param pwd
     */
    public static void simulateLogin(Context context,String id , String pwd){
        if (!Constant.APP_DEBUG) {
            return;
        }
        BaseObserve.sendObserve(Api.mainApi().post_user_login(id,pwd),null,new BaseConsumer(context, new BaseConsumer.OnResponseListenter() {
            @Override
            public void onSuccess(BaseEntity baseEntity) {
                //存储storage模块
            }

            @Override
            public void onError(int errorCode, String message) {
                //测试模式下，不存在失败
            }
        }));
    }
}
