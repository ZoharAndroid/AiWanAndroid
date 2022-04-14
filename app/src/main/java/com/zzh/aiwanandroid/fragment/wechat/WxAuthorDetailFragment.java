package com.zzh.aiwanandroid.fragment.wechat;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zzh.aiwanandroid.Constants;
import com.zzh.aiwanandroid.R;
import com.zzh.aiwanandroid.base.BaseFragment;
import com.zzh.aiwanandroid.base.LazyBaseFragment;
import com.zzh.aiwanandroid.bean.Article;
import com.zzh.aiwanandroid.bean.ArticlePages;
import com.zzh.aiwanandroid.config.CallbackListener;
import com.zzh.aiwanandroid.config.HttpConfig;
import com.zzh.aiwanandroid.utils.HttpUtils;
import com.zzh.aiwanandroid.utils.LogUtils;
import com.zzh.aiwanandroid.widget.CustomDialog;
import com.zzh.aiwanandroid.widget.onLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class WxAuthorDetailFragment extends BaseFragment {

    //
    // 判断是否加载过，如果加载过说明

    private int mWxAuthorId; //wx id
    private String mWxAuthorName; // wx name

    private static boolean isLoadOver;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeView;
    private List<Article> mArticle = new ArrayList<>();
    private WxAuthorDetailAdapter mAdapter;
    private int currentPage = 0;
    private int pageCount = 0;
    private List<Article> articles;

    private CustomDialog customDialog;

    private LinearLayout mNoDataView;

    public static WxAuthorDetailFragment getInstance(int param1, String param2) {
        WxAuthorDetailFragment fragment = new WxAuthorDetailFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.param1, param1);
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
    }



    @Override
    protected void initEventAndData() {
        mWxAuthorId = getArguments().getInt(Constants.param1);
        mWxAuthorName = getArguments().getString(Constants.param2);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new WxAuthorDetailAdapter(mArticle);
        mRecyclerView.setAdapter(mAdapter);


        mRecyclerView.addOnScrollListener(new onLoadMoreListener() {
            @Override
            protected void onLoading(int countItem, int lastItem) {
                if (currentPage + 1 <= pageCount) {
                    loadWechatArticle(mWxAuthorId, ++currentPage);
                }
            }
        });

        mSwipeView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeView.setRefreshing(true);
                mArticle.clear();
                currentPage = 0;
                loadWechatArticle(mWxAuthorId, currentPage);

            }
        });

        customDialog = new CustomDialog(getContext());
        loadWechatArticle(mWxAuthorId, currentPage);
    }



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_result;
    }


    /**
     * 加载公众号作者文章内容
     *
     * @param userId
     * @param currentPage
     */
    private void loadWechatArticle(int userId, int currentPage) {
       // customDialog.show();
        HttpUtils.sendHttpRequest(HttpConfig.QUERY_WECHAT_ARTICLE_URL(userId, currentPage), new CallbackListener() {
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

//                        if (customDialog != null) {
//                            customDialog.dismiss();
//                        }
                    }
                });
            }

            @Override
            public void onFailure(Call call) {

            }
        });
    }

    /**
     * 是否加载完毕状态
     *
     * @return
     */
    public static boolean getLoadIsOver() {
        return isLoadOver;
    }
}
