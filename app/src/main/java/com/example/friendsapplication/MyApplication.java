package com.example.friendsapplication;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.nfc.Tag;
import android.os.IBinder;
import android.util.Log;

import com.example.friendsservice.ServiceFriendInterface;

public class MyApplication extends Application {

    private static ServiceFriendInterface serviceFriendInterface;
    private static Context context;

    private static ServiceConnection serviceConnection;

    private static final String TAG = "MainActivity";
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(TAG,"connected");
                serviceFriendInterface = ServiceFriendInterface.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(TAG,"unconnected");
                serviceFriendInterface = null;
            }
        };
        Log.d(TAG,"connect over");
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
