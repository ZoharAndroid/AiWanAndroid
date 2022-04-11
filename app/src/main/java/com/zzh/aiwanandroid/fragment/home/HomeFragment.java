package com.zzh.aiwanandroid.fragment.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.zzh.aiwanandroid.Constants;
import com.zzh.aiwanandroid.R;
import com.zzh.aiwanandroid.base.BaseFragment;
import com.zzh.aiwanandroid.bean.Article;
import com.zzh.aiwanandroid.bean.ArticlePages;
import com.zzh.aiwanandroid.bean.ArticlePagesData;
import com.zzh.aiwanandroid.bean.BannerRoot;
import com.zzh.aiwanandroid.bean.TopArticle;
import com.zzh.aiwanandroid.config.CallbackListener;
import com.zzh.aiwanandroid.config.HttpConfig;
import com.zzh.aiwanandroid.utils.CommonUtils;
import com.zzh.aiwanandroid.utils.HttpUtils;
import com.zzh.aiwanandroid.utils.LogUtils;
import com.zzh.aiwanandroid.widget.onLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class HomeFragment extends BaseFragment {

    private RecyclerView mHomeRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;

    private HomeRecyclerViewAdapter adapter;

    private ArticlePages mArticlePages;
    private List<Article> articleList;

    private static final int SUCCESS_STATUS = 1;

    private int currentPage = 0; // 当前加载的页数

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
        mRefreshLayout = view.findViewById(R.id.swipe_refresh);
    }


    @Override
    protected void initEventAndData() {
        articleList = new ArrayList<>();
        adapter = new HomeRecyclerViewAdapter(articleList);
        mHomeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mHomeRecyclerView.setAdapter(adapter);


        // 获取首页banner数据
        loadBannerData();
        //加载置顶文章数据
        loadTopArticleData();
        //加载文章数据
        loadArticleData(currentPage);

        // 下拉刷新
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 开始刷新
                mRefreshLayout.setRefreshing(true);
                articleList.clear();
                currentPage = 0;
                // 加载置顶文章
                loadTopArticleData();
                // 加载普通文章
                loadArticleData(currentPage);
                updateUI();
                //刷新结束
                mRefreshLayout.setRefreshing(false);
            }
        });

        mHomeRecyclerView.addOnScrollListener(new onLoadMoreListener() {
            @Override
            protected void onLoading(int countItem, int lastItem) {
                // 加载更多数据
                if (currentPage + 1 <= mArticlePages.getData().getPageCount()) {
                    // 如果当前页面小于等于总页数，直接加载数据显示
                    loadArticleData(++currentPage);
                }
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }


    /**
     * 加载首页置顶文章数据
     */
    private void loadTopArticleData() {
        HttpUtils.sendHttpRequest(HttpConfig.HOME_TOP_URL, new CallbackListener() {
            @Override
            public void onSuccess(String response) {
                Gson gson = new Gson();
                TopArticle topArticle = gson.fromJson(response, TopArticle.class);
                // 传递数据
                adapter.getTopArticle(topArticle.getData());
            }

            @Override
            public void onFailure(Call call) {

            }
        });
    }

    /**
     * 加载首页banner轮播图片数据
     */
    private void loadBannerData() {
        HttpUtils.sendHttpRequest(HttpConfig.HOME_BANNER_URL, new CallbackListener() {
            @Override
            public void onSuccess(String response) {
                Gson gson = new Gson();
                BannerRoot bannerRoot = gson.fromJson(response, BannerRoot.class);
                // 发送消息更新Banner
                adapter.onLoadBannerData(bannerRoot);
            }

            @Override
            public void onFailure(Call call) {

            }
        });
    }

    /**
     * 加载文章数据数据
     */
    private void loadArticleData(int page) {
        // 重新发送请求
        HttpUtils.sendHttpRequest(HttpConfig.HOME_ARTICLE_URL(page), new CallbackListener() {
            @Override
            public void onSuccess(String response) {
                Gson gson = new Gson();
                mArticlePages = gson.fromJson(response, ArticlePages.class);
                adapter.getArticle(mArticlePages.getData().getDatas());
                //articleList.addAll(mArticlePages.getData().getDatas());
                adapter.setIsLastData(mArticlePages.getData().isOver());
                updateUI();
            }

            @Override
            public void onFailure(Call call) {

            }
        });
    }


    private void updateUI() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }


}

