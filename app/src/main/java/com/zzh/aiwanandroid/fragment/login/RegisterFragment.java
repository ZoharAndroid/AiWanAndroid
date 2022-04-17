package com.zzh.aiwanandroid.fragment.login;

import android.os.Bundle;

import com.zzh.aiwanandroid.Constants;
import com.zzh.aiwanandroid.R;
import com.zzh.aiwanandroid.base.BaseFragment;

public class RegisterFragment extends BaseFragment {

    private RegisterFragment() {
    }

    public static BaseFragment getInstance(String param1, String param2) {
        BaseFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        args.putString(Constants.param1, param1);
        args.putString(Constants.param2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
    }
}
