package com.zzh.aiwanandroid.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.Menu;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zzh.aiwanandroid.R;
import com.zzh.aiwanandroid.bean.LoginBean;
import com.zzh.aiwanandroid.config.MyApplication;

import java.util.Map;

import kotlin.Pair;

public class CommonUtils {

    public static final String Cookie = "Cookie";

    public static final String SP_USER = "user_info";


    /**
     * 保存Cookies
     *
     * @param cookie
     */
    public static void saveDataCookiesToSP(String cookie) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(CommonUtils.Cookie, cookie);
        edit.apply();
    }


    /**
     * /**
     * "admin": false,
     * "chapterTops": [],
     * "coinCount": 10,
     * "collectIds": [],
     * "email": "",
     * "icon": "",
     * "id": 129934,
     * "nickname": "zhangzh",
     * "password": "",
     * "publicName": "zhangzh",
     * "token": "",
     * "type": 0,
     * "username": "zhangzh"
     *
     * @param user
     */

    /**
     * 保存对象
     *
     * @param object
     */
    public static void saveObjectToSP(Object object) {
        SharedPreferences user_info = MyApplication.getContext().getSharedPreferences(CommonUtils.SP_USER, Context.MODE_PRIVATE);
        String key = getClassName(object.getClass());
        Gson gson = new Gson();
        String json = gson.toJson(object);
        user_info.edit().putString(key, json).apply();
    }

    /**
     * 获取对象
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getObjectFromSP(Class<T> clazz) {
        String key = getClassName(clazz);
        SharedPreferences sharedPreferences = MyApplication.getContext().getSharedPreferences(CommonUtils.SP_USER, Context.MODE_PRIVATE);
        String json = sharedPreferences.getString(key, "");
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }

    private static String getClassName(Class<?> clazz) {
        return clazz.getName();
    }

    /**
     * 获取Cookies
     *
     * @return
     */
    public static String getCookieFromSP() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        return preferences.getString(Cookie, "");

    }


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
    public static int[] getColors(Activity activity) {
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
