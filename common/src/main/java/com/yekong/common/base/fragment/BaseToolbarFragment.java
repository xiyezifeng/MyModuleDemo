package com.yekong.common.base.fragment;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.yekong.common.R;


/**
 * Created by xigua on 2017/6/20.
 */

public abstract class BaseToolbarFragment extends BaseFragment{

    TextView title;
    Toolbar toolbar;

    @Override
    public void doNext() {

    }

    @Override
    public void initSelf() {
        title = (TextView) getView().findViewById(R.id.title);
        toolbar = (Toolbar) getView().findViewById(R.id.toolbar);
        initToolBarData();
    }

    public void initToolBarData() {

        title.setText(getBarTitle()==0? R.string.null_text:getBarTitle());
        toolbar.setTitle("");
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        if (isShowLeft()) {
            if (leftBtnResId() != 0) {
                toolbar.setNavigationIcon(leftBtnResId());
            } else {
                ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onLeftClick();
                }
            });
        }



            if (getMenu() != 0 ){
                toolbar.inflateMenu(getMenu());
                Toolbar.OnMenuItemClickListener itemClickListener = new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                            onRightClick(item.getItemId());
                            return true;
                    }
                };
                toolbar.setOnMenuItemClickListener(itemClickListener);
            }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if ( getMenu() != 0) {
            inflater.inflate(getMenu(), menu);
        }
    }

    public abstract int getBarTitle();
    public abstract boolean isShowLeft();
    public abstract int leftBtnResId();
    public abstract void onLeftClick();
    public abstract void onRightClick(int id);
    public abstract int getMenu();
}
