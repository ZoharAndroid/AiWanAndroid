package com.zzh.aiwanandroid.fragment.structure;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

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
import com.zzh.aiwanandroid.fragment.search.ResultAdapter;
import com.zzh.aiwanandroid.utils.HttpUtils;
import com.zzh.aiwanandroid.utils.LogUtils;
import com.zzh.aiwanandroid.widget.onLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;


public class TreeDetailFragment extends BaseFragment {

    private TreeBean.Tree mTree;

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeView;
    private LinearLayout mNoDataView;

    private int currentPage = 0;
    private int pageCount = 0;
    private List<Article> mArticles = new ArrayList<>();
    private static boolean isLoadOver;
    private ResultAdapter mAdapter;

    public static TreeDetailFragment getInstance(TreeBean.Tree tree, String param2) {
        TreeDetailFragment fragment = new TreeDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(Constants.param1, tree);
        args.putString(Constants.param2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void initView(View view) {
        super.initView(view);
        mRecyclerView = view.findViewById(R.id.result_recycler_view);
        mSwipeView = view.findViewById(R.id.result_swipe_refresh);
        mNoDataView = view.findViewById(R.id.no_data_container);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);

    }

    @Override
    protected void initEventAndData() {

        if (getArguments() == null) {
            return;
        }
        // 获取二级接口的对象
        mTree = getArguments().getParcelable(Constants.param1);

        loadTreeArticleData(currentPage, mTree.getId());
        mAdapter = new ResultAdapter(mArticles);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnScrollListener(new onLoadMoreListener() {
            @Override
            protected void onLoading(int countItem, int lastItem) {
                if (currentPage + 1 <= pageCount) {
                    loadTreeArticleData(++currentPage, mTree.getId());
                }
            }
        });

        mSwipeView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeView.setRefreshing(true);
                mArticles.clear();
                currentPage = 0;
                loadTreeArticleData(++currentPage, mTree.getId());
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_result;
    }

    private void loadTreeArticleData(int page, int id) {
        HttpUtils.sendHttpRequest(HttpConfig.Tree_Article_URL(page, id), new CallbackListener() {
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
