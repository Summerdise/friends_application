package com.example.friendsapplication.presenter;

import com.example.friendsapplication.base.BasePresenter;
import com.example.friendsapplication.data.Moment;
import com.example.friendsapplication.data.User;
import com.example.friendsapplication.data.UserDatabase;
import com.example.friendsapplication.model.NoImageCreateModel;
import com.example.friendsapplication.view.NoImageCreateActivity;

public class NoImageCreatePresenter extends BasePresenter<NoImageCreateModel, NoImageCreateActivity> {


    private NoImageCreateActivity mNoImageCreateActivity;
    private NoImageCreateModel mNoImageCreateModel;
    private UserDatabase userDatabase;


    public NoImageCreatePresenter(NoImageCreateActivity mNoImageCreateActivity) {
        this.mNoImageCreateModel = getModelInstance();
        this.mNoImageCreateActivity = mNoImageCreateActivity;
    }

    @Override
    public NoImageCreateModel getModelInstance() {
        return new NoImageCreateModel(this);
    }

    public void updateNewNoImageMoment(String inputContent) {
        userDatabase = UserDatabase.getDatabase(mNoImageCreateActivity.getApplication());
        User nowUser = userDatabase.userDao().selectNowUserInformation();
        Moment moment = new Moment(nowUser.getId(), nowUser.getUserName(), nowUser.getAvatar(), inputContent, null, null, null, null);
        userDatabase.momentDao().insertAll(moment);
    }
}
