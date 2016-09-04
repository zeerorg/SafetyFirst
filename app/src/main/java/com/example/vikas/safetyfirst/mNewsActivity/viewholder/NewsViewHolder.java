package com.example.vikas.safetyfirst.mNewsActivity.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vikas.safetyfirst.R;
import com.example.vikas.safetyfirst.mData.News;


public class NewsViewHolder extends RecyclerView.ViewHolder {

    public TextView titleView;
    public TextView authorView;
    public ImageView starView;
    public TextView bodyView;

    public NewsViewHolder(View itemView) {
        super(itemView);

        titleView = (TextView) itemView.findViewById(R.id.post_title);
     //   authorView = (TextView) itemView.findViewById(R.id.post_author);
        bodyView = (TextView) itemView.findViewById(R.id.post_body);
    }

    public void bindToPost(News news, View.OnClickListener starClickListener) {
        titleView.setText(news.title);


        bodyView.setText(news.body);

//        starView.setOnClickListener(starClickListener);
    }
}
