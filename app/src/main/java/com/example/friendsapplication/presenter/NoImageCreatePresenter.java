package com.example.friendsapplication.presenter;

import android.os.RemoteException;

import com.example.friendsapplication.MyApplication;
import com.example.friendsapplication.base.BasePresenter;
import com.example.friendsapplication.model.NoImageCreateModel;
import com.example.friendsapplication.view.NoImageCreateActivity;
import com.example.friendsservice.Moment;
import com.example.friendsservice.ServiceFriendInterface;
import com.example.friendsservice.User;

public class NoImageCreatePresenter extends BasePresenter<NoImageCreateModel, NoImageCreateActivity> {


    private NoImageCreateActivity mNoImageCreateActivity;
    private NoImageCreateModel mNoImageCreateModel;
    private ServiceFriendInterface mServiceFriendInterface;

    public NoImageCreatePresenter(NoImageCreateActivity mNoImageCreateActivity) {
        this.mNoImageCreateModel = getModelInstance();
        this.mNoImageCreateActivity = mNoImageCreateActivity;
        mServiceFriendInterface = MyApplication.getServiceInterface();
    }

    @Override
    public NoImageCreateModel getModelInstance() {
        return new NoImageCreateModel(this);
    }

    public void updateNewNoImageMoment(String inputContent) throws RemoteException {
        User nowUser = mServiceFriendInterface.getNowUser();
        Moment moment = new Moment(nowUser.getId(),
                nowUser.getUserName(),
                nowUser.getAvatar(),
                inputContent,
                null, null, null, null);
        mServiceFriendInterface.addMoment(moment);
    }
}
