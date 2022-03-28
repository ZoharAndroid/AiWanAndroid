package com.zzh.aiwanandroid.utils;

import android.util.Log;

import androidx.annotation.NonNull;

import com.zzh.aiwanandroid.config.CallbackListener;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtils {

    private static final String TAG = "HttpUtils";

    /**
     * 发送Http请求
     *
     * @param url
     * @param listener
     */
    public static void sendHttpRequest(String url, CallbackListener listener) {
        LogUtils.d(url);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                LogUtils.d("网络请求失败");
                listener.onFailure(call);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                //LogUtils.d(Objects.requireNonNull(response.body()).string());
                LogUtils.d("网络请求成功");
                listener.onSuccess(response.body().string());
            }
        });


    }
}
