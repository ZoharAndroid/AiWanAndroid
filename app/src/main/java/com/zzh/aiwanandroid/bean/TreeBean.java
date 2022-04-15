package com.zzh.aiwanandroid.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class TreeBean {
    private List<Tree> data;
    private int errorCode;
    private String errorMsg;

    public List<Tree> getData() {
        return data;
    }

    public void setData(List<Tree> data) {
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

    public static class Tree implements Parcelable {
        private String author;
        private List<Tree> children;
        private int courseId;
        private String conver;
        private String desc;
        private int id;
        private String lisense;
        private String lisenseLink;
        private String name;
        private int order;
        private int parentChapterId;
        private boolean userControlSetTop;
        private int visible;

        protected Tree(Parcel in) {
            author = in.readString();
            children = in.createTypedArrayList(Tree.CREATOR);
            courseId = in.readInt();
            conver = in.readString();
            desc = in.readString();
            id = in.readInt();
            lisense = in.readString();
            lisenseLink = in.readString();
            name = in.readString();
            order = in.readInt();
            parentChapterId = in.readInt();
            userControlSetTop = in.readByte() != 0;
            visible = in.readInt();
        }

        public static final Creator<Tree> CREATOR = new Creator<Tree>() {
            @Override
            public Tree createFromParcel(Parcel in) {
                return new Tree(in);
            }

            @Override
            public Tree[] newArray(int size) {
                return new Tree[size];
            }
        };

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public List<Tree> getChildren() {
            return children;
        }

        public void setChildren(List<Tree> children) {
            this.children = children;
        }

        public int getCourseId() {
            return courseId;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public String getConver() {
            return conver;
        }

        public void setConver(String conver) {
            this.conver = conver;
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(author);
            dest.writeTypedList(children);
            dest.writeInt(courseId);
            dest.writeString(conver);
            dest.writeString(desc);
            dest.writeInt(id);
            dest.writeString(lisense);
            dest.writeString(lisenseLink);
            dest.writeString(name);
            dest.writeInt(order);
            dest.writeInt(parentChapterId);
            dest.writeByte((byte) (userControlSetTop ? 1 : 0));
            dest.writeInt(visible);
        }
    }
}
