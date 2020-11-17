package com.example.friendsapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    List<Data> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        initData();
        RecyclerView mainRecyclerView = findViewById(R.id.main_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mainRecyclerView.setLayoutManager(layoutManager);
        UserInfoViewAdapter adapter = new UserInfoViewAdapter(dataList);
        mainRecyclerView.setAdapter(adapter);
        mainRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private void initData() {
        dataList.add(new Data(new User("Kobe", R.drawable.avatar, R.drawable.background)));
        List<Integer> imageList = Arrays.asList(R.drawable.james_description);
        List<String> agreeList = Arrays.asList("nash", "jordan", "james");
        List<Comment> commentList = Arrays.asList(new Comment("nash", "cool"), new Comment("james", "nash", "you too"));
        MomentsItem momentsItem1 = new MomentsItem("jordan", R.drawable.jordan_avatar, "哦豁", null, agreeList, commentList);
        MomentsItem momentsItem2 = new MomentsItem("james", R.drawable.james_avatar, "cool?", imageList, agreeList, commentList);
        for (int i = 0; i < 2; i++) {
            dataList.add(new Data(momentsItem2));
            dataList.add(new Data(momentsItem1));
        }
    }
}