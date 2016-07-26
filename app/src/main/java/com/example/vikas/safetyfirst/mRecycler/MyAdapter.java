package com.example.vikas.safetyfirst.mRecycler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vikas.safetyfirst.R;
import com.example.vikas.safetyfirst.mData.News;
import com.example.vikas.safetyfirst.mInterface.ItemClickListener;
import com.example.vikas.safetyfirst.mPicasso.PicassoClient;
import com.example.vikas.safetyfirst.mNewsActivity.DetailActivity;

import java.util.ArrayList;

/**
 * Created by Vikas on 10-07-2016.
 */

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    Context c;
    ArrayList<News> news;

    public MyAdapter(Context c, ArrayList<News> news) {

        this.c = c;
        this.news = news;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.model, null);

        MyHolder holder = new MyHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {

        holder.headlineTxt.setText( news.get(position).getHeadline());
        holder.articleTxt.setText(news.get(position).getArticle());
        if(news.get(position).getImgSrc()!=null){
            holder.img .setVisibility(View.VISIBLE);
            PicassoClient.downloadImage(c,news.get(position).getImgSrc(),holder.img);
        }


        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Intent intent = new Intent(c, DetailActivity.class);
                intent.putExtra("Headline", news.get(position).getHeadline());
                intent.putExtra("Article", news.get(position).getArticle());

                String transitionName = "hello";

                View viewstart = holder.cardView;
                ActivityOptionsCompat options =

                        ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) c,
                                viewstart,   // Starting view
                                transitionName    // The String
                        );
                ActivityCompat.startActivity((Activity) c, intent, options.toBundle());
            //    c.startActivity(intent);
               }
        });

holder.likeBtn.setOnClickListener(new View.OnClickListener()
{
    @Override
    public void onClick(View v)
    {

    }
});
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public void dismissNews(int position){
news.remove(position);
        this.notifyItemRemoved(position);
    }

}