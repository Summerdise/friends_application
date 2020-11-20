package com.example.friendsapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.friendsapplication.data.Comment;

import java.util.Date;
import java.util.List;

@Entity(tableName = "moments")
public class Moment {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int userId;
    private String userName;
    private int avatar;
    private String content;
    private Date pubDate;
    private List<Integer> imageList;
    private List<String> agreeList;
    private List<Comment> commentList;

    public Moment(int userId, String userName, int avatar, String content, Date pubDate, List<Integer> imageList, List<String> agreeList, List<Comment> commentList) {
        this.userId = userId;
        this.userName = userName;
        this.avatar = avatar;
        this.content = content;
        this.pubDate = pubDate;
        this.imageList = imageList;
        this.agreeList = agreeList;
        this.commentList = commentList;
    }

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

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
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

    public List<Integer> getImageList() {
        return imageList;
    }

    public void setImageList(List<Integer> imageList) {
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
}
