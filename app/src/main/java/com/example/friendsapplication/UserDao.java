package com.example.friendsapplication;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    public void insertAll(User... users);

    @Query("SELECT * FROM users_moments")
    public List<User> selectAllUsers();

    @Query("SELECT * FROM users_moments WHERE userName = :name")
    public List<User> selectUser(String name);

    @Query("SELECT * FROM users_moments WHERE id = 1")
    public User selectNowUserInformation();
}
