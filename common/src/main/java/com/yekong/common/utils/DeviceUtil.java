package com.yekong.common.utils;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.widget.Toast;

/**
 * 作者：许 on 2017.3.31 0031 16:44
 * <p>
 * 界面说明：获取device的信息
 */

public class DeviceUtil {
    /**
     * 网络连接状态
     * @param context
     */
    public static boolean getNetState(Context context){
        ConnectivityManager connMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        boolean isWifiConn = networkInfo.isConnected();
        networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        boolean isMobileConn = networkInfo.isConnected();
        if (isWifiConn || isMobileConn){
            return true;
        }
        return false;
    }

    public static boolean checkNetAndShowDialog(Context context){
        if (getNetState(context)){
            return true;
        } else {
            new AlertDialog.Builder(context)
                    .setTitle("提示")
                    .setMessage("请检查网络连接")
                    .setNegativeButton("确定",null)
                    .create().show();

            return false;
        }
    }
    public static boolean checkNetAndShowToast(Context context){
        if (getNetState(context)){
            return true;
        } else {
            Toast.makeText(context, "请检查网络连接", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public static String getDeviceId(Application app){
        TelephonyManager tm = (TelephonyManager) app
                .getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }


}
