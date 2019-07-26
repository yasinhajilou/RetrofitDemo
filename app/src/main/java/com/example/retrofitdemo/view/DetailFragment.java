package com.example.retrofitdemo.view;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitdemo.R;
import com.example.retrofitdemo.adapter.CommentsRecyclerAdapter;
import com.example.retrofitdemo.model.Comment;
import com.example.retrofitdemo.model.MyViewModel;
import com.example.retrofitdemo.model.Post;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    MyViewModel mModel;
    RecyclerView mRecyclerView;
    CommentsRecyclerAdapter mAdapter;
    TextView tvPost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        mRecyclerView = view.findViewById(R.id.rvDetail);
        tvPost = view.findViewById(R.id.tvDetailFragment);

        mModel = ViewModelProviders.of(getActivity()).get(MyViewModel.class);

        setUpRecycler();

        mModel.getSelected().observe(this, new Observer<List<Comment>>() {
            @Override
            public void onChanged(List<Comment> comments) {
                mAdapter.setData(comments);
            }
        });

        mModel.getSelectedPost().observe(getViewLifecycleOwner(), new Observer<Post>() {
            @Override
            public void onChanged(Post post) {
                tvPost.setText(post.getTitle());
            }
        });
        return view;
    }

    private void setUpRecycler() {
        mAdapter = new CommentsRecyclerAdapter();
        mRecyclerView.setAdapter(mAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration decoration = new DividerItemDecoration(getContext() ,layoutManager.getOrientation());
        mRecyclerView.addItemDecoration(decoration);
    }
}
