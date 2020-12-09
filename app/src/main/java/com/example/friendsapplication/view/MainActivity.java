package com.example.friendsapplication.view;

import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.friendsapplication.Presenter.MainPresenter;
import com.example.friendsapplication.R;
import com.example.friendsapplication.base.BaseActivity;
import com.example.friendsapplication.data.Data;

import java.util.List;


public class MainActivity extends BaseActivity<MainPresenter> {

    private static final String TAG = "MainActivity";
    RecyclerView mainRecyclerView;

    @Override
    public void initView() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        mainRecyclerView = findViewById(R.id.main_recycler_view);
//        initDatabase();
    }

    @Override
    public void initData() {

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
//
//
//    private User initKobe() {
//        String userName = "Kobe";
//        int avatar = R.drawable.avatar;
//        int backgroundImage = R.drawable.background;
//        return new User(userName, avatar, backgroundImage);
//    }
//
//    private Moment initKobeMoment(int id, List<Integer> imageList, List<String> agreeList, List<Comment> commentList) {
//        database = UserDatabase.getDatabase(this);
//        User nowUser = database.userDao().selectUserById(id);
//        String userName = nowUser.getUserName();
//        int avatar = nowUser.getAvatar();
//        Date pubTime = null;
//        return new Moment(id, userName, avatar, "cool", pubTime, imageList, agreeList, commentList);
//    }
//
//    public void initDatabase() {
//        database = UserDatabase.getDatabase(this);
//        database.userDao().insertAll(initKobe());
//        List<Integer> imageList = Arrays.asList(R.drawable.background);
//        List<String> agreeList = Arrays.asList("nash", "jordan", "james");
//        List<Comment> commentList = Arrays.asList(new Comment("nash", "cool"), new Comment("james", "nash", "you too"));
//        User user = database.userDao().selectUserById(1);
//        int userId = user.getId();
//        database.momentDao().insertAll(initKobeMoment(userId, imageList, agreeList, commentList));
//    }

}
