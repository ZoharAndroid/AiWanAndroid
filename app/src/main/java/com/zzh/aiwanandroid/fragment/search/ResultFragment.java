package com.zzh.aiwanandroid.fragment.search;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zzh.aiwanandroid.Constants;
import com.zzh.aiwanandroid.R;
import com.zzh.aiwanandroid.activity.ResultActivity;
import com.zzh.aiwanandroid.base.BaseFragment;
import com.zzh.aiwanandroid.bean.Article;
import com.zzh.aiwanandroid.bean.ArticlePages;
import com.zzh.aiwanandroid.config.CallbackListener;
import com.zzh.aiwanandroid.config.HttpConfig;
import com.zzh.aiwanandroid.utils.HttpUtils;
import com.zzh.aiwanandroid.widget.onLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;


public class ResultFragment extends BaseFragment {

    private static boolean isLoadOver;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeView;
    private List<Article> mArticle = new ArrayList<>();
    private ResultAdapter mAdapter;
    private int currentPage = 0;
    private int pageCount = 0;
    private String mSearchContent; // 搜索内容
    private LinearLayout mNoDataView;

    private ResultFragment() {
    }

    public static Fragment getInstance(String param1, String param2) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putString(Constants.param1, param1);
        args.putString(Constants.param2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        assert getArguments() != null;
        mSearchContent = getArguments().getString(Constants.param1);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mRecyclerView = view.findViewById(R.id.result_recycler_view);
        mSwipeView = view.findViewById(R.id.result_swipe_refresh);
        mNoDataView = view.findViewById(R.id.no_data_container);
    }


    @Override
    protected void initEventAndData() {
        mArticle.addAll(((ResultActivity) getActivity()).getArticles());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new ResultAdapter(mArticle);
        mRecyclerView.setAdapter(mAdapter);

        loadMoreActivity(currentPage);

        mRecyclerView.addOnScrollListener(new onLoadMoreListener() {
            @Override
            protected void onLoading(int countItem, int lastItem) {
                if (currentPage + 1 <= pageCount) {
                    loadMoreActivity(++currentPage);
                }
            }
        });

        mSwipeView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeView.setRefreshing(true);
                mArticle.clear();
                currentPage = 0;
                loadMoreActivity(currentPage);

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_result;
    }

    /**
     * 加载更多数据
     *
     * @param page
     */
    private void loadMoreActivity(int page) {
        // 重新发送请求
        HttpUtils.sendSearchPostRequest(HttpConfig.QUERY_URL(page), mSearchContent, new CallbackListener() {
            @Override
            public void onSuccess(String response) {
                ArticlePages articlePages = HttpUtils.parseJson(response, ArticlePages.class);
                List<Article> articles = articlePages.getData().getDatas();
                pageCount = articlePages.getData().getPageCount();
                isLoadOver = articlePages.getData().isOver();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (pageCount == 0 && articles.isEmpty()) {
                            // 如果为空数据，那么加载暂无数据界面
                            mSwipeView.setVisibility(View.GONE);
                            mNoDataView.setVisibility(View.VISIBLE);
                        } else {
                            mAdapter.getMoreArticle(articles);
                            mSwipeView.setRefreshing(false);
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call call) {

            }
        });
    }

    public static boolean getLoadIsOver() {
        return isLoadOver;
    }
}
