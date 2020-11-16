package com.example.friendsapplication;

import android.media.Image;

public class User {

    private String userName;
    private int avatar;
    private int backgroundImage;

    public User(String userName, int avatar, int backgroundImage) {
        this.userName = userName;
        this.avatar = avatar;
        this.backgroundImage = backgroundImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public int getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(int backgroundImage) {
        this.backgroundImage = backgroundImage;
    }
}
