package com.example.friendsapplication.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.friendsapplication.data.Moment;

import java.util.List;

@Dao
public interface MomentDao {
    @Insert
    public void insertAll(Moment... moments);

    @Update
    public void updateMoments(Moment... moments);

    @Query("SELECT * FROM moments")
    public List<Moment> selectAllMoments();

    @Query("SELECT * FROM moments WHERE id = :id")
    public Moment selectMomentsById(int id);

    @Query("SELECT * FROM moments WHERE userId = :userId")
    public List<Moment> selectMomentsByUserId(int userId);

}
