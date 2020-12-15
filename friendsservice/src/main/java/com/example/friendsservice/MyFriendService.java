package com.example.friendsservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import com.example.friendsservice.dataHandle.UserDatabase;

import java.util.ArrayList;
import java.util.List;

public class MyFriendService extends Service {

    List<Data> dataList;
    UserDatabase userDatabase;



    private ServiceFriendInterface serviceFriendInterface = new ServiceFriendInterface.Stub() {
        @Override
        public List<Data> getDataList() throws RemoteException {
            return dataList;
        }

        @Override
        public void addData(Data data) throws RemoteException {
            return;
        }

        @Override
        public Moment getMomentById(int id) throws RemoteException {
            return userDatabase.momentDao().selectMomentsById(id);
        }

        @Override
        public List<Moment> getAllMoment() throws RemoteException {
            return userDatabase.momentDao().selectAllMoments();
        }

        @Override
        public void updateMoment(Moment moment) throws RemoteException {
            userDatabase.momentDao().updateMoments(moment);
        }

        @Override
        public void addMoment(Moment moment) throws RemoteException {
            userDatabase.momentDao().insertAll(moment);
        }

        @Override
        public User getNowUser() throws RemoteException {
            return userDatabase.userDao().selectNowUserInformation();
        }


    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        dataList = new ArrayList<>();
        userDatabase = UserDatabase.getDatabase(MyServiceApplication.getContext());
        return null;
    }
}
