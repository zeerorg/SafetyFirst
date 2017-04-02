package com.vikas.dtu.safetyfirst2.mRecycler;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vikas.dtu.safetyfirst2.R;
import com.vikas.dtu.safetyfirst2.mData.Post;
import com.vikas.dtu.safetyfirst2.mDiscussion.fragment.PostListFragment;


public class PostViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{

    public TextView titleView;
    public TextView authorView;
    public ImageView starView;
    public TextView numStarsView;
    public TextView bodyView;
    public ImageView authorImage;
    public ImageView postImage;
    PostListFragment.OnItemClickListener listener;


    public PostViewHolder(View itemView, PostListFragment.OnItemClickListener listener) {
        super(itemView);
        this.listener=listener;
        authorImage = (ImageView) itemView.findViewById(R.id.post_author_photo);
        titleView = (TextView) itemView.findViewById(R.id.post_title);
        authorView = (TextView) itemView.findViewById(R.id.post_author);
        starView = (ImageView) itemView.findViewById(R.id.star);
        numStarsView = (TextView) itemView.findViewById(R.id.post_num_stars);
        bodyView = (TextView) itemView.findViewById(R.id.post_body);
        postImage = (ImageView) itemView.findViewById(R.id.post_image);
        itemView.setOnClickListener(this);
        starView.setOnClickListener(this);
    }

    public void bindToPost(Post post) {
        titleView.setText(post.title);
        authorView.setText(post.author);
        numStarsView.setText(String.valueOf(post.starCount));
        if(post.xmlBody == null)
            bodyView.setText(post.body);
        else
            bodyView.setText(Html.fromHtml(post.xmlBody));

        bodyView.setMaxLines(6);
        bodyView.setEllipsize(TextUtils.TruncateAt.END);
//        starView.setOnClickListener(this);

        //   authorView.setOnClickListener(authorClickListener);
        //  authorImage.setOnClickListener(authorClickListener);
    }



    @Override
    public void onClick(View v) {
        listener.onItemClick(v,this.getLayoutPosition());
    }
}
