package com.yekong.im.fragment;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yekong.common.base.fragment.BaseToolbarFragment;
import com.yekong.im.R;

import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.model.Conversation;

/**
 * Created by xigua on 2017/9/14.
 */
@Route(path = "/im/conversationListFragment")
public class MyConversationListFragment extends BaseToolbarFragment {
    private FrameLayout content;
    private ConversationListFragment fragment;

    /**
     * 绑定条目 头像点击 可以不实现
     */
    private void bindClick(){

    }

    @Override
    public void initSelf() {
        super.initSelf();
        content = getView().findViewById(R.id.content);
//        setUri(uri);
        if (fragment == null) {
            Uri uri = Uri.parse("rong://" + getContext().getApplicationInfo().packageName).buildUpon()
                    .appendPath("conversationlist")
                    .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话，该会话聚合显示
                    .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//设置群组会话，该会话非聚合显示
                    .build();
            fragment = new ConversationListFragment();
            fragment.setUri(uri);
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.add(R.id.content, fragment);
            transaction.commit();
        }
    }

    @Override
    public void doNext() {
        super.doNext();

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public int getViewLayoutId() {
        return R.layout.im_conversation_fragment;
    }



    @Override
    public int getBarTitle() {
        return R.string.im_title_conversation_list;
    }

    @Override
    public boolean isShowLeft() {
        return false;
    }

    @Override
    public int leftBtnResId() {
        return 0;
    }

    @Override
    public void onLeftClick() {

    }

    @Override
    public void onRightClick(int id) {

    }

    @Override
    public int getMenu() {
        return 0;
    }
}
