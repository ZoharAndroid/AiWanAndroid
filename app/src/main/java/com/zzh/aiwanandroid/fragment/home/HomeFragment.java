package com.zzh.aiwanandroid.fragment.home;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.zzh.aiwanandroid.Constants;
import com.zzh.aiwanandroid.R;
import com.zzh.aiwanandroid.base.BaseFragment;
import com.zzh.aiwanandroid.bean.ArticlePages;
import com.zzh.aiwanandroid.config.CallbackListener;
import com.zzh.aiwanandroid.config.HttpConfig;
import com.zzh.aiwanandroid.utils.CommonUtils;
import com.zzh.aiwanandroid.utils.HttpUtils;

import okhttp3.Call;

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
        /**
         * 请求首页文章数据
         */
        HttpUtils.sendHttpRequest(HttpConfig.HOME_ARTICLE_URL(0), new CallbackListener() {
            @Override
            public void onSuccess(String response) {
                Gson gson = new Gson();
                ArticlePages articlePages = gson.fromJson(response, ArticlePages.class);
            }

            @Override
            public void onFailure(Call call) {
                CommonUtils.ToastShow("网络异常！");
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }


}
