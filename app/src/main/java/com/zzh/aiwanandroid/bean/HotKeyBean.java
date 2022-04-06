package com.zzh.aiwanandroid.bean;

import java.util.List;

/**
 * 热搜关键词Bean
 *
 */
public class HotKeyBean {
    private List<HotKey> data;
    private int errorCode;
    private String errorMsg;


    public List<HotKey> getData() {
        return data;
    }

    public void setData(List<HotKey> data) {
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

    public static class HotKey {
        private int id;
        private String link;
        private String name;
        private int order;
        private int visible;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public int getVisible() {
            return visible;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }
    }
}
