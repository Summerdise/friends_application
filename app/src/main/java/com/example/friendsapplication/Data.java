package com.example.friendsapplication;

import java.util.List;

public class Data {
    private static final int HEAD = 1;
    private static final int ITEM = 2;

    private int type;
    private User user;
    private List<MomentsItem> momentsItemList;

    public Data(User user) {
        this.type = HEAD;
        this.user = user;
    }

    public Data(List<MomentsItem> momentsItemList) {
        this.type = ITEM;
        this.momentsItemList = momentsItemList;
    }

    public int getType() {
        return type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<MomentsItem> getMomentsItemList() {
        return momentsItemList;
    }

    public void setMomentsItemList(List<MomentsItem> momentsItemList) {
        this.momentsItemList = momentsItemList;
    }
}
