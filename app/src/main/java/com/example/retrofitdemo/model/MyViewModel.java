package com.example.retrofitdemo.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MyViewModel extends ViewModel {
    MutableLiveData<List<Comment>> mData = new MutableLiveData<>();
    MutableLiveData<Post> mPostMutableLiveData = new MutableLiveData<>();

    public void select(List<Comment> items) {
        mData.setValue(items);
    }

    public LiveData<List<Comment>> getSelected() {
        return mData;
    }

    public void selectPost(Post post){
        mPostMutableLiveData.setValue(post);
    }

    public LiveData<Post> getSelectedPost(){
        return mPostMutableLiveData;
    }
}
