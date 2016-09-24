package com.vikas.dtu.safetyfirst.mRecycler;

import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vikas.dtu.safetyfirst.R;
import com.vikas.dtu.safetyfirst.mData.Post;

import de.hdodenhof.circleimageview.CircleImageView;



public class PostViewHolder extends RecyclerView.ViewHolder {

    public TextView titleView;
    public TextView authorView;
    public ImageView starView;
    public TextView numStarsView;
    public TextView bodyView;
    public ImageView authorImage;

    public PostViewHolder(View itemView) {
        super(itemView);

        authorImage = (ImageView) itemView.findViewById(R.id.post_author_photo);
        titleView = (TextView) itemView.findViewById(R.id.post_title);
        authorView = (TextView) itemView.findViewById(R.id.post_author);
        starView = (ImageView) itemView.findViewById(R.id.star);
        numStarsView = (TextView) itemView.findViewById(R.id.post_num_stars);
        bodyView = (TextView) itemView.findViewById(R.id.post_body);
    }

    public void bindToPost(Post post, View.OnClickListener starClickListener) {


        titleView.setText(post.title);
        authorView.setText(post.author);
        numStarsView.setText(String.valueOf(post.starCount));
        bodyView.setText(post.body);
        bodyView.setMaxLines(6);
        bodyView.setEllipsize(TextUtils.TruncateAt.END);

        starView.setOnClickListener(starClickListener);
    }
}
