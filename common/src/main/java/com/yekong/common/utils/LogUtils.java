package com.yekong.common.utils;

import android.util.Log;

import static com.yekong.common.constant.Constant.DEBUG_LOG;

/**
 * Created by xigua on 2017/9/19.
 */

public class LogUtils {
    public static final String TAG = "LogUtils";

    /**
     * 无视debug模式
     * @param content
     */
    public static void a (String content){
        Log.e(TAG, content);
    }
    public static void e (String content){
        if (DEBUG_LOG) {
            Log.e(TAG, content);
        }
    }
    public static void d (String content){
        if (DEBUG_LOG) {
            Log.d(TAG, content);
        }
    }
    public static void w (String content){
        if (DEBUG_LOG) {
            Log.w(TAG, content);
        }
    }
    public static void i (String content){
        if (DEBUG_LOG) {
            Log.i(TAG, content);
        }
    }
}
