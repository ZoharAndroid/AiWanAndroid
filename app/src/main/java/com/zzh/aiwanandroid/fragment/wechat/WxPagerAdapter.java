package com.zzh.aiwanandroid.fragment.wechat;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class WxPagerAdapter extends FragmentStateAdapter {

    private List<WxAuthorDetailFragment> mFragments;

    public WxPagerAdapter(FragmentManager fm, Lifecycle lifecycle, List<WxAuthorDetailFragment> mFragments) {
        super(fm, lifecycle);
        this.mFragments = mFragments;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getItemCount() {
        return mFragments.size();
    }
}
