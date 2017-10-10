package com.yekong.home;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.yekong.common.api.Api;
import com.yekong.common.entity.BaseEntity;
import com.yekong.common.subscribe.BaseConsumer;
import com.yekong.common.subscribe.BaseObserve;

/**
 * Created by xigua on 2017/9/13.
 */
@Route(path = "/home/homeFragment")
public class HomeFragment extends RxFragment implements View.OnClickListener {
    private Button button;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button = view.findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.home_home_fragment,null);
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

    @Override
    public void onClick(View view) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext());
                 builder.setSmallIcon(R.mipmap.ic_launcher_round);
                 builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.home_lage));
                 builder.setContentTitle("一个标题");//系统限制，可能不显示
//                 builder.setContentText("为了方便别人使用我们开发的Android Library，一般我们都会把Android Library打成aar包，并将aar包发布到Maven仓库当中。如果是开源项目就可以把包发布到Maven Central仓库或者JCenter仓库中；如果是公司内部使用，一般公司内部会自己搭建私有Maven仓库，就把包发布到私有Maven仓库当中，以方便别人直接使用。\n" +
//                         "\n" +
//                         "本文将介绍如何使用Android Studio将项目发布到Maven私有仓库(使用Nexus搭建)以及JCenter中央仓库。");

                //系统限制，可能不显示
                 builder.setDefaults(Notification.DEFAULT_ALL);
                 //添加宽视图
                 NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle();

                 style.setSummaryText("为了方便别人使用我们开发的Android Library，一般我们都会把Android Library打成aar包，并将aar包发布到Maven仓库当中。如果是开源项目就可以把包发布到Maven Central仓库或者JCenter仓库中；如果是公司内部使用，一般公司内部会自己搭建私有Maven仓库，就把包发布到私有Maven仓库当中，以方便别人直接使用 本文将介绍如何使用Android Studio将项目发布到Maven私有仓库(使用Nexus搭建)以及JCenter中央仓库。");
                 style.bigLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher_round));
                 style.bigPicture(BitmapFactory.decodeResource(getResources(),R.mipmap.home_lage));
                 builder.setStyle(style);
                 Notification n = builder.build();
                 NotificationManager manager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                 manager.notify(10, n);
    }
}
