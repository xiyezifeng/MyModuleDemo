package com.yekong.common.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xigua on 2017/6/20.
 */

public abstract class BaseFragment extends RxFragment {
    public static String TAG = "sight";
    public View rootView;
    Unbinder unbinder;


    protected final int TYPE_NOMAL = -1;
    protected int TYPE = TYPE_NOMAL;//-1 未刷新 0刷新 1更多
    protected final int TYPE_REFRUSH = 0;
    protected final int TYPE_MORE = 1;


    protected boolean isInit = false;
    protected boolean isInitData = false;
    protected boolean viewInit = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = inflater.inflate(getViewLayoutId(),null);
        unbinder = ButterKnife.bind(this, rootView);
        setHasOptionsMenu(true);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        rootView = view;
        isInit = true;
        setHasOptionsMenu(true);
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public boolean getUserVisibleHint() {
        return super.getUserVisibleHint();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
//        if (!isInitData && isInit && !viewInit) {
            isCanLoadData();
//        }
    }

    /**
     * 是否可以加载数据
     * 可以加载数据的条件：
     * 1.视图已经初始化
     * 2.视图对用户可见
     */
    public void isCanLoadData() {
        if (!isInit) {
            return;
        }
        if (getUserVisibleHint()){
            if (!viewInit) {
                viewInit = true;
                initSelf();
            }
            if (!isInitData) {
                isInitData = true;
                doNext();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        isCanLoadData();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public abstract int getViewLayoutId();
    public abstract void doNext();
    public abstract void initSelf();

    public View getRootView() {
        return rootView;
    }
    public void setInitData(boolean tag){
        isInitData = tag;
    }

}
