package com.zzh.aiwanandroid.fragment.wechat;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;

import com.zzh.aiwanandroid.base.BaseFragment;

import java.util.List;
import java.util.Objects;

public class FragmentViewPager extends FragmentStatePagerAdapter {

    private FragmentManager fm;
    private List<WxAuthorDetailFragment> fragments;

    public FragmentViewPager(FragmentManager fm, List<WxAuthorDetailFragment> fragments) {
        super(fm);
        this.fm = fm;
        this.fragments = fragments;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Fragment fragment = fragments.get(position);
        FragmentTransaction transaction = fm.beginTransaction();
        if (!fragment.isAdded()) {
            transaction.add(fragment, fragment.getClass().getSimpleName());
            transaction.commitAllowingStateLoss();
        } else {
            //transaction.hide(fragment).show()
        }
        return null;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(fragments.get(position).getView());
    }
}
