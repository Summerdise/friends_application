package com.example.friendsapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.friendsapplication.data.Comment;
import com.example.friendsapplication.data.Data;
import com.example.friendsapplication.data.MomentsItem;
import com.example.friendsapplication.data.UserInformation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    List<Data> dataList = new ArrayList<>();
    UserDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
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
        RecyclerView mainRecyclerView = findViewById(R.id.main_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mainRecyclerView.setLayoutManager(layoutManager);
        UserInfoViewAdapter adapter = new UserInfoViewAdapter(dataList);
        mainRecyclerView.setAdapter(adapter);
        mainRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private void initData(User user) {
        dataList.add(new Data(new UserInformation(user.getUserName(), user.getAvatar(), user.getBackgroundImage())));
        for (MomentsItem momentsItem : user.getMomentsItemList()) {
            dataList.add(new Data(momentsItem));
        }
    }

    private User initUser() {
        String userName = "Kobe";
        int avatar = R.drawable.avatar;
        int backgroundImage = R.drawable.background;
        Date pubTime = null;
        List<Integer> imageList = Arrays.asList(R.drawable.james_description);
        List<String> agreeList = Arrays.asList("nash", "jordan", "james");
        List<Comment> commentList = Arrays.asList(new Comment("nash", "cool"), new Comment("james", "nash", "you too"));
        MomentsItem momentsItem1 = new MomentsItem("jordan", R.drawable.jordan_avatar, "哦豁", pubTime, null, agreeList, commentList);
        MomentsItem momentsItem2 = new MomentsItem("james", R.drawable.james_avatar, "cool?", pubTime, imageList, agreeList, commentList);
        List<MomentsItem> momentsItemList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            momentsItemList.add(momentsItem2);
            momentsItemList.add(momentsItem1);
        }
        return new User(userName, avatar, backgroundImage, momentsItemList);
    }

    private User initKobe() {
        String userName = "Kobe";
        int avatar = R.drawable.avatar;
        int backgroundImage = R.drawable.background;
        Date pubTime = null;
        List<Integer> imageList = Arrays.asList(R.drawable.background);
        List<String> agreeList = Arrays.asList("nash", "jordan", "james");
        List<Comment> commentList = Arrays.asList(new Comment("nash", "cool"), new Comment("james", "nash", "you too"));
        MomentsItem momentsItem1 = new MomentsItem("kobe", R.drawable.avatar, "哦豁", pubTime, null, agreeList, commentList);
        MomentsItem momentsItem2 = new MomentsItem("kobe", R.drawable.avatar, "cool?", pubTime, imageList, agreeList, commentList);
        List<MomentsItem> momentsItemList = new ArrayList<>();
        momentsItemList.add(momentsItem1);
        momentsItemList.add(momentsItem2);
        return new User(userName, avatar, backgroundImage, momentsItemList);
    }

    public void initDatabase() {
        database = UserDatabase.getDatabase(this);
        database.userDao().insertAll(initKobe());
    }

    public void getMainDataList() {
        database = UserDatabase.getDatabase(this);
        User nowUser = database.userDao().selectNowUserInformation();
        UserInformation userInformation = new UserInformation(nowUser.getUserName(), nowUser.getAvatar(), nowUser.getBackgroundImage());
        dataList.add(new Data(userInformation));
        List<User> list = database.userDao().selectAllUsers();
        for (User user : list) {
            for (MomentsItem momentsItem : user.getMomentsItemList()) {
                dataList.add(new Data(momentsItem));
            }
        }
    }
}