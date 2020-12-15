package com.example.friendsservice;

import android.os.Parcel;
import android.os.Parcelable;

public class Comment implements Parcelable {
    private String fromUserName;
    private String toUserName;
    private String comment;

    public Comment(String fromUserName, String comment) {
        this.fromUserName = fromUserName;
        this.comment = comment;
    }

    public Comment(String fromUserName, String toUserName, String comment) {
        this.fromUserName = fromUserName;
        this.toUserName = toUserName;
        this.comment = comment;
    }

    protected Comment(Parcel in) {
        fromUserName = in.readString();
        toUserName = in.readString();
        comment = in.readString();
    }

    public static final Creator<Comment> CREATOR = new Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel in) {
            return new Comment(in);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fromUserName);
        dest.writeString(toUserName);
        dest.writeString(comment);
    }
}
