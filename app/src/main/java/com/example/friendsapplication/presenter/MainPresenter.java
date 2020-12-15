package com.example.friendsapplication.presenter;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;

import androidx.annotation.NonNull;

import com.example.friendsapplication.MyApplication;
import com.example.friendsapplication.base.BasePresenter;
import com.example.friendsapplication.model.MainModel;
import com.example.friendsapplication.view.MainActivity;
import com.example.friendsservice.Data;
import com.example.friendsservice.Moment;
import com.example.friendsservice.ServiceFriendInterface;
import com.example.friendsservice.User;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter extends BasePresenter<MainModel, MainActivity> {

    private MainActivity mMainActivity;
    private MainModel mMainModel;
    private ServiceFriendInterface mServiceFriendInterface;

    public MainPresenter(MainActivity mainActivity) {
        this.mMainModel = getModelInstance();
        this.mMainActivity = mainActivity;
        mServiceFriendInterface = MyApplication.getServiceInterface();
    }

    @Override
    public MainModel getModelInstance() {
        return new MainModel(this);
    }

    class LoadingHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            List<Data> dataList = null;
            try {
                dataList = getMainDataList();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            Handler mainHandler = new Handler(Looper.getMainLooper());
            List<Data> finalDataList = dataList;
            mainHandler.post(() -> {
                mMainActivity.readyForStartShow(finalDataList);
            });
        }
    }

    public void loadingAndShowContent() {
        new Thread(() -> {
            Looper.prepare();
            LoadingHandler handler = new LoadingHandler();
            Message msg = Message.obtain();
            handler.sendMessage(msg);
            Looper.loop();
        }).start();
    }


    public List<Data> getMainDataList() throws RemoteException {
        List<Data> dataList = new ArrayList<>();
        User nowUser = mServiceFriendInterface.getNowUser();
        dataList.add(new Data(nowUser));
        List<Moment> momentList = mServiceFriendInterface.getAllMoment();
        for (Moment moment : momentList) {
            dataList.add(new Data(moment));
        }
        return dataList;
    }

}
