package com.example.retrofitdemo.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    MutableLiveData<Comment> mData = new MutableLiveData<>();

    public void select(Comment item) {
        mData.setValue(item);
    }

    public LiveData<Comment> getSelected() {
        return mData;
    }
}
