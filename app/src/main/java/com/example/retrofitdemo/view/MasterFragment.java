package com.example.retrofitdemo.view;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.retrofitdemo.MyCallback;
import com.example.retrofitdemo.RecyclerItemClickListener;
import com.example.retrofitdemo.adapter.PostRecyclerAdapter;
import com.example.retrofitdemo.R;
import com.example.retrofitdemo.model.Comment;
import com.example.retrofitdemo.model.MyViewModel;
import com.example.retrofitdemo.model.Post;
import com.example.retrofitdemo.model.Repo;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MasterFragment extends Fragment implements RecyclerItemClickListener {

    NavController mNavController;
    RecyclerView mRecyclerView;
    private Repo mRepo;
    MyViewModel mModel;
    private PostRecyclerAdapter postRecyclerAdapter;

    public MasterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_master, container, false);
        mModel = ViewModelProviders.of(getActivity()).get(MyViewModel.class);
        mRecyclerView = view.findViewById(R.id.rvMaster);
        setUpRecycler();
        mRepo = Repo.getInstance();
        mRepo.getPost(new MyCallback<List<Post>>() {
            @Override
            public void onSuccessful(List<Post> data) {
                postRecyclerAdapter.setData(data);
            }

            @Override
            public void onError(String message) {
                Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    private void setUpRecycler() {
        postRecyclerAdapter = new PostRecyclerAdapter(this);
        mRecyclerView.setAdapter(postRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mNavController = NavHostFragment.findNavController(this);
    }

    @Override
    public void onClick(final int postId) {
//        mNavController.navigate(R.id.action_masterFragment_to_detailFragment,bundle);
        mRepo.getComments(postId, new MyCallback<List<Comment>>() {
            @Override
            public void onSuccessful(List<Comment> data) {
                if (data != null) {
                    mModel.select(data.get(0));
                    mNavController.navigate(R.id.action_masterFragment_to_detailFragment);
                }else {
                    Toast.makeText(getContext(), "it is null", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(String message) {
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
