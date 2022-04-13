package com.zzh.aiwanandroid.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class LazyBaseFragment extends Fragment {

    protected View rootView;

    /**
     * 是否第一场开启网络加载
     */
    private boolean isFirstLoad;

    private boolean beforeVisibleState = false;

    /**
     * 当前Fragment是否处于可见状态标志
     */
    private boolean isFragmentVisible;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutId(), container, false);
        initView(rootView);
        isFirstLoad = true;
        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        initEventAndData();
        if (!beforeVisibleState && isResumed()){
            dispatchHintState(true);
        }
        beforeVisibleState = true;
    }

    private void dispatchHintState(boolean state){
        if (beforeVisibleState == state){
            return;
        }
        beforeVisibleState = state;
        if(state){
            startLoadData();
        }else{
            stopLoadData();
        }
    }

    protected void startLoadData(){};
    protected void stopLoadData(){};

    @Override
    public void onPause() {
        super.onPause();
        if(beforeVisibleState && !isResumed()){
            dispatchHintState(false);
        }
        beforeVisibleState = false;
    }

    /**
     * fragment 显示在前台时调用
     */
    protected abstract void onVisible();

    /**
     * fragment 隐藏时调用
     */
    protected abstract void onInVisible();


    protected abstract void initEventAndData();

    protected void initView(View view) {
    }

    protected abstract int getLayoutId();
}
