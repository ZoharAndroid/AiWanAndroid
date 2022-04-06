package com.zzh.aiwanandroid.activity;


import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // 返回上一级
                finish();
                break;
            case R.id.menu_share:
                // 分享

                break;
            case R.id.menu_collection:
                // 收藏

                break;
            case R.id.menu_explorer:
                // 用浏览器打开

                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 创建菜单
        getMenuInflater().inflate(R.menu.tool_bar_menu, menu);

        return true;
    }

}
