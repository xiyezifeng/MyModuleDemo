package com.yekong.im.plugin;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yekong.im.R;
import com.yekong.im.entity.MotioncEntity;
import com.yekong.im.helper.IMHelper;
import com.yekong.im.message.EmotionMessage;

import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imkit.emoticon.IEmoticonTab;
import io.rong.imlib.IRongCallback;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;

/**
 * Created by xigua on 2017/9/18.
 */

public class MyEmoticon implements IEmoticonTab {
    private RecyclerView recyclerView;
    private GroupAdapter adapter;
    private final String key = "group_1";
    private List<MotioncEntity>  list = IMHelper.getInstance().getMotionList(key);

    @Override
    public Drawable obtainTabDrawable(Context context) {
        return context.getResources().getDrawable(R.mipmap.ic_launcher);
    }

    @Override
    public View obtainTabPager(Context context) {
        return initView(context);
    }

    @Override
    public void onTableSelected(int i) {

        Log.e("MyEmoticon", "i:" + i);
    }

    private View initView(Context context){
        View view ;
        view = LayoutInflater.from(context).inflate(R.layout.im_emotion_group_1, null);
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(context,4));
        adapter = new GroupAdapter(context);
        recyclerView.setAdapter(adapter);
        return view;
    }

    private class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder>{
        private Context context;

        public GroupAdapter(Context context) {
            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.im_item_group,parent,false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            MotioncEntity entity = list.get(position);
            holder.item.setImageResource(entity.getResId()==0?R.mipmap.ic_launcher:entity.getResId());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //发送表情
                    EmotionMessage message = EmotionMessage.obtain("[表情]", key, position+"");
                    Message message1 = Message.obtain(IMHelper.getInstance().getCurrentConversationID(), IMHelper.getInstance().getType(), message);
                    RongIM.getInstance().sendMessage(message1, "您有一条消息", null, new IRongCallback.ISendMessageCallback() {
                        @Override
                        public void onAttached(Message message) {
                            Log.e("GroupAdapter", "onAttached");
                        }

                        @Override
                        public void onSuccess(Message message) {
                            Log.e("GroupAdapter", "success");
                        }

                        @Override
                        public void onError(Message message, RongIMClient.ErrorCode errorCode) {
                            Log.e("GroupAdapter", "onError");
                        }
                    });
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            ImageView item;
            public ViewHolder(View itemView) {
                super(itemView);
                item = itemView.findViewById(R.id.item);
            }
        }
    }


}
