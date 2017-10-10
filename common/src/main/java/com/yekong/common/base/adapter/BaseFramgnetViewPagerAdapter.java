package com.yekong.common.base.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by xigua on 2017/9/13.
 */

public class BaseFramgnetViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> context;
    public BaseFramgnetViewPagerAdapter(List<Fragment> context,FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return context.get(position);
    }

    @Override
    public int getCount() {
        return context.size();
    }
}
