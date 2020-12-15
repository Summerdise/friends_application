package com.example.friendsservice;


import android.os.Parcel;
import android.os.Parcelable;

public class Data implements Parcelable {
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

    protected Data(Parcel in) {
        type = in.readInt();
        user = in.readParcelable(User.class.getClassLoader());
        moment = in.readParcelable(Moment.class.getClassLoader());
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public com.example.friendsservice.Data createFromParcel(Parcel in) {
            return new com.example.friendsservice.Data(in);
        }

        @Override
        public com.example.friendsservice.Data[] newArray(int size) {
            return new com.example.friendsservice.Data[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(type);
        dest.writeParcelable(user, 0);
        dest.writeParcelable(moment, 0);
    }
}
