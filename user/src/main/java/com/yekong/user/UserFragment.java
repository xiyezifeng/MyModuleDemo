package com.yekong.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.yekong.common.api.Api;
import com.yekong.common.entity.BaseEntity;
import com.yekong.common.subscribe.BaseConsumer;
import com.yekong.common.subscribe.BaseObserve;

/**
 * Created by xigua on 2017/9/14.
 */

@Route(path = "/user/userFragment")
public class UserFragment extends RxFragment{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.user_user_fragment,null);
    }

    void test(){
        BaseObserve.fragmentSendObserve(this, Api.mainApi().sendPost(""),null , new BaseConsumer(getContext(), new BaseConsumer.OnResponseListenter() {
            @Override
            public void onSuccess(BaseEntity baseEntity) {

            }

            @Override
            public void onError(int errorCode, String message) {

            }
        }));
    }
}
