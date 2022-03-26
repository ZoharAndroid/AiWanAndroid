package com.zzh.aiwanandroid.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    /**
     * 是否第一次加载页面
     */
    private boolean isFirstLoad = true;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        initView(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstLoad) {
            initEventAndData();
            isFirstLoad = false;
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isFirstLoad = true;
    }


    protected abstract void initEventAndData();

    protected void initView(View view) {
    }

    protected abstract int getLayoutId();
}
