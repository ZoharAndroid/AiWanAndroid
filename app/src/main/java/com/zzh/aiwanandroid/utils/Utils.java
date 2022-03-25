package com.zzh.aiwanandroid.utils;

import android.content.Context;
import android.widget.Toast;

import com.zzh.aiwanandroid.config.MyApplication;

public class Utils {


    /**
     * 显示Toast
     *
     * @param context
     * @param string
     */
    public static void ToastShow(String string) {
        Toast.makeText(MyApplication.getContext(), string, Toast.LENGTH_SHORT).show();
    }
}
