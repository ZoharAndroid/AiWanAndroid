package com.zzh.aiwanandroid.utils;

import android.util.Log;

public class LogUtils {

    private static final String TAG = "----LogUtils----";


    public static final int LEVEL_V = 0;
    public static final int LEVEL_D = 1;
    public static final int LEVEL_I = 2;
    public static final int LEVEL_W = 3;
    public static final int LEVEL_E = 4;

    // 默认等级为Verbose
    public static int level = LEVEL_V;

    public static void v(String msg) {
        if (level <= LEVEL_V) {
            Log.v(TAG, msg);
        }
    }

    public static void d(String msg){
        if (level <= LEVEL_D){
            Log.d(TAG,msg);
        }
    }

    public static void e(String msg){
        if (level <= LEVEL_E){
            Log.d(TAG,msg);
        }
    }


}
