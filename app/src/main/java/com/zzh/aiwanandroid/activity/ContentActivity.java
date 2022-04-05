package com.zzh.aiwanandroid.activity;


import androidx.fragment.app.Fragment;

import com.zzh.aiwanandroid.Constants;
import com.zzh.aiwanandroid.R;
import com.zzh.aiwanandroid.base.BaseActivity;
import com.zzh.aiwanandroid.base.ExampleBaseActivity;
import com.zzh.aiwanandroid.bean.Article;
import com.zzh.aiwanandroid.fragment.ContentFragment;
import com.zzh.aiwanandroid.fragment.OnLoadFragmentListener;
import com.zzh.aiwanandroid.utils.LogUtils;

public class ContentActivity extends ExampleBaseActivity {

    private String mArticleURL;
    private String mArticleTitle;


    @Override
    protected void initView() {
        super.initView();

    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        mArticleURL = getIntent().getStringExtra(Constants.intent_extra_url);
        mArticleTitle = getIntent().getStringExtra(Constants.intent_extra_title);
        setToolbarTitle(mArticleTitle);
        setContentFragment(ContentFragment.getInstance(mArticleURL, null));

    }

}
