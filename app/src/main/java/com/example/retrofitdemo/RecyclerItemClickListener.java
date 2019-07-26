package com.example.retrofitdemo;

import com.example.retrofitdemo.model.Comment;
import com.example.retrofitdemo.model.Post;

public interface RecyclerItemClickListener {
    void onClick(int postId , Post post);
}
