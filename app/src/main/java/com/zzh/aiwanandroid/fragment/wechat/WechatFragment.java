package com.zzh.aiwanandroid.fragment.wechat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.zzh.aiwanandroid.Constants;
import com.zzh.aiwanandroid.R;
import com.zzh.aiwanandroid.base.BaseFragment;
import com.zzh.aiwanandroid.base.LazyBaseFragment;
import com.zzh.aiwanandroid.bean.WeChatBean;
import com.zzh.aiwanandroid.config.CallbackListener;
import com.zzh.aiwanandroid.config.HttpConfig;
import com.zzh.aiwanandroid.utils.HttpUtils;
import com.zzh.aiwanandroid.utils.LogUtils;
import com.zzh.aiwanandroid.widget.CustomDialog;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class WechatFragment extends LazyBaseFragment {

    private TabLayout mTabView;
    private CustomDialog dialog;
    private ViewPager mViewPager;

    private List<BaseFragment> mFragments = new ArrayList<>();


    public static WechatFragment getInstance(String param1, String param2) {
        Bundle args = new Bundle();
        WechatFragment fragment = new WechatFragment();
        args.putString(Constants.param1, param1);
        args.putString(Constants.param2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mTabView = view.findViewById(R.id.tab_layout);
        dialog = new CustomDialog(getContext());
        mViewPager = view.findViewById(R.id.view_pager);
    }

    @Override
    protected void onVisible() {
        LogUtils.d("onVisible");
    }

    @Override
    protected void onInVisible() {
        LogUtils.d("onInVisible");
    }

    @Override
    protected void initEventAndData() {
        dialog.show();
        // 加载微信公众号账号
        loadWechatAuthor();
    }


    private void initViewPagerAndTabLayout(List<WeChatBean.WeChat> wxAuthors) {
        mViewPager.setAdapter(new FragmentViewPager(getChildFragmentManager(),mFragments){
            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return wxAuthors.get(position).getName();
            }
        });
//        mViewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
//            @NonNull
//            @Override
//            public Fragment getItem(int position) {
//                WxAuthorDetailFragment fragment = (WxAuthorDetailFragment) mFragments.get(position);
//                return fragment;
//            }
//
//            @Override
//            public int getCount() {
//                return mFragments == null ? 0 : mFragments.size();
//            }
//
//            @Nullable
//            @Override
//            public CharSequence getPageTitle(int position) {
//                return wxAuthors.get(position).getName();
//            }
//        });
        mTabView.setupWithViewPager(mViewPager);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wechat;
    }

    /**
     * 加载公众号
     */
    private void loadWechatAuthor() {
        HttpUtils.sendHttpRequest(HttpConfig.GET_WECHAT_URL, new CallbackListener() {
            @Override
            public void onSuccess(String response) {
                WeChatBean weChatBean = HttpUtils.parseJson(response, WeChatBean.class);
                List<WeChatBean.WeChat> weChatList = weChatBean.getData();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       mFragments.clear();
                        for (WeChatBean.WeChat chat : weChatList) {
                            mFragments.add(WxAuthorDetailFragment.getInstance(chat.getId(), chat.getName()));
                            mTabView.addTab(mTabView.newTab().setText(chat.getName()));
                        }
                        initViewPagerAndTabLayout(weChatList);
                        dialog.dismiss();
                    }
                });
            }

            @Override
            public void onFailure(Call call) {

            }
        });
    }


}
