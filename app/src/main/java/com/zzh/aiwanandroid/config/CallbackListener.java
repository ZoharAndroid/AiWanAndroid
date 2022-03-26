package com.zzh.aiwanandroid.config;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Http请求回调方法
 */
public interface CallbackListener {

    /**
     * 发送请求成功
     *
     * @param response
     */
    void onSuccess(String response);

    /**
     * 发送请求失败
     *
     * @param call
     */
    void onFailure(Call call);
}
