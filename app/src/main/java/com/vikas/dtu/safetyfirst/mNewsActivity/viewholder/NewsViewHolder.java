package com.vikas.dtu.safetyfirst.mNewsActivity.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vikas.dtu.safetyfirst.R;
import com.vikas.dtu.safetyfirst.mData.News;


public class NewsViewHolder extends RecyclerView.ViewHolder {

    public TextView titleView;
    public TextView authorView;
    public ImageView bookmark;
    public ImageView share;
    public TextView bodyView;

    public NewsViewHolder(View itemView) {
        super(itemView);

        titleView = (TextView) itemView.findViewById(R.id.post_title);
     //   authorView = (TextView) itemView.findViewById(R.id.post_author);
        bodyView = (TextView) itemView.findViewById(R.id.post_body);
        bookmark = (ImageView) itemView.findViewById(R.id.bookmark);
        share = (ImageView) itemView.findViewById(R.id.share);

    }

    public void bindToNews(News news, View.OnClickListener starClickListener, View.OnClickListener shareClickListener) {
        titleView.setText(news.title);
        bodyView.setText(news.body);
        bookmark.setOnClickListener(starClickListener);
        share.setOnClickListener(shareClickListener);
//
    }
}
