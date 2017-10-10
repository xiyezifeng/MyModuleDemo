package com.yekong.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.yekong.common.base.adapter.BaseFramgnetViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xigua on 2017/9/13.
 */

public class HomeActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_home);
        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        Fragment home = (Fragment) ARouter.getInstance().build("/home/homeFragment").navigation();
        Fragment user = (Fragment) ARouter.getInstance().build("/user/userFragment").navigation();
        Fragment im   = (Fragment) ARouter.getInstance().build("/im/conversationListFragment").navigation();

        if (home != null) {
            fragments.add(home);
        }
        if (user != null) {
            fragments.add(user);
        }
        if (im != null) {
            fragments.add(im);
        }
        viewPager.setOffscreenPageLimit(fragments.size());
        BaseFramgnetViewPagerAdapter adapter = new BaseFramgnetViewPagerAdapter(fragments,this.getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }
}
