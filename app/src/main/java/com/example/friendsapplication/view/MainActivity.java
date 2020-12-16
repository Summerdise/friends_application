package com.example.friendsapplication.view;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.friendsapplication.MyApplication;
import com.example.friendsapplication.presenter.MainPresenter;
import com.example.friendsapplication.R;
import com.example.friendsapplication.base.BaseActivity;
import com.example.friendsservice.Data;
import com.example.friendsservice.ServiceFriendInterface;

import java.util.List;


public class MainActivity extends BaseActivity<MainPresenter> {

    private static final String TAG = "MainActivity";
    RecyclerView mainRecyclerView;


    @Override
    public void initView() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        mainRecyclerView = findViewById(R.id.main_recycler_view);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initService() {
        Intent intent = new Intent();
        intent.setPackage("com.example.friendsservice");
        intent.setComponent(new ComponentName("com.example.friendsservice","com.example.friendsservice.MyFriendService"));
        getApplicationContext().bindService(intent, MyApplication.getServiceConnection(), Context.BIND_AUTO_CREATE);

        Log.d(TAG,"bind over");
        Log.d(TAG,MyApplication.getServiceInterface().toString());
    }

    @Override
    public void initListener() {

    }

    @Override
    public int getContentViewID() {
        return R.layout.activity_main;
    }

    @Override
    public MainPresenter getPresenterListener() {
        return new MainPresenter(this);
    }

    @Override
    public void destroy() {
        unbindService(MyApplication.getServiceConnection());
    }

    @Override
    public void onClick(View v) {

    }


    @Override
    protected void onResume() {
        super.onResume();
        getPresenterListener().loadingAndShowContent();
    }

    public void readyForStartShow(List<Data> dataList) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mainRecyclerView.setLayoutManager(layoutManager);
        UserInfoViewAdapter adapter = new UserInfoViewAdapter(dataList, this);
        mainRecyclerView.setAdapter(adapter);
        mainRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

}
