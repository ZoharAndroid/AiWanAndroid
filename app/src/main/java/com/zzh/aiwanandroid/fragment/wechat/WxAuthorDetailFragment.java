package com.zzh.aiwanandroid.fragment.wechat;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.zzh.aiwanandroid.Constants;
import com.zzh.aiwanandroid.R;
import com.zzh.aiwanandroid.base.BaseFragment;
import com.zzh.aiwanandroid.utils.LogUtils;

public class WxAuthorDetailFragment extends BaseFragment {

    private int mWxAuthorId;
    private String mWxAuthorName;

    public static WxAuthorDetailFragment getInstance(int param1, String param2) {
        WxAuthorDetailFragment fragment = new WxAuthorDetailFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.param1, param1);
        args.putString(Constants.param2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void initEventAndData() {
        mWxAuthorId = getArguments().getInt(Constants.param1);
        mWxAuthorName = getArguments().getString(Constants.param2);

        LogUtils.d(mWxAuthorId + "---" + mWxAuthorName);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_result;
    }
}
