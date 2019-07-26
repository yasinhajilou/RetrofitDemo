package com.example.retrofitdemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitdemo.R;
import com.example.retrofitdemo.model.Comment;

import java.util.List;

public class CommentsRecyclerAdapter extends RecyclerView.Adapter<CommentsRecyclerAdapter.CommentViewHolder> {

    List<Comment> mComments;

    public void setData(List<Comment> data){
        mComments = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mComments != null ? mComments.size() : 0;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail , parent , false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        holder.bind(mComments.get(position));
    }


     class CommentViewHolder extends RecyclerView.ViewHolder {
        TextView tvName , tvBody , tvEmail;
         CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvItemName);
            tvBody = itemView.findViewById(R.id.tvItemBody);
            tvEmail = itemView.findViewById(R.id.tvItemEmail);
        }
        void bind(Comment comment){
             tvName.setText(comment.getName());
             tvEmail.setText(comment.getEmail());
             tvBody.setText(comment.getBody());
        }
    }
}
