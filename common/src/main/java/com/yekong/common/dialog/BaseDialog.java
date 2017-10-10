package com.yekong.common.dialog;

import android.app.ProgressDialog;

/**
 * Created by Administrator on 2017/5/12.
 */

public abstract class BaseDialog {
    ProgressDialog alertDialog;
    public abstract void create();
    public abstract void show();
    public abstract void close();
}
