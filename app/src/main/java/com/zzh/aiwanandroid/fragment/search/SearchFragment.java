package com.zzh.aiwanandroid.fragment.search;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import com.zzh.aiwanandroid.Constants;
import com.zzh.aiwanandroid.R;
import com.zzh.aiwanandroid.activity.ResultActivity;
import com.zzh.aiwanandroid.activity.SearchActivity;
import com.zzh.aiwanandroid.base.BaseFragment;
import com.zzh.aiwanandroid.bean.ArticlePages;
import com.zzh.aiwanandroid.bean.HotKeyBean;
import com.zzh.aiwanandroid.config.CallbackListener;
import com.zzh.aiwanandroid.config.HttpConfig;
import com.zzh.aiwanandroid.utils.HttpUtils;
import com.zzh.aiwanandroid.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import okhttp3.Call;

public class SearchFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private TextView mClearAllTextView;
    private TagFlowLayout mTagFlow;
    private TextView mSearchShow;
    private List<HotKeyBean.HotKey> mHotKeyList;
    private TagAdapter<HotKeyBean.HotKey> tagAdapter;

    private String mSearchContent;

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

    }

    @Override
    protected void initEventAndData() {
        mHotKeyList = new ArrayList<>();

        int[] colors = new int[]{getResources().getColor(R.color.text_color),
                getResources().getColor(R.color.text_color_1),
                getResources().getColor(R.color.text_color_2),
                getResources().getColor(R.color.text_color_3),
                getResources().getColor(R.color.text_color_4),
                getResources().getColor(R.color.text_color_5),
                getResources().getColor(R.color.text_color_6)};
        // 获取热搜关键词
        HttpUtils.sendHttpRequest(HttpConfig.HOT_KEY_URL, new CallbackListener() {
            @Override
            public void onSuccess(String response) {
                // 获取数据
                HotKeyBean hotKey = HttpUtils.parseJson(response, HotKeyBean.class);
                mHotKeyList.addAll(hotKey.getData());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tagAdapter.notifyDataChanged();
                    }
                });
            }

            @Override
            public void onFailure(Call call) {

            }
        });

        tagAdapter = new TagAdapter(mHotKeyList) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TextView tv = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag, mTagFlow, false);
                tv.setText(mHotKeyList.get(position).getName());
                int index = new Random().nextInt(colors.length);
                tv.setTextColor(colors[index]);
                return tv;
            }
        };
        mTagFlow.setAdapter(tagAdapter);

        mTagFlow.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                mSearchContent = mHotKeyList.get(position).getName();
                // 发送到结果展示Activity
                Intent intent = new Intent(view.getContext(), ResultActivity.class);
                intent.putExtra(Constants.intent_extra_title, mSearchContent);
                startActivity(intent);
                return true;
            }
        });
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }
}
