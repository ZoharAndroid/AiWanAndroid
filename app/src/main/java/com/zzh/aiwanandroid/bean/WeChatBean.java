package com.zzh.aiwanandroid.bean;

import java.util.List;


/**
 * 公众号联系人
 *
 * 2022-4-8 09:51:33
 */
public class WeChatBean {
    private List<WeChat> data;
    private int errorCode;
    private String errorMsg;

    public List<WeChat> getData() {
        return data;
    }

    public void setData(List<WeChat> data) {
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

    public class WeChat {
        private String author;
        private String[] children;
        private int courseId;
        private String cover;
        private String desc;
        private int id;
        private String lisense;
        private String lisenseLink;
        private String name;
        private int order;
        private int parentChapterId;
        private boolean userControlSetTop;
        private int visible;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String[] getChildren() {
            return children;
        }

        public void setChildren(String[] children) {
            this.children = children;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLisense() {
            return lisense;
        }

        public void setLisense(String lisense) {
            this.lisense = lisense;
        }

        public String getLisenseLink() {
            return lisenseLink;
        }

        public void setLisenseLink(String lisenseLink) {
            this.lisenseLink = lisenseLink;
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

        public int getParentChapterId() {
            return parentChapterId;
        }

        public void setParentChapterId(int parentChapterId) {
            this.parentChapterId = parentChapterId;
        }

        public boolean isUserControlSetTop() {
            return userControlSetTop;
        }

        public void setUserControlSetTop(boolean userControlSetTop) {
            this.userControlSetTop = userControlSetTop;
        }

        public int getVisible() {
            return visible;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }
    }
}
