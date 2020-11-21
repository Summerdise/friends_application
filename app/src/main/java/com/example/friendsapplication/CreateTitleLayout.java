package com.example.friendsapplication;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateTitleLayout extends ConstraintLayout {

    @BindView(R.id.title_cancel_button)
    Button titleCancelBtn;
    @BindView(R.id.title_publish_button)
    Button titlePublishBtn;


    public CreateTitleLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.create_title,this);
        ButterKnife.bind(this);
        titleCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });
    }
}
