package com.example.friendsservice.dataHandle;

import androidx.room.TypeConverter;

import com.example.friendsservice.Comment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Date;
import java.util.List;

public class Converter {

    Gson gson = new Gson();


    @TypeConverter
    public String dateToString(Date date) {
        return gson.toJson(date);
    }

    @TypeConverter
    public Date stringToDate(String string) {
        DateTypeToken myTypeToken = new DateTypeToken();
        Date date = gson.fromJson(string, myTypeToken.getType());
        return date;
    }

    @TypeConverter
    public String stringListToString(List<String> list) {
        return gson.toJson(list);
    }

    @TypeConverter
    public List<String> stringToStringList(String string) {
        StringListTypeToken myTypeToken = new StringListTypeToken();
        List<String> stringList = gson.fromJson(string, myTypeToken.getType());
        return stringList;
    }

    @TypeConverter
    public String commentListToString(List<Comment> list) {
        return gson.toJson(list);
    }

    @TypeConverter
    public List<Comment> stringToCommentList(String string) {
        CommentListTypeToken myTypeToken = new CommentListTypeToken();
        List<Comment> commentList = gson.fromJson(string, myTypeToken.getType());
        return commentList;
    }



    class DateTypeToken extends TypeToken<Date> {
    }

    class StringListTypeToken extends TypeToken<List<String>> {
    }
    class CommentListTypeToken extends TypeToken<List<Comment>> {
    }
}
