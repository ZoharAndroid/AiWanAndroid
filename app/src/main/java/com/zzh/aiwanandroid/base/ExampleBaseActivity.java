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

public class ExampleBaseActivity extends BaseActivity {

    public static final int HOME_ICON_IS_BACK = 1;
    public static final int HOME_ICON_IS_X = 2;

    private TextView mExampleTitle;
    private Toolbar mExampleToolbar;
    private LinearLayout mExampleSearchContainer;
    private EditText mExampleSearchEdit;
    private ImageView mExampleSearchDismiss;
    private FrameLayout mExampleFrameLayout;

    @Override
    protected void initView() {
        super.initView();
        mExampleTitle = findViewById(R.id.example_content_title);
        mExampleToolbar = findViewById(R.id.example_content_toolbar);
        mExampleSearchContainer = findViewById(R.id.search_container);
        mExampleSearchEdit = findViewById(R.id.search_edit_text);
        mExampleSearchDismiss = findViewById(R.id.search_image_dismiss);
        mExampleFrameLayout = findViewById(R.id.example_content_container_frame);

        // 设置toolbar
        setSupportActionBar(mExampleToolbar);
        setToolbarHomeIconAndEnabled(HOME_ICON_IS_BACK);

    }

    protected void setToolbarHomeIconAndEnabled(int homeIconType) {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            // 显示图标
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            switch (homeIconType) {
                case HOME_ICON_IS_BACK:
                    supportActionBar.setHomeAsUpIndicator(R.mipmap.icon_back);
                    break;
                case HOME_ICON_IS_X:
                    supportActionBar.setHomeAsUpIndicator(R.mipmap.icon_x);
            }

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
     * 设置FrameLayout显示状态，默认是false，不显示该布局
     *
     * @param isShow
     */
    protected void setExampleFrameLayoutDisplay(boolean isShow) {
        if (isShow) {
            mExampleFrameLayout.setVisibility(View.VISIBLE);
        } else {
            mExampleFrameLayout.setVisibility(View.GONE);
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
        return R.layout.example_activity_layout;
    }


    /**
     * 给Content添加Fragment
     *
     * @param fragment
     */
    public void setContentFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.example_content_container_frame, fragment).commit();
    }
}
