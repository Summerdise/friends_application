package com.example.friendsapplication;

import java.util.List;

public class MomentsItem {
    private String name;
    private int avatar;
    private String content;
    private List<Integer> imageList;
    private List<String> agreeList;
    private List<Comment> commentList;

    public MomentsItem(String name, int avatar, String content, List<Integer> imageList, List<String> agreeList, List<Comment> commentList) {
        this.name = name;
        this.avatar = avatar;
        this.content = content;
        this.imageList = imageList;
        this.agreeList = agreeList;
        this.commentList = commentList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
