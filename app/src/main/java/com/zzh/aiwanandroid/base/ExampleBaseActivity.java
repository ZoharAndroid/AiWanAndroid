package com.zzh.aiwanandroid.base;

import android.icu.util.MeasureUnit;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    private LinearLayout mExampleSearchContainer;
    private EditText mExampleSearchEdit;
    private ImageView mExampleSearchDismiss;

    @Override
    protected void initView() {
        super.initView();

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


    protected EditText getSearchEditText(){
        return mExampleSearchEdit;
    }

    protected ImageView getSearchImageView(){
        return mExampleSearchDismiss;
    }

    /**
     * 是否显示Toolbar标题
     *
     * @param isShow
     */
    protected void showTitleTextView(boolean isShow){
        if (isShow){
            mExampleTitle.setVisibility(View.VISIBLE);
        }else{
            mExampleTitle.setVisibility(View.GONE);
        }
    }

    /**
     * 是否显示Edit
     *
     * @param isShow
     */
    protected void showEditTextView(boolean isShow){
        if (isShow){
            mExampleSearchContainer.setVisibility(View.VISIBLE);
        }else{
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
