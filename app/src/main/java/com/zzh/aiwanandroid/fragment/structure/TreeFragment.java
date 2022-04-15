package com.zzh.aiwanandroid.fragment.structure;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.zzh.aiwanandroid.Constants;
import com.zzh.aiwanandroid.R;
import com.zzh.aiwanandroid.base.BaseFragment;
import com.zzh.aiwanandroid.bean.TreeBean;

import java.util.ArrayList;
import java.util.List;

public class TreeFragment extends BaseFragment {

    private ViewPager2 mViewPager;
    private TabLayout mTabLayout;

    private int mClickPosition;
    private List<TreeBean.Tree> mTreeList;

    private List<TreeDetailFragment> mFragments = new ArrayList<>();

    private TreeFragment() {
    }


    public static TreeFragment getInstance(List<TreeBean.Tree> treeList, int position) {
        TreeFragment fragment = new TreeFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(Constants.param1, (ArrayList<? extends Parcelable>) treeList);
        args.putInt(Constants.param2, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mViewPager = view.findViewById(R.id.view_pager_tree_detail);
        mTabLayout = view.findViewById(R.id.tab_layout_tree_detail);
    }


    @Override
    protected void initEventAndData() {

        if (getArguments() != null) {
            mTreeList = getArguments().getParcelableArrayList(Constants.param1);
            mClickPosition = getArguments().getInt(Constants.param2);
        }

        for (TreeBean.Tree tree : mTreeList) {
            mFragments.add(TreeDetailFragment.getInstance(tree, null));
        }

        mViewPager.setAdapter(new FragmentStateAdapter(getChildFragmentManager(), getLifecycle()) {
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
                tab.setText(mTreeList.get(position).getName());
            }
        }).attach();

        // tablayout 选中
        mTabLayout.getTabAt(mClickPosition).select();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tree_detail;
    }
}
