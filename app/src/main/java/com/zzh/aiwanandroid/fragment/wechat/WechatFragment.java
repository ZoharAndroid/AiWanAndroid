package com.zzh.aiwanandroid.fragment.wechat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
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

public class WechatFragment extends BaseFragment {

    private TabLayout mTabView;
    private CustomDialog dialog;
    private ViewPager2 mViewPager;

    private List<WxAuthorDetailFragment> mFragments = new ArrayList<>();


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
    protected void initEventAndData() {
        dialog.show();
        // 加载微信公众号账号
        loadWechatAuthor();
    }


    private void initViewPagerAndTabLayout(List<WeChatBean.WeChat> wxAuthors) {

        for (WeChatBean.WeChat chat : wxAuthors) {
            mFragments.add(WxAuthorDetailFragment.getInstance(chat.getId(), chat.getName()));
            //mTabView.addTab(mTabView.newTab().setText(chat.getName()));
        }

        WxPagerAdapter adapter = new WxPagerAdapter(getChildFragmentManager(), getLifecycle(), mFragments);
        mViewPager.setAdapter(adapter);


        new TabLayoutMediator(mTabView, mViewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(wxAuthors.get(position).getName());
            }
        }).attach();


//        mTabView.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//
//            int lastPosition = 0;
//            int beforePosition = 0;
//
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                lastPosition = tab.getPosition();
//                LogUtils.d("onTabSelected:" + tab.getPosition());
//                switchFragment(beforePosition, lastPosition, lastPosition + "");
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//                beforePosition = tab.getPosition();
//                LogUtils.d("onTabUnselected:" + tab.getPosition());
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//                LogUtils.d("onTabReselected:" + tab.getPosition());
//            }
//        });
//
//
//        mViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//
//            int beforePosition = 0;
//
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
//                LogUtils.d("onPageScrolled:" + position + " " + positionOffset + " " + positionOffsetPixels);
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                super.onPageSelected(position);
//                LogUtils.d("onPageSelected:" + position);
//                switchFragment(beforePosition, position, position + "");
//                beforePosition = position;
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//                super.onPageScrollStateChanged(state);
//            }
//        });
    }

//    private void switchFragment(int beforePosition, int lastPosition, String tag) {
//        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
//        WxAuthorDetailFragment beforeFragment = mFragments.get(beforePosition);
//        WxAuthorDetailFragment targetFragment = mFragments.get(lastPosition);//需要切换的Fragment
//        ft.hide(beforeFragment);
//        if (!targetFragment.isAdded() && null == getChildFragmentManager().findFragmentByTag(tag)) {
//            ft.add(targetFragment, tag);
//        }
//        ft.show(targetFragment);
//        ft.commit();
//    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wechat;
    }

    /**
     * 加载公众号账号
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
                        initViewPagerAndTabLayout(weChatList);
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call call) {

            }
        });
    }

}
