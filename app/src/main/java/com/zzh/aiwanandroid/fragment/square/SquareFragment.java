package com.zzh.aiwanandroid.fragment.square;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zzh.aiwanandroid.Constants;
import com.zzh.aiwanandroid.R;
import com.zzh.aiwanandroid.base.BaseFragment;
import com.zzh.aiwanandroid.bean.Article;
import com.zzh.aiwanandroid.bean.ArticlePages;
import com.zzh.aiwanandroid.config.CallbackListener;
import com.zzh.aiwanandroid.config.HttpConfig;
import com.zzh.aiwanandroid.fragment.search.ResultAdapter;
import com.zzh.aiwanandroid.utils.HttpUtils;
import com.zzh.aiwanandroid.widget.onLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 广场
 */
public class SquareFragment extends BaseFragment {

    private SwipeRefreshLayout mSwipeView;
    private RecyclerView mRecyclerView;
    private List<Article> mArticle = new ArrayList<>();
    private int currentPage = 0;
    private int pageCount = 0;
    private ResultAdapter mAdapter;
    private LinearLayout mNoDataView;
    private static boolean isLoadOver;

    public static Fragment getInstance(String param1, String param2) {
        SquareFragment fragment = new SquareFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.param1, param1);
        bundle.putString(Constants.param2, param2);
        fragment.setArguments(bundle);
        return fragment;
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
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new ResultAdapter(mArticle);
        mRecyclerView.setAdapter(mAdapter);

        loadMoreArticle(currentPage);

        mRecyclerView.addOnScrollListener(new onLoadMoreListener() {
            @Override
            protected void onLoading(int countItem, int lastItem) {
                if (currentPage + 1 <= pageCount) {
                    loadMoreArticle(++currentPage);
                }
            }
        });

        mSwipeView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeView.setRefreshing(true);
                mArticle.clear();
                currentPage = 0;
                loadMoreArticle(currentPage);

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_result;
    }

    /**
     * 加载数据
     *
     * @param page
     */
    private void loadMoreArticle(int page) {
        HttpUtils.sendHttpRequest(HttpConfig.SQUARE_URL(page), new CallbackListener() {
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
