package com.yekong.im.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yekong.common.base.listener.RecyclerGlobalListener;
import com.yekong.im.R;
import com.yekong.im.db.SystemNotifyEntity;
import com.yekong.im.helper.IMHelper;

import java.util.ArrayList;

import butterknife.BindView;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.UserInfo;

/**
 * Created by xigua on 2017/9/20.
 */

public class SystemNotifyAdapter extends RecyclerView.Adapter<SystemNotifyAdapter.ViewHolder> {

    private Context context;
    private RecyclerGlobalListener<SystemNotifyEntity> listener;
    private ArrayList<SystemNotifyEntity> list;

    public SystemNotifyAdapter(Context context, RecyclerGlobalListener<SystemNotifyEntity> listener) {
        this.context = context;
        this.listener = listener;
    }

    public void setList(ArrayList<SystemNotifyEntity> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder( LayoutInflater.from(context).inflate(R.layout.im_item_sys_notify,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final SystemNotifyEntity systemNotifyEntity = list.get(position);
        String userId = systemNotifyEntity.getForm();
        if (userId.startsWith("g")) {
            Group group = IMHelper.getInstance().findGroupById(userId);
            Glide.with(context)
                    .load(group.getPortraitUri())
                    .centerCrop()
                    .into(holder.ivAvatar);
            holder.tvDes.setText(systemNotifyEntity.getContent());
        }else{
            UserInfo userInfo = IMHelper.getInstance().findUserById(userId);
            Glide.with(context)
                    .load(userInfo.getPortraitUri())
                    .centerCrop()
                    .into(holder.ivAvatar);
            holder.tvDes.setText(systemNotifyEntity.getContent());
        }
        holder.btnAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onViewClick(view,systemNotifyEntity,position);
            }
        });
        holder.btnRefuse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onViewClick(view,systemNotifyEntity,position);
            }
        });
        if (systemNotifyEntity.getState().equals("0")) {
            holder.tvStatus.setVisibility(View.GONE);
            holder.btnAgree.setVisibility(View.VISIBLE);
            holder.btnRefuse.setVisibility(View.VISIBLE);
        }else{
            holder.btnAgree.setVisibility(View.GONE);
            holder.btnRefuse.setVisibility(View.GONE);
            holder.tvStatus.setVisibility(View.VISIBLE);
        }
        if (systemNotifyEntity.getState().equals("1")) {
            holder.tvStatus.setText("已同意");
        }else if(systemNotifyEntity.getState().equals("2")){
            holder.tvStatus.setText("已拒绝");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_avatar)
        ImageView ivAvatar;
        @BindView(R.id.tv_des)
        TextView tvDes;
        @BindView(R.id.btn_agree)
        Button btnAgree;
        @BindView(R.id.btn_refuse)
        Button btnRefuse;
        TextView tvStatus;
        public ViewHolder(View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);
            tvDes = itemView.findViewById(R.id.tv_des);
            btnAgree = itemView.findViewById(R.id.btn_agree);
            btnRefuse = itemView.findViewById(R.id.btn_refuse);
            tvStatus = itemView.findViewById(R.id.tv_status);
        }
    }
}
