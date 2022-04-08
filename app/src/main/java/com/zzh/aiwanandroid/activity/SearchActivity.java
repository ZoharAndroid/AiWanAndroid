package com.zzh.aiwanandroid.activity;

import android.content.Intent;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.zzh.aiwanandroid.Constants;
import com.zzh.aiwanandroid.R;
import com.zzh.aiwanandroid.base.ExampleBaseScrollActivity;
import com.zzh.aiwanandroid.bean.ArticlePages;
import com.zzh.aiwanandroid.config.CallbackListener;
import com.zzh.aiwanandroid.config.HttpConfig;
import com.zzh.aiwanandroid.fragment.search.SearchFragment;
import com.zzh.aiwanandroid.utils.HttpUtils;

import java.util.ArrayList;

import okhttp3.Call;

/**
 * 搜索界面
 *
 */
public class SearchActivity extends ExampleBaseScrollActivity {

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
                if (TextUtils.isEmpty(mSearchContent)) {
                    // 显示消除图标
                    getSearchImageView().setVisibility(View.GONE);
                } else {
                    getSearchImageView().setVisibility(View.VISIBLE);
                }
            }
        });

        getSearchImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(mSearchContent)) {
                    getSearchEditText().setText("");
                }
            }
        });

        // 添加内容
        setContentFragment(SearchFragment.getInstance(null, null));
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
                if(TextUtils.isEmpty(mSearchContent)){
                    Toast.makeText(this, getResources().getString(R.string.input_search),Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(SearchActivity.this, ResultActivity.class);
                    intent.putExtra(Constants.intent_extra_title, mSearchContent);
                    startActivity(intent);
                }
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
