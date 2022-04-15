package com.zzh.aiwanandroid.bean;

import java.util.List;

/**
 * 导航数据Bean
 *
 */
public class NavigationBean {

    private List<Navigation> data;
    private int errorCode;
    private String errorMsg;

    public static class Navigation{
        private List<Article> articles;
        private int cid;
        private String name;

        public List<Article> getArticles() {
            return articles;
        }

        public void setArticles(List<Article> articles) {
            this.articles = articles;
        }

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public List<Navigation> getData() {
        return data;
    }

    public void setData(List<Navigation> data) {
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
