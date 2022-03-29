package com.zzh.aiwanandroid.bean;

import java.util.List;

/**
 * 文章列表
 */
public class ArticlePages {

    private ArticlePagesData data;
    private int errorCode;
    private String errorMsg;

    public ArticlePages(ArticlePagesData data) {
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

    public ArticlePagesData getData() {
        return data;
    }

    public void setData(ArticlePagesData data) {
        this.data = data;
    }
}
