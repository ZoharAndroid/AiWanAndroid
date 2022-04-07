package com.zzh.aiwanandroid.base;

import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import com.zzh.aiwanandroid.R;

public class ExampleBaseScrollActivity extends BaseActivity {

    private TextView mExampleTitle;
    private Toolbar mExampleToolbar;
    private LinearLayout mExampleSearchContainer;
    private EditText mExampleSearchEdit;
    private ImageView mExampleSearchDismiss;
    private NestedScrollView mExampleNestScroll;

    @Override
    protected void initView() {
        super.initView();
        mExampleNestScroll = findViewById(R.id.example_content_container);
        mExampleTitle = findViewById(R.id.example_content_title);
        mExampleToolbar = findViewById(R.id.example_content_toolbar);
        mExampleSearchContainer = findViewById(R.id.search_container);
        mExampleSearchEdit = findViewById(R.id.search_edit_text);
        mExampleSearchDismiss = findViewById(R.id.search_image_dismiss);

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


    /**
     * 获取ExampleBase Toolbar中的编辑框控件
     *
     * @return
     */
    protected EditText getSearchEditText() {
        return mExampleSearchEdit;
    }

    /**
     * 获取Toolbar中显示的删除图标控件
     *
     * @return
     */
    protected ImageView getSearchImageView() {
        return mExampleSearchDismiss;
    }


    /**
     * 设置NestScrollView显示状态，默认为显示状态
     *
     * @param isShow
     */
    protected void setExampleNetScrollViewDisplay(boolean isShow) {
        if (isShow) {
            mExampleNestScroll.setVisibility(View.VISIBLE);
        } else {
            mExampleNestScroll.setVisibility(View.GONE);
        }
    }


    /**
     * 是否显示Toolbar标题
     *
     * @param isShow
     */
    protected void showTitleTextView(boolean isShow) {
        if (isShow) {
            mExampleTitle.setVisibility(View.VISIBLE);
        } else {
            mExampleTitle.setVisibility(View.GONE);
        }
    }

    /**
     * 是否显示Edit
     *
     * @param isShow
     */
    protected void showEditTextView(boolean isShow) {
        if (isShow) {
            mExampleSearchContainer.setVisibility(View.VISIBLE);
        } else {
            mExampleSearchContainer.setVisibility(View.GONE);
        }

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
        return R.layout.example_scroll_activity_layout;
    }


    /**
     * 给Content添加Fragment
     *
     * @param fragment
     */
    public void setContentFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.example_content_container, fragment).commit();


    }

}
