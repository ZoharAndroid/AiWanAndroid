package com.zzh.aiwanandroid.fragment.search;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zhy.view.flowlayout.TagFlowLayout;
import com.zzh.aiwanandroid.Constants;
import com.zzh.aiwanandroid.R;
import com.zzh.aiwanandroid.base.BaseFragment;

public class SearchFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private TextView mClearAllTextView;
    private TagFlowLayout mTagFlow;
    private TextView mSearchShow;

    public static Fragment getInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putString(Constants.param1, param1);
        args.putString(Constants.param2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);

        mRecyclerView = view.findViewById(R.id.search_result_content);
        mClearAllTextView = view.findViewById(R.id.text_view_clear_all);
        mTagFlow = view.findViewById(R.id.tag_search_layout);
        mSearchShow = view.findViewById(R.id.text_view_search_show);
        
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
       // mRecyclerView.setAdapter();

    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }
}
