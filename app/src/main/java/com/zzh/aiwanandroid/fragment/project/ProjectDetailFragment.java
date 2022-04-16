package com.zzh.aiwanandroid.fragment.project;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zzh.aiwanandroid.Constants;
import com.zzh.aiwanandroid.R;
import com.zzh.aiwanandroid.base.BaseFragment;
import com.zzh.aiwanandroid.bean.Article;
import com.zzh.aiwanandroid.bean.ArticlePages;
import com.zzh.aiwanandroid.bean.TreeBean;
import com.zzh.aiwanandroid.config.CallbackListener;
import com.zzh.aiwanandroid.config.HttpConfig;
import com.zzh.aiwanandroid.utils.HttpUtils;
import com.zzh.aiwanandroid.widget.onLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class ProjectDetailFragment extends BaseFragment implements LoadDataListener {

    private int mId;

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;

    private int mCurrentPage = 1;
    private int mPageCount;
    private List<Article> mArticles = new ArrayList<>();
    private ProjectDetailAdapter adapter;
    private boolean isOver = false;

    private ProjectDetailFragment() {
    }


    public static ProjectDetailFragment getInstance(int param1, String param2) {
        ProjectDetailFragment fragment = new ProjectDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.param1, param1);
        bundle.putString(Constants.param2, param2);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);

        mRecyclerView = view.findViewById(R.id.recycler_view_project_detail);
        mRefreshLayout = view.findViewById(R.id.refresh_layout_project_detail);
    }

    @Override
    protected void initEventAndData() {
        if (getArguments() == null) {
            return;
        }

        mId = getArguments().getInt(Constants.param1);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);

        LoadDataListener listener = this;

        loadProjectDetailData(mCurrentPage, listener);

        adapter = new ProjectDetailAdapter(getActivity(), mArticles);
        mRecyclerView.setAdapter(adapter);

        // 下拉刷新
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 开始刷新
                mCurrentPage = 1; // 注意从1开始
                mRefreshLayout.setRefreshing(true);
                mArticles.clear();
                // 重新加载数据
                loadProjectDetailData(mCurrentPage, listener);
            }
        });

        // 上拉加载
        mRecyclerView.addOnScrollListener(new onLoadMoreListener() {
            @Override
            protected void onLoading(int countItem, int lastItem) {
                if (mCurrentPage < mPageCount) {
                    loadProjectDetailData(++mCurrentPage, listener);
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project_detail;
    }

    private void loadProjectDetailData(int page, LoadDataListener listener) {
        HttpUtils.sendHttpRequest(HttpConfig.PROJECT_DETAIL_URL(page, mId), new CallbackListener() {
            @Override
            public void onSuccess(String response) {
                listener.onLoadFinishSuccess(response);
            }

            @Override
            public void onFailure(Call call) {
                listener.onLoadFinishFailed();
            }
        });
    }

    @Override
    public void onLoadFinishSuccess(String response) {
        int positionStart = mArticles.size();
        ArticlePages articlePages = HttpUtils.parseJson(response, ArticlePages.class);
        List<Article> moreArticleDatas = articlePages.getData().getDatas();
        mArticles.addAll(moreArticleDatas);
        mPageCount = articlePages.getData().getPageCount();
        adapter.setIsOver(mCurrentPage == mPageCount);
        //adapter.isOver(articlePages.getData().isOver()); // 判断请求的是否是最后页数据
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // 更新数据源
                adapter.notifyItemRangeChanged(positionStart, moreArticleDatas.size());
                // 关闭刷新
                mRefreshLayout.setRefreshing(false);
            }
        });

    }

    @Override
    public void onLoadFinishFailed() {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // 关闭刷新
                mRefreshLayout.setRefreshing(false);
            }
        });

    }
}
