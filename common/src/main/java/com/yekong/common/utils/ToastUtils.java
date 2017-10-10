package com.yekong.common.utils;

import android.content.Context;
import android.widget.Toast;

import static com.yekong.common.constant.Constant.DEBUG_TOAST;

/**
 * Created by xigua on 2017/9/13.
 */

public class ToastUtils {
    public static void showToast(Context context,String des){
        if (DEBUG_TOAST) {
            Toast.makeText(context, des, Toast.LENGTH_SHORT).show();
        }
    }
}
