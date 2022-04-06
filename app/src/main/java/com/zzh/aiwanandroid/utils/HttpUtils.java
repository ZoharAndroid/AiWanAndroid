package com.zzh.aiwanandroid.utils;

import android.content.ContentValues;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.zzh.aiwanandroid.config.CallbackListener;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtils {

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

    public static void sendPostRequest(String url, String jsonContent,CallbackListener listener){
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder().add("k",jsonContent).build();
        Request request = new Request.Builder().url(url).post(requestBody).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                LogUtils.d("网络请求失败");
                listener.onFailure(call);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                LogUtils.d("网络请求成功");
                listener.onSuccess(response.body().string());
            }
        });
    }


    /**
     * 解析Json数据
     *
     * @param response
     * @param classOfT
     * @param <T>
     * @return
     */
    public static <T> T parseJson(String response,Class<T> classOfT){
        Gson gson = new Gson();
        T t = gson.fromJson(response, classOfT);
        return t;
    }
}
