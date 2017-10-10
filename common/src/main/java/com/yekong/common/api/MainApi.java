package com.yekong.common.api;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by xigua on 2017/7/24.
 */

public interface MainApi {

    @FormUrlEncoded
    @POST("/user/login")
    Observable<String> post_user_login(@Field("id")String id ,@Field("pwd")String pwd);

    @FormUrlEncoded
    @POST("/")
    Observable<String> sendPost(@Field("data") String data);

    @Multipart
    @POST("/")
    Observable<String> sendPostMulti(@Part List<MultipartBody.Part> body);
}
