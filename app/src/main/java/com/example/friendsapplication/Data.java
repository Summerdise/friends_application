package com.example.friendsapplication;

import java.util.List;

public class Data {
    private static final int HEAD = 1;
    private static final int ITEM = 2;

    private int type;
    private User user;
    private MomentsItem momentsItem;

    public Data(User user) {
        this.type = HEAD;
        this.user = user;
    }

    public Data(MomentsItem momentsItem) {
        this.type = ITEM;
        this.momentsItem = momentsItem;
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

    public MomentsItem getMomentsItem() {
        return momentsItem;
    }

    public void setMomentsItem(MomentsItem momentsItem) {
        this.momentsItem = momentsItem;
    }
}
