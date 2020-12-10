package com.example.friendsapplication.view;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;

import com.example.friendsapplication.presenter.NoImageCreatePresenter;
import com.example.friendsapplication.R;
import com.example.friendsapplication.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoImageCreateActivity extends BaseActivity<NoImageCreatePresenter> {

    @BindView(R.id.title_publish_button)
    Button publishButton;
    @BindView(R.id.input_text)
    EditText inputText;

    @Override
    public void initView() {
        ButterKnife.bind(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        inputText.addTextChangedListener(new TextChangedWatcher());
        publishButton.setOnClickListener(new PublishButtonListener());
    }

    @Override
    public int getContentViewID() {
        return R.layout.activity_no_image_create;
    }

    @Override
    public NoImageCreatePresenter getPresenterListener() {
        return new NoImageCreatePresenter(this);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void onClick(View v) {

    }

    class TextChangedWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() == 0) {
                setPublishButtonUnable();
            } else {
                setPublishButtonAble();
            }
        }
    }

    class PublishButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String inputContent = String.valueOf(inputText.getText());
            getPresenterListener().updateNewNoImageMoment(inputContent);
            finish();
        }
    }

    public void setPublishButtonUnable() {
        publishButton.setClickable(false);
        publishButton.setTextColor(Color.rgb(192, 192, 192));
        publishButton.setBackgroundColor(Color.rgb(246, 247, 248));
    }

    public void setPublishButtonAble() {
        publishButton.setClickable(true);
        publishButton.setTextColor(Color.rgb(255, 255, 255));
        publishButton.setBackgroundColor(Color.rgb(50, 205, 50));
    }
}
