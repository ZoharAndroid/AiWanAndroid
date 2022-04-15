package com.zzh.aiwanandroid.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.zzh.aiwanandroid.R;
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

    /**
     * 获取颜色集
     *
     * @param activity
     * @return
     */
    public static int[] getColors(Activity activity){
        int[] colors = new int[]{activity.getResources().getColor(R.color.text_color),
                activity.getResources().getColor(R.color.text_color_1),
                activity.getResources().getColor(R.color.text_color_2),
                activity.getResources().getColor(R.color.text_color_3),
                activity.getResources().getColor(R.color.text_color_4),
                activity.getResources().getColor(R.color.text_color_5),
                activity.getResources().getColor(R.color.text_color_6)};
        return colors;
    }
}
