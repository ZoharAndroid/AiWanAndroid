package com.zzh.aiwanandroid.fragment.structure;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zzh.aiwanandroid.Constants;
import com.zzh.aiwanandroid.R;
import com.zzh.aiwanandroid.base.BaseFragment;
import com.zzh.aiwanandroid.bean.TreeBean;
import com.zzh.aiwanandroid.config.CallbackListener;
import com.zzh.aiwanandroid.config.HttpConfig;
import com.zzh.aiwanandroid.utils.HttpUtils;
import com.zzh.aiwanandroid.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class SystemFragment extends BaseFragment {

    private SwipeRefreshLayout mRefreshView;
    private RecyclerView mRecyclerView;

    private List<TreeBean.Tree> mTreesLists = new ArrayList<>();

    private SystemRecyclerAdapter mAdapter;

    private SystemFragment(){}

    public static SystemFragment getInstance(String param1, String param2) {
        SystemFragment fragment = new SystemFragment();
        Bundle args = new Bundle();
        args.putString(Constants.param1, param1);
        args.putString(Constants.param2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mRecyclerView = view.findViewById(R.id.system_recycler_view);
        mRefreshView = view.findViewById(R.id.system_swipe_refresh);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new SystemRecyclerAdapter(getActivity(),mTreesLists);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initEventAndData() {
        loadSystemData(HttpConfig.STRUCTURE_URL);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_system;
    }

    /**
     * 加载体系结构数据
     *
     * @param url
     */
    private void loadSystemData(String url){
        HttpUtils.sendHttpRequest(url, new CallbackListener() {
            @Override
            public void onSuccess(String response) {
                TreeBean treeBean = HttpUtils.parseJson(response, TreeBean.class);
                List<TreeBean.Tree> topTrees = treeBean.getData();
               // mTreesLists.addAll(treeBean.getData());
//                getActivity().runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mRecyclerView.setAdapter(new SystemRecyclerAdapter(getActivity(),mTreesLists));
//                    }
//                });
                // 获取数据，将将输出传输到Adapter去处理
                mAdapter.getMoreData(topTrees);
            }

            @Override
            public void onFailure(Call call) {

            }
        });
    }


}
