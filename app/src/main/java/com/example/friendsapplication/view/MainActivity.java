package com.example.friendsapplication.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.friendsapplication.R;
import com.example.friendsapplication.data.Comment;
import com.example.friendsapplication.data.Data;
import com.example.friendsapplication.model.Moment;
import com.example.friendsapplication.model.User;
import com.example.friendsapplication.model.UserDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    List<Data> dataList = new ArrayList<>();
    UserDatabase database;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    class LoadingHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
//            initDatabase();
            getMainDataList();
            Handler mainHandler = new Handler(Looper.getMainLooper());
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    Log.e(TAG,Thread.currentThread().getName());
                    readyForStartShow();
                }
            });
        }
    }

    public void loadingAndShowContent() {
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                LoadingHandler handler = new LoadingHandler();
                Message msg = Message.obtain();
                handler.sendMessage(msg);
                Looper.loop();
            }
        }.start();
    }

    public void readyForStartShow() {
        RecyclerView mainRecyclerView = findViewById(R.id.main_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mainRecyclerView.setLayoutManager(layoutManager);
        UserInfoViewAdapter adapter = new UserInfoViewAdapter(dataList, this);
        mainRecyclerView.setAdapter(adapter);
        mainRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadingAndShowContent();
    }

    private User initKobe() {
        String userName = "Kobe";
        int avatar = R.drawable.avatar;
        int backgroundImage = R.drawable.background;
        return new User(userName, avatar, backgroundImage);
    }

    private Moment initKobeMoment(int id, List<Integer> imageList, List<String> agreeList, List<Comment> commentList) {
        database = UserDatabase.getDatabase(this);
        User nowUser = database.userDao().selectUserById(id);
        String userName = nowUser.getUserName();
        int avatar = nowUser.getAvatar();
        Date pubTime = null;
        return new Moment(id, userName, avatar, "cool", pubTime, imageList, agreeList, commentList);
    }

    public void initDatabase() {
        database = UserDatabase.getDatabase(this);
        database.userDao().insertAll(initKobe());
        List<Integer> imageList = Arrays.asList(R.drawable.background);
        List<String> agreeList = Arrays.asList("nash", "jordan", "james");
        List<Comment> commentList = Arrays.asList(new Comment("nash", "cool"), new Comment("james", "nash", "you too"));
        User user = database.userDao().selectUserById(1);
        int userId = user.getId();
        database.momentDao().insertAll(initKobeMoment(userId, imageList, agreeList, commentList));
    }

    public void getMainDataList() {
        Log.e(TAG, Thread.currentThread().getName());
        database = UserDatabase.getDatabase(this);
        dataList.clear();
        User nowUser = database.userDao().selectNowUserInformation();
        dataList.add(new Data(nowUser));
        List<Moment> momentList = database.momentDao().selectAllMoments();
        for (Moment moment : momentList) {
            dataList.add(new Data(moment));
        }
    }
}
