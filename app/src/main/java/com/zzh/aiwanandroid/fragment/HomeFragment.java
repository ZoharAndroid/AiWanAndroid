package com.zzh.aiwanandroid.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.zzh.aiwanandroid.Constants;
import com.zzh.aiwanandroid.R;
import com.zzh.aiwanandroid.base.BaseFragment;
import com.zzh.aiwanandroid.config.CallbackListener;
import com.zzh.aiwanandroid.config.HttpConfig;
import com.zzh.aiwanandroid.utils.HttpUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class HomeFragment extends BaseFragment {

    private RecyclerView mHomeRecyclerView;

    /**
     * 获取HomeFragment实例
     *
     * @param param1
     * @param param2
     * @return
     */
    public static Fragment getInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.param1, param1);
        bundle.putString(Constants.param2, param2);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // 获取传递过来的参数

    }


    @Override
    protected void initView(View view) {
        super.initView(view);
        mHomeRecyclerView = view.findViewById(R.id.home_pager_recycler_view);
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }


}
