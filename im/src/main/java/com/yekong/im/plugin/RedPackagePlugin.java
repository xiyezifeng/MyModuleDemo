package com.yekong.im.plugin;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.yekong.im.R;

import io.rong.imkit.RongExtension;
import io.rong.imkit.plugin.IPluginModule;

/**
 * Created by xigua on 2017/9/18.
 */

public class RedPackagePlugin implements IPluginModule {
    @Override
    public Drawable obtainDrawable(Context context) {
        return context.getResources().getDrawable(R.mipmap.home_);
    }

    @Override
    public String obtainTitle(Context context) {
        return "红包";
    }

    @Override
    public void onClick(Fragment fragment, RongExtension rongExtension) {
        Toast.makeText(fragment.getContext(), "red click", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onActivityResult(int i, int i1, Intent intent) {

    }
}
