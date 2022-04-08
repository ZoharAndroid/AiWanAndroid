package com.zzh.aiwanandroid.activity;

import android.text.Html;
import android.text.Spanned;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.zzh.aiwanandroid.Constants;
import com.zzh.aiwanandroid.R;
import com.zzh.aiwanandroid.base.ExampleBaseActivity;
import com.zzh.aiwanandroid.bean.Article;
import com.zzh.aiwanandroid.fragment.search.ResultFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索结果界面
 *
 */
public class ResultActivity extends ExampleBaseActivity {

    private String mSearchContent;
    private List<Article> mArticles = new ArrayList<>();

    @Override
    protected void initView() {
        super.initView();

    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        mSearchContent = getIntent().getStringExtra(Constants.intent_extra_title);


        // 设置标题
        setToolbarTitle(mSearchContent);

        setContentFragment(ResultFragment.getInstance(mSearchContent, null));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 创建菜单
        getMenuInflater().inflate(R.menu.tool_bar_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        MenuItem shareItem = menu.findItem(R.id.menu_share);
        MenuItem collectItem = menu.findItem(R.id.menu_collection);
        MenuItem explorerItem = menu.findItem(R.id.menu_explorer);

        searchItem.setVisible(false);
        shareItem.setVisible(false);
        collectItem.setVisible(false);
        explorerItem.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    public List<Article> getArticles() {
        return mArticles;
    }
}
