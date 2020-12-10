package com.example.friendsapplication.presenter;

import com.example.friendsapplication.base.BasePresenter;
import com.example.friendsapplication.model.ImageCreateModel;
import com.example.friendsapplication.view.ImageCreateActivity;

public class ImageCreatePresenter extends BasePresenter<ImageCreateModel, ImageCreateActivity> {

    private ImageCreateActivity mImageCreateActivity;
    private ImageCreateModel mImageCreateModel;

    public ImageCreatePresenter(ImageCreateActivity mImageCreateActivity) {
        this.mImageCreateModel = getModelInstance();
        this.mImageCreateActivity = mImageCreateActivity;
    }

    @Override
    public ImageCreateModel getModelInstance() {
        return new ImageCreateModel(this);
    }


}
