package com.yekong.im.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yekong.common.base.activity.BaseToolbarActivity;
import com.yekong.common.base.listener.RecyclerGlobalListener;
import com.yekong.common.utils.LogUtils;
import com.yekong.im.R;
import com.yekong.im.adapter.SystemNotifyAdapter;
import com.yekong.im.db.SystemNotifyEntity;
import com.yekong.im.db.SystemNotifyEntityDao;
import com.yekong.im.helper.IMDbHelper;

import java.util.ArrayList;

import butterknife.BindView;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

/**
 * Created by xigua on 2017/9/20.
 */

public class SystemNotifyActivity extends BaseToolbarActivity implements RecyclerGlobalListener<SystemNotifyEntity> {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    private SystemNotifyAdapter adapter;
    private ArrayList<SystemNotifyEntity> list = new ArrayList<>();

    @Override
    public int getViewLayoutId() {
        return R.layout.im_activity_sys_notify;
    }

    @Override
    public int getBarTitle() {
        return R.string.im_title_sys_notify;
    }

    @Override
    public boolean isShowLeft() {
        return true;
    }

    @Override
    public int leftBtnResId() {
        return 0;
    }

    @Override
    public void onLeftClick() {
        finish();
    }

    @Override
    public int getMenu() {
        return 0;
    }

    @Override
    public void onRightClick(int id) {

    }

    @Override
    public void doNext() {
        super.doNext();
        SystemNotifyEntityDao dao = IMDbHelper.getInstance().getDaoSession().getSystemNotifyEntityDao();
        list.addAll(dao.loadAll());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void initSelf() {
        super.initSelf();
        //清除未读
        RongIM.getInstance().clearMessagesUnreadStatus(Conversation.ConversationType.PRIVATE, "00000", new RongIMClient.ResultCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {
                LogUtils.a("清除未读成功");
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                LogUtils.a( "清除未读失败");
            }
        });

        adapter = new SystemNotifyAdapter(this,this);
        adapter.setList(list);
        if (recyclerview == null) {
            recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        }
        if (recyclerview != null) {
            recyclerview.setLayoutManager(new LinearLayoutManager(this));
            recyclerview.setAdapter(adapter);
        }
    }

    @Override
    public void onRootViewClick(View v, SystemNotifyEntity systemNotifyEntity, int pos) {

    }

    @Override
    public void onViewClick(View v, SystemNotifyEntity systemNotifyEntity, int pos) {
        SystemNotifyEntityDao systemNotifyEntityDao  =  IMDbHelper.getInstance().getDaoSession().getSystemNotifyEntityDao();
        switch (v.getId()) {
            case R.id.btn_agree:
                systemNotifyEntity.setState("1");
                systemNotifyEntity.setContent("同意了"+systemNotifyEntity.getForm()+"的申请");
                systemNotifyEntity.setMessageKey(systemNotifyEntity.getMessageKey()+"_1");
                break;
            case R.id.btn_refuse:
                systemNotifyEntity.setState("2");
                systemNotifyEntity.setContent("拒绝了"+systemNotifyEntity.getForm()+"的申请");
                systemNotifyEntity.setMessageKey(systemNotifyEntity.getMessageKey()+"_2");
                break;
        }
        systemNotifyEntityDao.update(systemNotifyEntity);
        adapter.notifyDataSetChanged();
    }
}
