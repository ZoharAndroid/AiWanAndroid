package com.zzh.aiwanandroid.fragment.structure;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zzh.aiwanandroid.Constants;
import com.zzh.aiwanandroid.R;
import com.zzh.aiwanandroid.base.BaseFragment;
import com.zzh.aiwanandroid.bean.NavigationBean;
import com.zzh.aiwanandroid.config.CallbackListener;
import com.zzh.aiwanandroid.config.HttpConfig;
import com.zzh.aiwanandroid.utils.HttpUtils;
import com.zzh.aiwanandroid.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.QTabView;
import q.rorbin.verticaltablayout.widget.TabView;

public class NavigationFragment extends BaseFragment {

    private VerticalTabLayout mVerticalTabView;
    private RecyclerView mRecyclerView;

    private List<NavigationBean.Navigation> mNavigations;

    private NavigationFragment() {
    }

    public static NavigationFragment getInstance(String param1, String param2) {
        NavigationFragment fragment = new NavigationFragment();
        Bundle args = new Bundle();
        args.putString(Constants.param1, param1);
        args.putString(Constants.param2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void initView(View view) {
        super.initView(view);
        mVerticalTabView = view.findViewById(R.id.vertical_tab_layout_navigation);
        mRecyclerView = view.findViewById(R.id.recycler_view_navigation);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);

    }

    @Override
    protected void initEventAndData() {
        loadNavigationData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_naivigation;
    }


    private void loadNavigationData() {
        HttpUtils.sendHttpRequest(HttpConfig.NAVIGATION_URL(), new CallbackListener() {
            @Override
            public void onSuccess(String response) {
                NavigationBean navigationBean = HttpUtils.parseJson(response, NavigationBean.class);
                mNavigations = navigationBean.getData();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (NavigationBean.Navigation navigation : mNavigations) {
                            // 设置垂直TayLayout
                            mVerticalTabView.addTab(new QTabView(getContext()).setTitle(new QTabView.TabTitle.Builder().setContent(navigation.getName())
                                    .setTextColor(getActivity().getResources().getColor(R.color.purple_700), getActivity().getResources().getColor(R.color.tab_text_color))
                                    .build()));
                        }

                        NavigationRecyclerAdapter adapter = new NavigationRecyclerAdapter(getActivity(),mNavigations);

                        // 设置滚动内容
                        mRecyclerView.setAdapter(adapter);

                        // 关联TayLayout和滚动内容
                        mVerticalTabView.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
                            @Override
                            public void onTabSelected(TabView tab, int position) {
                                    LogUtils.d("onTabSelected" + position);
                            }

                            @Override
                            public void onTabReselected(TabView tab, int position) {
                                LogUtils.d("onTabReselected" + position);
                            }
                        });
                    }
                });
            }

            @Override
            public void onFailure(Call call) {

            }
        });
    }

}
