package com.example.friendsapplication.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.friendsapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TitleLayout extends ConstraintLayout {


    @BindView(R.id.title_back_button)
    ImageButton titleBackBtn;
    @BindView(R.id.title_photo_button)
    ImageButton titlePhotoBtn;


    public TitleLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title,this);
        ButterKnife.bind(this);
        titleBackBtn.setOnClickListener(v -> ((Activity) getContext()).finish());
        titlePhotoBtn.setOnClickListener((OnClickListener) v -> {
            Intent intent = new Intent((Activity) getContext(),ImageCreateActivity.class);
            context.startActivity(intent);
        });
        titlePhotoBtn.setOnLongClickListener((OnLongClickListener) v -> {
            Intent intent = new Intent((Activity) getContext(), NoImageCreateActivity.class);
            context.startActivity(intent);
            return false;
        });
    }
}
