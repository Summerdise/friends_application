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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        initData(initUser());
        RecyclerView mainRecyclerView = findViewById(R.id.main_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mainRecyclerView.setLayoutManager(layoutManager);
        UserInfoViewAdapter adapter = new UserInfoViewAdapter(dataList);
        mainRecyclerView.setAdapter(adapter);
        mainRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private void initData(User user) {
        dataList.add(new Data(new UserInformation(user.getUserName(),user.getAvatar(), user.getBackgroundImage())));
        for (MomentsItem momentsItem : user.getMomentsItemList()) {
            dataList.add(new Data(momentsItem));
        }
    }

    private User initUser(){
        String userName = "Kobe";
        int avatar = R.drawable.avatar;
        int backgroundImage = R.drawable.background;
        Date pubTime = null;
        List<Integer> imageList = Arrays.asList(R.drawable.james_description);
        List<String> agreeList = Arrays.asList("nash", "jordan", "james");
        List<Comment> commentList = Arrays.asList(new Comment("nash", "cool"), new Comment("james", "nash", "you too"));
        MomentsItem momentsItem1 = new MomentsItem("jordan", R.drawable.jordan_avatar, "哦豁",pubTime, null, agreeList, commentList);
        MomentsItem momentsItem2 = new MomentsItem("james", R.drawable.james_avatar, "cool?",pubTime, imageList, agreeList, commentList);
        List<MomentsItem> momentsItemList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            momentsItemList.add(momentsItem2);
            momentsItemList.add(momentsItem1);
        }
        return new User(userName,avatar,backgroundImage,momentsItemList);
    }
}