package com.zzh.aiwanandroid.utils;

import android.content.Context;
import android.widget.Toast;

import com.zzh.aiwanandroid.config.MyApplication;

public class CommonUtils {


    /**
     * 显示Toast
     *
     * @param string
     */
    public static void ToastShow(String string) {
        Toast.makeText(MyApplication.getContext(), string, Toast.LENGTH_SHORT).show();
    }
}
