package com.zzh.aiwanandroid.fragment.project;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

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

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class ProjectDetailFragment extends BaseFragment {

    private int mId;

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mRefreshLayout;

    private int mCurrentPage = 0;
    private List<Article> mArticles = new ArrayList<>();
    private ProjectDetailAdapter adapter;

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

        loadProjectDetailData(mCurrentPage);

        adapter = new ProjectDetailAdapter(getActivity(),mArticles);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project_detail;
    }

    private void loadProjectDetailData(int page) {
        HttpUtils.sendHttpRequest(HttpConfig.PROJECT_DETAIL_URL(page, mId), new CallbackListener() {
            @Override
            public void onSuccess(String response) {
                int positionStart = mArticles.size();
                ArticlePages articlePages = HttpUtils.parseJson(response, ArticlePages.class);
                List<Article> moreArticleDatas = articlePages.getData().getDatas();
                mArticles.addAll(moreArticleDatas);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 更新数据源
                        adapter.notifyItemRangeChanged(positionStart, moreArticleDatas.size());
                    }
                });
            }

            @Override
            public void onFailure(Call call) {

            }
        });
    }

}
