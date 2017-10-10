package com.yekong.common.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;


import butterknife.ButterKnife;

/**
 * Created by xigua on 2017/6/20.
 */

public abstract class BaseActivity extends RxAppCompatActivity {

    protected final int TYPE_NOMAL = -1;
    protected int TYPE = TYPE_NOMAL;//-1 未刷新 0刷新 1更多

    protected final int TYPE_REFRUSH = 0;
    protected final int TYPE_MORE = 1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getViewLayoutId()>0) {
            setContentView(getViewLayoutId());
            ButterKnife.bind(this);
            initSelf();
            doNext();
        }
    }

    public abstract int getViewLayoutId();
    public abstract void doNext();
    public abstract void initSelf();

    public void log(String log){

    }

    @Override
    public void onBackPressed() {
        supportFinishAfterTransition();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
