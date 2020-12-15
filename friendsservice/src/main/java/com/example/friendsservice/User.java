package com.example.friendsservice;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "users")
public class User implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String userName;
    private String avatar;
    private String backgroundImage;

    public User(String userName, String avatar, String backgroundImage) {
        this.userName = userName;
        this.avatar = avatar;
        this.backgroundImage = backgroundImage;
    }


    protected User(Parcel in) {
        id = in.readInt();
        userName = in.readString();
        avatar = in.readString();
        backgroundImage = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public com.example.friendsservice.User createFromParcel(Parcel in) {
            return new com.example.friendsservice.User(in);
        }

        @Override
        public com.example.friendsservice.User[] newArray(int size) {
            return new com.example.friendsservice.User[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(userName);
        dest.writeString(avatar);
        dest.writeString(backgroundImage);
    }
}