package com.yekong.im.message;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.View;
import android.view.ViewGroup;

import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.provider.IContainerItemProvider;

/**
 * Created by xigua on 2017/9/20.
 */
@ProviderTag(messageContent = SystemNotifyMessage.class)
public class SystemNotiryItemProvider extends IContainerItemProvider.MessageProvider<SystemNotifyMessage>  {
    @Override
    public void bindView(View view, int i, SystemNotifyMessage systemNotifyMessage, UIMessage uiMessage) {

    }

    @Override
    public Spannable getContentSummary(SystemNotifyMessage systemNotifyMessage) {
        return  new SpannableString(systemNotifyMessage.getDes()==null?"":systemNotifyMessage.getDes());
    }

    @Override
    public void onItemClick(View view, int i, SystemNotifyMessage systemNotifyMessage, UIMessage uiMessage) {

    }

    @Override
    public View newView(Context context, ViewGroup viewGroup) {
        return null;
    }
}
