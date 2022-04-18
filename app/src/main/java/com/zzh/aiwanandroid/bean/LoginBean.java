package com.zzh.aiwanandroid.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class LoginBean {

    /**
     * {
     *     "data": {
     *         "admin": false,
     *         "chapterTops": [],
     *         "coinCount": 0,
     *         "collectIds": [],
     *         "email": "",
     *         "icon": "",
     *         "id": 129934,
     *         "nickname": "zhangzh",
     *         "password": "",
     *         "publicName": "zhangzh",
     *         "token": "",
     *         "type": 0,
     *         "username": "zhangzh"
     *     },
     *     "errorCode": 0,
     *     "errorMsg": ""
     * }
     */
    private User data;
    private int errorCode;
    private String errorMsg;

    public static class User implements Parcelable {
        private boolean admin;
        private String[] chapterTops;
        private int coinCount;
        private int[] collectIds;
        private String email;
        private int id;
        private String nickname;
        private String password;
        private String publicName;
        private String token;
        private String username;

        protected User(Parcel in) {
            admin = in.readByte() != 0;
            chapterTops = in.createStringArray();
            coinCount = in.readInt();
            collectIds = in.createIntArray();
            email = in.readString();
            id = in.readInt();
            nickname = in.readString();
            password = in.readString();
            publicName = in.readString();
            token = in.readString();
            username = in.readString();
        }

        public static final Creator<User> CREATOR = new Creator<User>() {
            @Override
            public User createFromParcel(Parcel in) {
                return new User(in);
            }

            @Override
            public User[] newArray(int size) {
                return new User[size];
            }
        };

        public boolean isAdmin() {
            return admin;
        }

        public void setAdmin(boolean admin) {
            this.admin = admin;
        }

        public String[] getChapterTops() {
            return chapterTops;
        }

        public void setChapterTops(String[] chapterTops) {
            this.chapterTops = chapterTops;
        }

        public int getCoinCount() {
            return coinCount;
        }

        public void setCoinCount(int coinCount) {
            this.coinCount = coinCount;
        }

        public int[] getCollectIds() {
            return collectIds;
        }

        public void setCollectIds(int[] collectIds) {
            this.collectIds = collectIds;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPublicName() {
            return publicName;
        }

        public void setPublicName(String publicName) {
            this.publicName = publicName;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeByte((byte) (admin ? 1 : 0));
            dest.writeStringArray(chapterTops);
            dest.writeInt(coinCount);
            dest.writeIntArray(collectIds);
            dest.writeString(email);
            dest.writeInt(id);
            dest.writeString(nickname);
            dest.writeString(password);
            dest.writeString(publicName);
            dest.writeString(token);
            dest.writeString(username);
        }
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
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
