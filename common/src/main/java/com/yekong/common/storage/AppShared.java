package com.yekong.common.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.yekong.common.base.BaseApplication;

/**
 * 全局 shared
 */
public class AppShared {

    private static volatile AppShared singleton;
    private SharedPreferences sharedPreferences;
    private static final String SHARED_NAME = "app";
    public static final int TYPE_STRING = 0;
    public static final int TYPE_INT = 1;
    public static final int TYPE_LONG = 2;
    public static final int TYPE_FLOAT = 3;
    public static final int TYPE_BOOLEAN = 4;

    private AppShared() {
        init();
    }

    public static AppShared getInstance() {
        if (singleton == null) {
            synchronized (AppShared.class) {
                if (singleton == null) {
                    singleton = new AppShared();
                }
            }
        }
        return singleton;
    }

    void init(){
        sharedPreferences = BaseApplication.getApplication().getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public void putValue(String key , Object value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (value instanceof String) {
            editor.putString(key, (String) value);
        }else if(value instanceof Integer){
            editor.putInt(key, (Integer) value);
        }else if(value instanceof Long){
            editor.putLong(key, (Long) value);
        }else if(value instanceof Boolean){
            editor.putBoolean(key, (Boolean) value);
        }else if(value instanceof Float){
            editor.putFloat(key, (Float) value);
        }
        editor.commit();
    }

    public <T> T  getValue(String key , int type){
        switch (type) {
            case TYPE_STRING:
                return (T) sharedPreferences.getString(key, "");
            case TYPE_INT:
                return (T) ((Integer)sharedPreferences.getInt(key, -1));
            case TYPE_LONG:
                return (T) ((Long)sharedPreferences.getLong(key, -1));
            case TYPE_FLOAT:
                return (T) ((Float)sharedPreferences.getFloat(key, -1));
            case TYPE_BOOLEAN:
                return (T) ((Boolean)sharedPreferences.getBoolean(key,false));
        }
        return null;
    }
}