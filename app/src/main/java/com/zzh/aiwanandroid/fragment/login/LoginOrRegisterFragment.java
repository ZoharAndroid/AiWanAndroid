package com.zzh.aiwanandroid.fragment.login;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.zzh.aiwanandroid.Constants;
import com.zzh.aiwanandroid.R;
import com.zzh.aiwanandroid.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class LoginOrRegisterFragment extends BaseFragment {

    private ViewPager2 mViewPager;
    private FragmentStateAdapter stateAdapter;

    private LoginOrRegisterFragment() {
    }

    public static LoginOrRegisterFragment getInstance(String param1, String param2) {
        LoginOrRegisterFragment fragment = new LoginOrRegisterFragment();
        Bundle args = new Bundle();
        args.putString(Constants.param1, param1);
        args.putString(Constants.param2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mViewPager = view.findViewById(R.id.login_or_register_view_pager);


        List<BaseFragment> mFragments = new ArrayList<>();
        mFragments.add(LoginFragment.getInstance(null, null));
        mFragments.add(RegisterFragment.getInstance(null, null));

        stateAdapter = new FragmentStateAdapter(getChildFragmentManager(), getLifecycle()) {
             @NonNull
             @Override
             public Fragment createFragment(int position) {
                 return mFragments.get(position);
             }

             @Override
             public int getItemCount() {
                 return mFragments.size();
             }
         };

        mViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

        mViewPager.setAdapter(stateAdapter);
    }

    public void goNextFragment(){
        int pageCurrentPosition = mViewPager.getCurrentItem();
        if (pageCurrentPosition  == 0) {
            mViewPager.setCurrentItem(pageCurrentPosition + 1,true);
        }else{
            mViewPager.setCurrentItem(pageCurrentPosition - 1,true);
        }
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login_or_register;
    }
}
