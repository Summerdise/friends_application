package com.example.friendsapplication;

import androidx.room.TypeConverter;

import com.example.friendsapplication.data.MomentsItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class Converter {

    Gson gson = new Gson();


    @TypeConverter
    public String momentToString(List<MomentsItem> momentsItemList) {
        return gson.toJson(momentsItemList);
    }

    @TypeConverter
    public List<MomentsItem> stringToMoment(String string) {
        MyTypeToken myTypeToken = new MyTypeToken();
        List<MomentsItem> momentsItemList = gson.fromJson(string, myTypeToken.getType());
        return momentsItemList;
    }

    class MyTypeToken extends TypeToken<List<MomentsItem>> {
    }
}
