package com.example.friendsapplication;

public class Comment {
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
}
