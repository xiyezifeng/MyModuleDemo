package com.yekong.common.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.yekong.common.constant.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.yekong.common.constant.Constant.BASE_URL;


public class Api {
    private static Api instance;
    public static final String BASEURL = BASE_URL;

    private Api() {
    }

    public static Api getInstance() {
        if (instance == null) {
            instance = new Api();
        }
        return instance;
    }

    private OkHttpClient okHttpClient;
    public OkHttpClient getOkHttpClient(){
        if ( null == okHttpClient) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(Constant.APP_DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
            okHttpClient = new OkHttpClient()
                    .newBuilder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .build();
        }
        return okHttpClient;
    }

    private static Retrofit apiAdapter;

    public Retrofit getApiAdapter(){
        if (null == apiAdapter){
            if (null == okHttpClient) okHttpClient = getOkHttpClient();
            apiAdapter = new Retrofit.Builder()
                    .baseUrl(BASEURL+"/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .client(getOkHttpClient())
                    .build();

        }
        return apiAdapter;
    }

    static MainApi homeApi;
    public static MainApi mainApi(){
        if (null == homeApi)
            homeApi =  getInstance().getApiAdapter().create(MainApi.class);
        return homeApi;
    }
    static PayApi payApi;
    public static PayApi payApi(){
        if (null == payApi)
            payApi =  getInstance().getApiAdapter().create(PayApi.class);
        return payApi;
    }

}