package com.zzh.aiwanandroid.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;

import com.zzh.aiwanandroid.R;
import com.zzh.aiwanandroid.base.ExampleBaseActivity;
import com.zzh.aiwanandroid.fragment.search.SearchFragment;
import com.zzh.aiwanandroid.utils.LogUtils;

public class SearchActivity extends ExampleBaseActivity {

    private String mSearchContent;

    @Override
    protected void initView() {
        super.initView();

        showTitleTextView(false);
        showEditTextView(true);
    }


    @Override
    protected void initEventAndData() {
        super.initEventAndData();

        getSearchEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mSearchContent = s.toString().trim();
                if (TextUtils.isEmpty(mSearchContent)){
                    // 显示消除图标
                    getSearchImageView().setVisibility(View.GONE);
                }else{
                    getSearchImageView().setVisibility(View.VISIBLE);
                }
            }
        });

        getSearchImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(mSearchContent)){
                    getSearchEditText().setText("");
                }
            }
        });

        // 添加内容
        setContentFragment(SearchFragment.getInstance(null,null));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // 返回上一级
                finish();
                break;
            case R.id.menu_search:
                // 搜索
                LogUtils.d(mSearchContent);
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 创建菜单
        getMenuInflater().inflate(R.menu.tool_bar_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_search);
        MenuItem shareItem = menu.findItem(R.id.menu_share);
        MenuItem collectItem = menu.findItem(R.id.menu_collection);
        MenuItem explorerItem = menu.findItem(R.id.menu_explorer);

        searchItem.setVisible(true);
        shareItem.setVisible(false);
        collectItem.setVisible(false);
        explorerItem.setVisible(false);

        return true;
    }


}
