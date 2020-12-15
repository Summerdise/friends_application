// ServiceFriendInterface.aidl
package com.example.friendsservice;

import com.example.friendsservice.Data;
import com.example.friendsservice.Comment;
import com.example.friendsservice.User;
import com.example.friendsservice.Moment;

// Declare any non-default types here with import statements

interface ServiceFriendInterface {

    List<Data> getDataList();

    void addData(in Data data);

    Moment getMomentById(int id);

    List<Moment> getAllMoment();

    void updateMoment(in Moment moment);

    void addMoment(in Moment moment);

    User getNowUser();


}