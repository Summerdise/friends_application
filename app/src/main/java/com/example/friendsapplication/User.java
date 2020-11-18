package com.example.friendsapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.friendsapplication.data.MomentsItem;

import java.util.List;

@Entity(tableName = "users_moments")
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String userName;
    private int avatar;
    private int backgroundImage;
    private List<MomentsItem> momentsItemList;

    public User(String userName, int avatar, int backgroundImage, List<MomentsItem> momentsItemList) {
        this.userName = userName;
        this.avatar = avatar;
        this.backgroundImage = backgroundImage;
        this.momentsItemList = momentsItemList;
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

    public List<MomentsItem> getMomentsItemList() {
        return momentsItemList;
    }

    public void setMomentsItemList(List<MomentsItem> momentsItemList) {
        this.momentsItemList = momentsItemList;
    }
}
