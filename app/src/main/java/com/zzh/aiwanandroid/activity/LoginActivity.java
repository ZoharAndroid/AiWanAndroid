package com.zzh.aiwanandroid.activity;

import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.zzh.aiwanandroid.base.ExampleBaseActivity;
import com.zzh.aiwanandroid.fragment.login.LoginOrRegisterFragment;


/**
 * 登录界面
 */
public class LoginActivity extends ExampleBaseActivity {


    @Override
    protected void initView() {
        super.initView();
        // 设置标题栏中的返回键为X
        setToolbarHomeIconAndEnabled(ExampleBaseActivity.HOME_ICON_IS_X);
        setContentFragment(LoginOrRegisterFragment.getInstance(null, null));
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
