package com.yekong.common.dialog;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.cunoraz.gifview.library.GifView;
import com.yekong.common.R;


/**
 * Created by Administrator on 2017/5/12.
 * 网络请求提示框
 */

public class CustomAlertDialog extends BaseDialog{
    private Context context;
    private AlertDialog alertDialog;
    private String hintText;
    private boolean isCreate = false;
    private GifView gifView;
    private void showDialog(){
        if (!isCreate) {
            create();
        }
        if (!alertDialog.isShowing())
            alertDialog.show();
    }

    private void closeDialog(){
        if (alertDialog != null && alertDialog.isShowing())
            alertDialog.dismiss();
    }

    public CustomAlertDialog(Context context) {
        this(context,"加载中...");
        this.context = context;
    }

    public CustomAlertDialog(Context context, String hintText) {
        this.context = context;
        this.hintText = hintText;
    }

    @Override
    public void create() {
        View view = View.inflate(context, R.layout.dialog_network, null);
        alertDialog = new AlertDialog.Builder(context)
        .create();
        alertDialog.setView(view);
        alertDialog.setCancelable(true);
        alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.transparent);
        TextView textView = (TextView) view.findViewById(R.id.tv_text);
        textView.setText(hintText);
        gifView = (GifView) view.findViewById(R.id.iv_icon);
        gifView.setGifResource(R.mipmap.net_loading);
        gifView.play();
        isCreate = true;
    }

    @Override
    public void show() {
        showDialog();
    }

    @Override
    public void close() {
        closeDialog();
    }
}
