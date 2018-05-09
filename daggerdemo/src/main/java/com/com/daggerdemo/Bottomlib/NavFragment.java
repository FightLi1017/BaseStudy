package com.com.daggerdemo.Bottomlib;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.com.daggerdemo.BlankFragment;
import com.com.daggerdemo.BlankFragment1;
import com.com.daggerdemo.BlankFragment2;
import com.com.daggerdemo.BlankFragment3;
import com.com.daggerdemo.R;

// TODO: 2018/4/26 感觉后期可以完全做成add的形式 现在先不管 可以考虑读取文件
public class NavFragment extends Fragment {
    private NavigationTab navItemHome;
    private NavigationTab navItemAction;
    private NavigationTab navItemStudy;
    private NavigationTab navItemMe;
    private Context mContext;
    private int mContainerId;
    private FragmentManager mFragmentManager;
    private NavigationTab mCurrentNavTab;
    public NavFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setBackgroundResource(R.drawable.navgation);
        navItemHome.init(R.drawable.tab_icon_new,"测试1", BlankFragment.class);
        navItemAction.init(R.drawable.tab_icon_explore,"测试2", BlankFragment1.class);
        navItemStudy.init(R.drawable.tab_icon_me,"个人信息", BlankFragment2.class);
        navItemMe.init(R.drawable.tab_icon_tweet,"测试3", BlankFragment3.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plus_one, container, false);
        initView(view);
        return view;
    }

    public void config(Context context, FragmentManager fragmentManager,
                       @IdRes int mContainerId) {
        this.mContext = context;
        this.mFragmentManager = fragmentManager;
        this.mContainerId = mContainerId;
        doselect(navItemHome);
    }


    private void initView(View view) {
        navItemHome=view.findViewById(R.id.nav_item_home);
        navItemAction=view.findViewById(R.id.nav_item_action);
        navItemStudy=view.findViewById(R.id.nav_item_study);
        navItemMe=view.findViewById(R.id.nav_item_me);
        navItemHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doselect((NavigationTab)v);
            }
        });
        navItemAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doselect((NavigationTab)v);
            }
        });
        navItemStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doselect((NavigationTab)v);
            }
        });
        navItemMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doselect((NavigationTab)v);
            }
        });
    }

    private void doselect(NavigationTab newNavTab) {
        NavigationTab oldNavTab=null;
        if (mCurrentNavTab!=null){
            oldNavTab=mCurrentNavTab;
            if (oldNavTab==newNavTab){
                // TODO: 2018/4/26 you can do something
                return;
            }
            oldNavTab.setSelected(false);
        }
        newNavTab.setSelected(true);
        doTabChange(oldNavTab,newNavTab);
        mCurrentNavTab=newNavTab;
    }

    private void doTabChange(NavigationTab oldNavTab, NavigationTab newNavTab) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        if (oldNavTab != null) {
            if (oldNavTab.getFragment() != null) {
                ft.detach(oldNavTab.getFragment());
            }
        }
        if (newNavTab != null) {
            if (newNavTab.getFragment() == null) {
                Fragment fragment = Fragment.instantiate(mContext, newNavTab.getClz().getName(), null);
                ft.add(mContainerId, fragment, fragment.getTag());
                newNavTab.setFragment(fragment);
            } else {
                ft.attach(newNavTab.getFragment());
            }
        }
        ft.commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}
