package com.example.friendsapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.friendsapplication.data.Comment;
import com.example.friendsapplication.data.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    List<Data> dataList = new ArrayList<>();
    UserDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
//        ButterKnife.bind(this);
        Thread loadingTread = new Thread(new Runnable() {
            @Override
            public void run() {
//                initDatabase();
                getMainDataList();
            }
        });
        loadingTread.start();
        try {
            loadingTread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        readyForStartShow();
    }

    public void readyForStartShow(){
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
        readyForStartShow();
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
        database = UserDatabase.getDatabase(this);
        User nowUser = database.userDao().selectNowUserInformation();
        dataList.add(new Data(nowUser));
        List<Moment> momentList = database.momentDao().selectAllMoments();
        for (Moment moment : momentList) {
            dataList.add(new Data(moment));
        }
    }
}
