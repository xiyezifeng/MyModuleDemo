package com.yekong.common.api;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by xigua on 2017/7/26.
 */

public interface PayApi {

    //微信支付
    @FormUrlEncoded
    @POST("https://api.mch.weixin.qq.com/pay/unifiedorder")
    Observable<ResponseBody> wechat_pay(@Field("entity") String url);
}
