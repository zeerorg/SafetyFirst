package com.example.vikas.safetyfirst.mRecycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vikas.safetyfirst.R;
import com.example.vikas.safetyfirst.mData.News2;

/**
 * Created by Vikas on 03-09-2016.
 */
public class NewsViewHolder extends RecyclerView.ViewHolder {

    TextView headlineTxt;
    ImageView img;
    Button readMoreBtn;
    ImageButton likeBtn;
    TextView articleTxt;

    public NewsViewHolder(View itemView) {
        super(itemView);
        headlineTxt = (TextView) itemView.findViewById(R.id.nameTxt);
        img= (ImageView) itemView.findViewById(R.id.movieImage);
        readMoreBtn= (Button) itemView.findViewById(R.id.readMoreBtn);
        articleTxt= (TextView) itemView.findViewById(R.id.articleTxt);
        likeBtn = (ImageButton) itemView.findViewById(R.id.favorite_button);
    }

    public void bindToNews(News2 news, View.OnClickListener readmoreClickListener, View.OnClickListener likeClickListener) {
        headlineTxt.setText(news.headline);
        //img.setText(news.img);
        articleTxt.setText(news.article);


        readMoreBtn.setOnClickListener(readmoreClickListener);
        likeBtn.setOnClickListener(likeClickListener);
    }
}
