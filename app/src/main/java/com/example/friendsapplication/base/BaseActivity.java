package com.example.friendsapplication.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity<P extends BasePresenter > extends AppCompatActivity implements View.OnClickListener {

    private P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewID());
        initService();
        initData();
        initView();
        initListener();
        mPresenter = getPresenterListener();
        mPresenter.bindView(this);
    }

    public abstract void initView();
    public abstract void initData();
    public abstract void initService();
    public abstract void initListener();
    public abstract int getContentViewID();
    public abstract P getPresenterListener();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();
    }

    public abstract void destroy();
}
