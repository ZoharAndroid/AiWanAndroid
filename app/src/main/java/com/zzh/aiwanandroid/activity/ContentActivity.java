package com.zzh.aiwanandroid.activity;


import androidx.fragment.app.Fragment;

import com.zzh.aiwanandroid.Constants;
import com.zzh.aiwanandroid.base.BaseActivity;
import com.zzh.aiwanandroid.base.ExampleBaseActivity;
import com.zzh.aiwanandroid.bean.Article;
import com.zzh.aiwanandroid.fragment.ContentFragment;
import com.zzh.aiwanandroid.fragment.OnLoadFragmentListener;
import com.zzh.aiwanandroid.utils.LogUtils;

public class ContentActivity extends ExampleBaseActivity {

    private Article mArticle;


    @Override
    protected void initView() {
        super.initView();

    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        mArticle = (Article) getIntent().getParcelableExtra(Constants.intent_extra);
        setToolbarTitle(mArticle.getTitle());
        setContentFragment(ContentFragment.getInstance(mArticle.getLink(), null));
    }

}