package com.example.friendsapplication.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {User.class, Moment.class},version = 1,exportSchema = false)
@TypeConverters({Converter.class})
public abstract class UserDatabase extends RoomDatabase {

    private static volatile UserDatabase database;

    public static UserDatabase getDatabase(Context context){
        if (database == null) {
            synchronized (UserDatabase.class) {
                if (database == null) {
                    database = Room.databaseBuilder(context.getApplicationContext(),
                            UserDatabase.class, "users_database").allowMainThreadQueries().build();
                }
            }
        }
        return database;
    }

    public abstract UserDao userDao();
    public abstract MomentDao momentDao();
}
