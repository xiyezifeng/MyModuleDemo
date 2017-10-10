package com.yekong.im.activity;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yekong.common.base.activity.BaseToolbarActivity;
import com.yekong.im.R;
import com.yekong.im.helper.IMDbHelper;
import com.yekong.im.db.UserInfoEntity;
import com.yekong.im.helper.IMHelper;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imlib.model.UserInfo;

/**
 * Created by xigua on 2017/9/14.
 */
@Route(path = "/im/conversationActivity")
public class ConversationActivity extends BaseToolbarActivity {
    private ConversationFragment fragment;
    private String targetId;
    private String title;

    @Override
    public void initSelf() {
        super.initSelf();
        FragmentManager fm = getSupportFragmentManager();
        fragment = (ConversationFragment) fm.findFragmentById(R.id.conversation);
        //rong://com.yekong.im/conversation/group?targetId=g_001&title=%E7%BB%841
        Uri uri = fragment.getUri();
        Log.e("ConversationActivity", uri.getQuery());
        String[] query = IMHelper.getInstance().getTitleAndId(uri.getQuery());
        targetId = query[0];
        title = query[1];
        setTitle(title);

        switch (fragment.getConversationType().getValue()) {
            case 1:
                //单聊
                break;
            case 3:
                //群聊
                break;
        }
        RongIM.getInstance().setGroupMembersProvider(new RongIM.IGroupMembersProvider() {
            @Override
            public void getGroupMembers(String s, RongIM.IGroupMemberCallback iGroupMemberCallback) {
                //根据网络获取群组成员ID，或者具体数据
                List<UserInfoEntity> list = IMDbHelper.getInstance().getDaoSession().getUserInfoEntityDao().loadAll();
                List<UserInfo> userInfos = new ArrayList<>();
                for (UserInfoEntity entity : list) {
                    userInfos.add(new UserInfo(entity.userId, entity.userName, Uri.parse(entity.getUserAvata())));
                }
                iGroupMemberCallback.onGetGroupMembersResult(userInfos);
            }
        });
        IMHelper.getInstance().setCurrentConversationID(targetId);
        IMHelper.getInstance().setType(fragment.getConversationType());
    }

    @Override
    public int getBarTitle() {
        return 0;
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
    public int getViewLayoutId() {
        return R.layout.im_activity_conversation;
    }


}
