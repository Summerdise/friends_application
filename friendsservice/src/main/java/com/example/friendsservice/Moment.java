package com.example.friendsservice;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.List;

@Entity(tableName = "moments")
public class Moment implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int userId;
    private String userName;
    private String avatar;
    private String content;
    private Date pubDate;
    private List<String> imageList;
    private List<String> agreeList;
    private List<Comment> commentList;

    public Moment(int userId, String userName, String avatar, String content, Date pubDate, List<String> imageList, List<String> agreeList, List<Comment> commentList) {
        this.userId = userId;
        this.userName = userName;
        this.avatar = avatar;
        this.content = content;
        this.pubDate = pubDate;
        this.imageList = imageList;
        this.agreeList = agreeList;
        this.commentList = commentList;
    }

    protected Moment(Parcel in) {
        id = in.readInt();
        userId = in.readInt();
        userName = in.readString();
        avatar = in.readString();
        content = in.readString();
        agreeList = in.createStringArrayList();
        commentList = in.createTypedArrayList(Comment.CREATOR);
    }

    public static final Creator<Moment> CREATOR = new Creator<Moment>() {
        @Override
        public com.example.friendsservice.Moment createFromParcel(Parcel in) {
            return new com.example.friendsservice.Moment(in);
        }

        @Override
        public com.example.friendsservice.Moment[] newArray(int size) {
            return new com.example.friendsservice.Moment[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public List<String> getAgreeList() {
        return agreeList;
    }

    public void setAgreeList(List<String> agreeList) {
        this.agreeList = agreeList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(userId);
        dest.writeString(userName);
        dest.writeString(avatar);
        dest.writeString(content);
        dest.writeStringList(agreeList);
        dest.writeTypedList(commentList);
    }
}
