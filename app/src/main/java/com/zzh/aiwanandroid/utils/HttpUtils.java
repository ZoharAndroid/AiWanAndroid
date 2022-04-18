package com.zzh.aiwanandroid.utils;

import android.content.ContentValues;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.zzh.aiwanandroid.config.CallbackListener;
import com.zzh.aiwanandroid.config.HttpConfig;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

import kotlin.Pair;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtils {

    public static final String COOKIE = "Cookie";


    /**
     * loginUserName_wanandroid_com=zhangzh; token_pass_wanandroid_com=5d9b90bcb70640183e09d1e755ead823; JSESSIONID=2AA25D4861DB14C129A9EDBEB6DB3BC4; loginUserName=zhangzh; token_pass=5d9b90bcb70640183e09d1e755ead823
     */

    /**
     * 发送Http请求
     *
     * @param url
     * @param listener
     */
    public static void sendHttpRequest(String url, CallbackListener listener) {
        LogUtils.d(url);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().header(HttpUtils.COOKIE, getCookie()).url(url).build();
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

    /**
     * 登录请求
     *
     * @param username
     * @param password
     * @param listener
     */
    public static void sendLoginPostRequest(String username, String password, CallbackListener listener) {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .build();
        Request request = new Request.Builder().post(requestBody).header(HttpUtils.COOKIE, getCookie()).url(HttpConfig.LOGIN_URL()).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                listener.onFailure(call);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                saveCookie(response);
                listener.onSuccess(response.body().string());
            }
        });

    }


    /**
     * 保存SaveCookie
     *
     * @param response
     */
    private static void saveCookie(Response response) {
        Headers headers = response.headers();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < headers.size(); i++) {
            if (headers.name(i).equals("Set-Cookie")) {
                sb.append(headers.value(i).split(";")[0]);
                sb.append(";");
            }
        }
        // 保存Cookies
        CommonUtils.saveDataCookiesToSP(sb.toString());
    }

    /**
     * 获取cookie
     *
     * @return
     */
    private static String getCookie() {
        return CommonUtils.getCookieFromSP();
    }

    /**
     * Post 发送搜索请求
     *
     * @param url
     * @param postContent
     * @param listener
     */
    public static void sendSearchPostRequest(String url, String postContent, CallbackListener listener) {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder().add("k", postContent).build();
        Request request = new Request.Builder().header(HttpUtils.COOKIE, getCookie()).url(url).post(requestBody).build();
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

    public static void sendRegisterPostRequest(String username, String password, String repassword, CallbackListener listener) {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .add("repassword", repassword)
                .build();
        Request request = new Request.Builder().header(HttpUtils.COOKIE, getCookie()).url(HttpConfig.REGISTER_URL()).post(requestBody).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                listener.onFailure(call);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                saveCookie(response);
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
    public static <T> T parseJson(String response, Class<T> classOfT) {
        Gson gson = new Gson();
        T t = gson.fromJson(response, classOfT);
        return t;
    }
}
