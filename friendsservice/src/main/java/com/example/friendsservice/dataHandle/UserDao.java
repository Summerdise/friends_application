package com.example.friendsservice.dataHandle;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.friendsservice.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    public void insertAll(User... users);

    @Query("SELECT * FROM users")
    public List<User> selectAllUsers();

    @Query("SELECT * FROM users WHERE id = :userId")
    public User selectUserById(int userId);

    @Query("SELECT * FROM users WHERE id = 1")
    public User selectNowUserInformation();
}
