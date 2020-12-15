package com.example.friendsapplication;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.example.friendsservice.ServiceFriendInterface;

public class MyApplication extends Application {

    private static ServiceFriendInterface serviceFriendInterface;
    private static Context context;

    private static ServiceConnection serviceConnection;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                serviceFriendInterface = ServiceFriendInterface.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                serviceFriendInterface = null;
            }
        };
    }

    public static Context getContext() {
        return context;
    }

    public static ServiceConnection getServiceConnection(){
        return serviceConnection;
    }

    public static ServiceFriendInterface getServiceInterface(){
        return serviceFriendInterface;
    }
}
