package com.yekong.im.message;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yekong.im.R;
import com.yekong.im.entity.MotioncEntity;
import com.yekong.im.helper.IMHelper;

import java.util.List;

import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.provider.IContainerItemProvider;
import io.rong.imlib.model.Message;

/**
 * Created by xigua on 2017/9/18.
 */
@ProviderTag(messageContent = EmotionMessage.class)
public class EmotionMessageItemProvider extends  IContainerItemProvider.MessageProvider<EmotionMessage> {
    class ViewHolder {
        ImageView message;
    }
    @Override
    public void bindView(View view, int i, EmotionMessage emotionMessage, UIMessage uiMessage) {
        ViewHolder holder = (ViewHolder) view.getTag();

        if (uiMessage.getMessageDirection() == Message.MessageDirection.SEND) {//消息方向，自己发送的
            holder.message.setBackgroundResource(io.rong.imkit.R.drawable.rc_ic_bubble_right);
        } else {
            holder.message.setBackgroundResource(io.rong.imkit.R.drawable.rc_ic_bubble_left);
        }
        //int index = IMHelper.getInstance().getMotionList(emotionMessage.getGroupId()).get(parseInt(emotionMessage.getIndexId())).intValue();
        if (emotionMessage.getGroupId() != null) {
            if (emotionMessage.getIndexId() != null) {
                try {
                    List<MotioncEntity> entities = IMHelper.getInstance().getMotionList(emotionMessage.getGroupId());
                    int index = Integer.parseInt(emotionMessage.getIndexId());
                    MotioncEntity  entity = entities.get(index);

                    holder.message.setImageResource(entity.getResId() == 0?R.mipmap.ic_launcher:entity.getResId());
                } catch (Exception e) {

                }
            }
        }
//        holder.message.setImageResource(R.mipmap.ic_launcher);
//        AndroidEmoji.ensure(emotionMessage.getContent());
        /*holder.message.setText(uiMessage.getContent());
        AndroidEmoji.ensure((Spannable) holder.message.getText());//显示消息中的 Emoji 表情。*/

    }

    @Override
    public Spannable getContentSummary(EmotionMessage emotionMessage) {
        return new SpannableString(emotionMessage.getContent());
    }

    @Override
    public void onItemClick(View view, int i, EmotionMessage emotionMessage, UIMessage uiMessage) {
        Log.e("EmotionMessageItemProvi", "表情被点击");
    }

    @Override
    public View newView(Context context, ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.im_item_image, null);
        ViewHolder holder = new ViewHolder();
        holder.message = view.findViewById(R.id.ima);
        view.setTag(holder);
        return view;
    }
}
