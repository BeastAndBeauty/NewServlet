package com.paopao.newservlet.ui.adapter;



import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.paopao.newservlet.ui.childFragment.CaiJingFragment;
import com.paopao.newservlet.ui.childFragment.GuoNeiFragment;
import com.paopao.newservlet.ui.childFragment.JunShiFragment;
import com.paopao.newservlet.ui.childFragment.KeJiFragment;
import com.paopao.newservlet.ui.childFragment.SheHuiFragment;
import com.paopao.newservlet.ui.childFragment.ShiShangFragment;
import com.paopao.newservlet.ui.childFragment.TiYuFragment;
import com.paopao.newservlet.ui.childFragment.TouTiaoFragment;
import com.paopao.newservlet.ui.childFragment.YuLeFragment;

/**
 * 作者：paopao on 2018/12/5 19:55
 * <p>
 * 作用:主页面 ViewPager的适配器
 */
public class MainPagerAdapter extends FragmentPagerAdapter {
    private String[] mTitles=new String[]{"头条","社会","娱乐","体育","军事","科技","财经","国内","时尚"};

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        if (i==0)
            return new TouTiaoFragment();
        else if (i==1)
            return new SheHuiFragment();
        else if (i==2)
            return new YuLeFragment();
        else if (i==3)
            return new TiYuFragment();
        else if (i==4)
            return new JunShiFragment();
        else if (i==5)
            return new KeJiFragment();
        else if(i==6)
            return new CaiJingFragment();
        else if(i==7)
            return new GuoNeiFragment();
        else
            return new ShiShangFragment();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
