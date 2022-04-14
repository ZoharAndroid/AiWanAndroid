package com.zzh.aiwanandroid.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zzh.aiwanandroid.utils.LogUtils;

public abstract class LazyBaseFragment extends Fragment {

    protected View rootView;

    /**
     * 是否第一场开启网络加载
     */
    private boolean isFirstLoad = true;

    protected abstract void onVisible();

    protected abstract void onInVisible();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutId(), container, false);
        initView(rootView);
        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        if (isFirstLoad) {
            initEventAndData();
            isFirstLoad = false;
        }else{
        onVisible();
    }}


    @Override
    public void onPause() {
        super.onPause();
        onInVisible();
    }


    protected abstract void initEventAndData();

    protected void initView(View view) {
    }

    protected abstract int getLayoutId();
}
