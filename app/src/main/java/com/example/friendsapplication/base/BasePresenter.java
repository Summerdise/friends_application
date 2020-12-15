package com.example.friendsapplication.base;


public abstract class BasePresenter<M extends BaseModel, V extends BaseActivity> {

    private M mModel;
    private V mView;

    public BasePresenter() {
        this.mModel = getModelInstance();
    }


    public void bindView(V mView){
        this.mView = mView;
    }

    public void unBindView(){
        this.mView = null;
    }

    public abstract M getModelInstance();
}
