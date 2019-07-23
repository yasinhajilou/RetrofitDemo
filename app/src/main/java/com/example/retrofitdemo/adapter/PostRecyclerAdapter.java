package com.example.retrofitdemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitdemo.R;
import com.example.retrofitdemo.RecyclerItemClickListener;
import com.example.retrofitdemo.model.Post;

import java.util.List;

public class PostRecyclerAdapter extends RecyclerView.Adapter<PostRecyclerAdapter.ViewHolder> {

    RecyclerItemClickListener mRecyclerItemClickListener;
    List<Post> mPosts;

    public PostRecyclerAdapter(RecyclerItemClickListener recyclerItemClickListener) {
        mRecyclerItemClickListener = recyclerItemClickListener;
    }

    public void setData(List<Post> posts) {
        mPosts = posts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_master, parent, false);
        return new ViewHolder(view,mRecyclerItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PostRecyclerAdapter.ViewHolder holder, int position) {
        holder.bind(mPosts.get(position));
    }

    @Override
    public int getItemCount() {
        return mPosts != null ? mPosts.size() : 0;
    }

     class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTitle;
        RecyclerItemClickListener mListener;
         ViewHolder(@NonNull View itemView , RecyclerItemClickListener recyclerItemClickListener) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
             mListener = recyclerItemClickListener;
             itemView.setOnClickListener(this);
        }

        void bind(Post post){
             tvTitle.setText(post.getTitle());
        }

         @Override
         public void onClick(View view) {
             mListener.onClick(mPosts.get(getAdapterPosition()).getId());
         }
     }
}
