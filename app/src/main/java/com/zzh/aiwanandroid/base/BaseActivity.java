package com.zzh.aiwanandroid.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
        initEventAndData();

    }

    /**
     * 初始化事件和数据
     */
    protected  void initEventAndData(){

    };

    /**
     * 初始化布局文件
     */
    protected void initView()  {

    }


    /**
     * 获取布局文件
     *
     * @return
     */
    protected abstract int getLayoutId();
}
