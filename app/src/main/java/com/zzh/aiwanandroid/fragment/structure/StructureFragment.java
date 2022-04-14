package com.zzh.aiwanandroid.fragment.structure;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.zzh.aiwanandroid.Constants;
import com.zzh.aiwanandroid.R;
import com.zzh.aiwanandroid.activity.MainActivity;
import com.zzh.aiwanandroid.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class StructureFragment extends BaseFragment {

    private ViewPager2 mViewPager;
    private TabLayout mTabLayout;

    private List<String> tabTitleList = new ArrayList<>();

    private List<Fragment> mFragments = new ArrayList<>();

    public static StructureFragment getInstance(String param1, String param2) {
        Bundle args = new Bundle();
        StructureFragment fragment = new StructureFragment();
        args.putString(Constants.param1, param1);
        args.putString(Constants.param2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mViewPager = view.findViewById(R.id.view_pager_structure);
        mTabLayout = ((MainActivity) getActivity()).getTabLayout();


    }


    @Override
    protected void initEventAndData() {

        tabTitleList.add("体系");
        tabTitleList.add("导航");

        setTabLayout();

        // 创建Fragment
        mFragments.add(SystemFragment.getInstance(null, null));
        mFragments.add(NavigationFragment.getInstance(null, null));

        mViewPager.setAdapter(new FragmentStateAdapter(getChildFragmentManager(),getLifecycle()) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getItemCount() {
                return mFragments.size();
            }
        });

        new TabLayoutMediator(mTabLayout, mViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabTitleList.get(position));
            }
        }).attach();


    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_structure;
    }

    /**
     * 设置TabLayout
     */
    private void setTabLayout() {
        // 找到Fragment中的ViewPager
        for (String text : tabTitleList) {
            mTabLayout.addTab(mTabLayout.newTab().setText(text));
        }
        // 设置tab选中颜色
        mTabLayout.setTabTextColors(getResources().getColor(R.color.tab_text_color), getResources().getColor(R.color.white));
    }

}
