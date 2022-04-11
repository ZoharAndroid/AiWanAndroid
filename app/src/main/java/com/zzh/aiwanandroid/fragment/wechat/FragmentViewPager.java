package com.zzh.aiwanandroid.fragment.wechat;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;

import com.zzh.aiwanandroid.base.BaseFragment;

import java.util.List;

public class FragmentViewPager extends PagerAdapter {

    private FragmentManager fm;
    private List<BaseFragment> fragments;

    public FragmentViewPager(FragmentManager fm, List<BaseFragment> fragments) {
        this.fm = fm;
        this.fragments = fragments;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Fragment fragment = fragments.get(position);
        if (!fragment.isAdded()) {
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.add(fragment, fragment.getClass().getSimpleName());
            transaction.commitAllowingStateLoss();
            fm.executePendingTransactions();
        }
        if (fragment.getView().getParent() == null) {
            container.addView(fragment.getView());
        }
        return fragment.getView();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView(fragments.get(position).getView());

    }
}
