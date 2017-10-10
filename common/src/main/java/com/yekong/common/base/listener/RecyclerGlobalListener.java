package com.yekong.common.base.listener;

import android.view.View;

/**
 * Created by xigua on 2017/9/20.
 */

public interface RecyclerGlobalListener<T> {
    void onRootViewClick(View v,T t,int pos);
    void onViewClick(View v,T t,int pos);
}
