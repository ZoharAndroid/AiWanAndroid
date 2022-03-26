package com.zzh.aiwanandroid.bean;

import java.util.List;

/**
 * 文章列表
 */
public class ArticlePages {

    private List<ArticlePagesData> data;
    private int errorCode;
    private String errorMsg;

    public List<ArticlePagesData> getData() {
        return data;
    }

    public void setData(List<ArticlePagesData> data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
