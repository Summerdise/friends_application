package com.example.friendsapplication.Presenter;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import com.example.friendsapplication.base.BasePresenter;
import com.example.friendsapplication.data.Data;
import com.example.friendsapplication.data.Moment;
import com.example.friendsapplication.data.User;
import com.example.friendsapplication.data.UserDatabase;
import com.example.friendsapplication.model.MainModel;
import com.example.friendsapplication.view.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class MainPresenter extends BasePresenter<MainModel, MainActivity> {

    private MainActivity mMainActivity;
    private MainModel mMainModel;
    private UserDatabase database;

    public MainPresenter(MainActivity mainActivity) {
        this.mMainModel = getModelInstance();
        this.mMainActivity = mainActivity;
    }

    @Override
    public MainModel getModelInstance() {
        return new MainModel(this);
    }

    class LoadingHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            List<Data> dataList = getMainDataList();
            Handler mainHandler = new Handler(Looper.getMainLooper());
            mainHandler.post(() -> {
                mMainActivity.readyForStartShow(dataList);
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


    public List<Data> getMainDataList() {
        database = UserDatabase.getDatabase(mMainActivity.getApplication());
        List<Data> dataList = new ArrayList<>();
        User nowUser = database.userDao().selectNowUserInformation();
        dataList.add(new Data(nowUser));
        List<Moment> momentList = database.momentDao().selectAllMoments();
        for (Moment moment : momentList) {
            dataList.add(new Data(moment));
        }
        return dataList;
    }

}
