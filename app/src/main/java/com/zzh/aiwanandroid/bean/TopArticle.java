package com.zzh.aiwanandroid.bean;

import java.util.List;

/**
 * 置顶文章
 */
public class TopArticle {

    private List<Article> data;
    private int errorCode;
    private String errorMsg;

    public List<Article> getData() {
        return data;
    }

    public void setData(List<Article> data) {
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
