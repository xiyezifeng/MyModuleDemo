package com.yekong.common.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.yekong.common.R;

import java.util.ArrayList;


/**
 * Created by xigua on 2017/6/20.
 */

public abstract class BaseToolbarActivity extends BaseActivity {

    TextView title;
    Toolbar toolbar;

    private ArrayList<View> resoues = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void doNext() {

    }

    @Override
    public void initSelf() {
        title = (TextView) findViewById(R.id.title);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolBarData();
    }

    public void initToolBarData() {
        title.setText(getBarTitle()==0? R.string.null_text:getBarTitle());
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        if (isShowLeft()) {
            if (leftBtnResId() != 0) {
                toolbar.setNavigationIcon(leftBtnResId());
            } else {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                     onLeftClick();
                }
            } );
        }

            Toolbar.OnMenuItemClickListener itemClickListener = new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    onRightClick(item.getItemId());
                    onOptionsItemSelected(item);
                    return true;
                }
            };
            toolbar.setOnMenuItemClickListener(itemClickListener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if ( getMenu() != 0) {
            getMenuInflater().inflate(getMenu(), menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }



    public void setTitle(String title) {
        this.title.setText(title);
    }
    public ArrayList<View> getRightViews(){
        return resoues;
    }

    public abstract int getBarTitle();
    public abstract boolean isShowLeft();
    public abstract int leftBtnResId();
    public abstract void onLeftClick();
    public abstract int getMenu();
    public abstract void onRightClick(int id);

}
