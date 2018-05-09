package com.com.daggerdemo.Bottomlib;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.com.daggerdemo.R;

public class NavigationTab extends LinearLayout {
    private ImageView navIvIcon;
    private TextView navTvDot;
    private TextView navTvTitle;
    private Fragment mFragment;
    private Class<?> clz;

    public NavigationTab(Context context) {
        super(context);
        init();
    }

    public NavigationTab(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NavigationTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.navigation_tab, this, true);
        navIvIcon = findViewById(R.id.nav_iv_icon);
        navTvTitle = findViewById(R.id.nav_tv_title);
        navTvDot = findViewById(R.id.nav_tv_dot);

    }

    public void showRedDot(int count) {
        navTvDot.setVisibility(count > 0 ? VISIBLE : GONE);
        if (count > 99) {
            navTvDot.setText("99+");
        } else {
            navTvDot.setText(String.valueOf(count));
        }
    }
    public void init(@DrawableRes int res, String title, Class<?> clz) {
        navTvTitle.setText(title);
        navIvIcon.setImageResource(res);
        this.clz=clz;
    }
    public void init(@DrawableRes int res, @StringRes int title, Class<?> clz) {
        navTvTitle.setText(title);
        navIvIcon.setImageResource(res);
        this.clz=clz;
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        navIvIcon.setSelected(selected);
        navTvTitle.setSelected(selected);
    }

    public Fragment getFragment() {
        return mFragment;
    }

    public void setFragment(Fragment fragment) {
        mFragment = fragment;
    }
    public Class<?> getClz() {
        return clz;
    }
}
