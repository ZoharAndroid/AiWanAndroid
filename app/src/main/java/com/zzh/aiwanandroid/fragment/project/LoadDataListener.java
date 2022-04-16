package com.zzh.aiwanandroid.fragment.project;

/**
 * 加载更多接口
 */
public interface LoadDataListener {

    /**
     * 加载成功
     *
     * @param response
     */
    void onLoadFinishSuccess(String response);

    /**
     * 加载失败
     */
    void onLoadFinishFailed();
}
