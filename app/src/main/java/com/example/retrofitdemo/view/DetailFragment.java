package com.example.retrofitdemo.view;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofitdemo.R;
import com.example.retrofitdemo.model.Comment;
import com.example.retrofitdemo.model.MyViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    MyViewModel mModel;
    TextView tvName,tvEmail , tvBody;
    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        tvName = view.findViewById(R.id.tvName);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvBody = view.findViewById(R.id.tvBody);

        mModel = ViewModelProviders.of(getActivity()).get(MyViewModel.class);
        mModel.getSelected().observe(this, new Observer<Comment>() {
            @Override
            public void onChanged(Comment comment) {
                tvName.setText(comment.getName());
                tvBody.setText(comment.getBody());
                tvEmail.setText(comment.getEmail());
            }
        });
        return view;
    }

}
