package com.paopao.newservlet.ui.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paopao.newservlet.R;
import com.paopao.newservlet.ui.adapter.MainPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 * 新闻主页面的Fragment
 */
public class MainFragment extends Fragment {

    private View view;
    ViewPager viewPager;
    MainPagerAdapter pagerAdapter;
    TabLayout tabLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_main, container, false);
        initView();
        return view;
    }

    private void initView(){
        viewPager= view. findViewById(R.id.view_pager);
        tabLayout= view.findViewById(R.id.tab_layout);

        pagerAdapter=new MainPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(2);

        //TabLayout绑定ViewPager
        tabLayout.setupWithViewPager(viewPager);
    }

    public static MainFragment newInstance(){
        MainFragment mainFragment=new MainFragment();
        return mainFragment;
    }

}
