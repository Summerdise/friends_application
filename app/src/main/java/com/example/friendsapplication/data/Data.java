package com.example.friendsapplication.data;

public class Data {
    private static final int HEAD = 1;
    private static final int ITEM = 2;

    private int type;
    private UserInformation userInformation;
    private MomentsItem momentsItem;

    public Data(UserInformation userInformation) {
        this.type = HEAD;
        this.userInformation = userInformation;
    }

    public Data(MomentsItem momentsItem) {
        this.type = ITEM;
        this.momentsItem = momentsItem;
    }

    public int getType() {
        return type;
    }

    public UserInformation getUserInformation() {
        return userInformation;
    }

    public void setUserInformation(UserInformation userInformation) {
        this.userInformation = userInformation;
    }

    public MomentsItem getMomentsItem() {
        return momentsItem;
    }

    public void setMomentsItem(MomentsItem momentsItem) {
        this.momentsItem = momentsItem;
    }
}
