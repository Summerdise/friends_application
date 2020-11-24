package com.example.friendsapplication.data;

import com.example.friendsapplication.model.Moment;
import com.example.friendsapplication.model.User;

public class Data {
    private static final int HEAD = 1;
    private static final int ITEM = 2;

    private int type;
    private User user;
    private Moment moment;

    public Data(User user) {
        this.type = HEAD;
        this.user = user;
    }

    public Data(Moment moment) {
        this.type = ITEM;
        this.moment = moment;
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

    public Moment getMoment() {
        return moment;
    }

    public void setMoment(Moment moment) {
        this.moment = moment;
    }
}
