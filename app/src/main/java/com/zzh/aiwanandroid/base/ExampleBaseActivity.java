package com.zzh.aiwanandroid.base;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.zzh.aiwanandroid.R;
import com.zzh.aiwanandroid.fragment.OnLoadFragmentListener;

public  class ExampleBaseActivity extends BaseActivity{

    private TextView mExampleTitle;
    private Toolbar mExampleToolbar;
    private FrameLayout mExampleContainer;

    @Override
    protected void initView() {
        super.initView();

        mExampleTitle = findViewById(R.id.example_content_title);
        mExampleContainer = findViewById(R.id.example_content_container);
        mExampleToolbar = findViewById(R.id.example_content_toolbar);

        // 设置toolbar
        setSupportActionBar(mExampleToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            // 显示图标
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setHomeAsUpIndicator(R.mipmap.icon_back);
        }

    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();

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

    /**
     * 设置标题栏标题
     *
     * @param title
     */
    protected void setToolbarTitle(String title) {
        mExampleTitle.setText(title);
        mExampleToolbar.setSelected(true);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.example_activity_layout;
    }


    /**
     * 给Content添加Fragment
     *
     * @param fragment
     */
    public void setContentFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.example_content_container,fragment).commit();
    }
}
