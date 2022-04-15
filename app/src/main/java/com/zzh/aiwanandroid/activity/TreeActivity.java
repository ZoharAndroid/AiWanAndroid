package com.zzh.aiwanandroid.activity;

import android.text.TextUtils;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.zzh.aiwanandroid.Constants;
import com.zzh.aiwanandroid.base.ExampleBaseActivity;
import com.zzh.aiwanandroid.bean.TreeBean;
import com.zzh.aiwanandroid.fragment.structure.TreeFragment;

import java.util.List;

public class TreeActivity extends ExampleBaseActivity {

    private String mTopTreeTitle;
    private List<TreeBean.Tree> mTreeList;
    private int mTagClickPosition;

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initEventAndData() {
        super.initEventAndData();
        mTopTreeTitle = getIntent().getStringExtra(Constants.intent_extra_title);
        mTreeList = getIntent().getParcelableArrayListExtra(Constants.intent_extra_trees);
        mTagClickPosition = getIntent().getIntExtra(Constants.intent_extra_position, 0);

        if (!TextUtils.isEmpty(mTopTreeTitle)) {
            setToolbarTitle(mTopTreeTitle);
        }
        setContentFragment(TreeFragment.getInstance(mTreeList, mTagClickPosition));
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
