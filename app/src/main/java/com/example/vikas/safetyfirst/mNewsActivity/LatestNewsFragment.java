package com.example.vikas.safetyfirst.mNewsActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vikas.safetyfirst.R;
import com.example.vikas.safetyfirst.mData.News2;
import com.example.vikas.safetyfirst.mRecycler.NewsViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public abstract class LatestNewsFragment extends Fragment {
    private static final String TAG = "NewsListFragment";

    // [START define_database_reference]
    private DatabaseReference mDatabase;
    // [END define_database_reference]

    private FirebaseRecyclerAdapter<News2, NewsViewHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;

    public LatestNewsFragment() {}

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_latest_news, container, false);
        // [START create_database_reference]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END create_database_reference]

        mRecycler = (RecyclerView) rootView.findViewById(R.id.news_list);
      //  mRecycler.setHasFixedSize(true);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Set up Layout Manager, reverse layout
        mManager = new LinearLayoutManager(getActivity());
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);

        // Set up FirebaseRecyclerAdapter with the Query
        Query postsQuery = getQuery(mDatabase);


        mAdapter = new FirebaseRecyclerAdapter<News2, NewsViewHolder>(News2.class, R.layout.item_post,
                NewsViewHolder.class, postsQuery) {
            @Override
            protected void populateViewHolder(final NewsViewHolder viewHolder, final News2 model, final int position) {
                final DatabaseReference postRef = getRef(position);

                // Set click listener for the whole post view
                final String postKey = postRef.getKey();
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       /* // Launch PostDetailActivity
                        Intent intent = new Intent(getActivity(), DetailActivity.class);
                        intent.putExtra(DetailActivity.EXTRA_POST_KEY, postKey);
                        startActivity(intent);*/
                    }
                });


                // Bind Post to ViewHolder, setting OnClickListener for the star button
                viewHolder.bindToNews(model, new View.OnClickListener() {
                    @Override
                    public void onClick(View readmore) {
                        // Need to write to both places the post is stored
                     //   DatabaseReference globalPostRef = mDatabase.child("posts").child(postRef.getKey());
    //                    DatabaseReference userPostRef = mDatabase.child("user-posts").child(model.uid).child(postRef.getKey());

                        // Run two transactions
                       // onStarClicked(globalPostRef);
                      //  onStarClicked(userPostRef);
                    }
                },

                new View.OnClickListener(){

                    @Override
                    public void onClick(View  likeView) {
                        // Need to write to both places the post is stored
                     //   DatabaseReference globalPostRef = mDatabase.child("posts").child(postRef.getKey());
                        //                    DatabaseReference userPostRef = mDatabase.child("user-posts").child(model.uid).child(postRef.getKey());

                        // Run two transactions
                        // onStarClicked(globalPostRef);
                        //  onStarClicked(userPostRef);
                    }
                });
            }
        };
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mAdapter != null) {
            mAdapter.cleanup();
        }
    }

    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public abstract Query getQuery(DatabaseReference databaseReference);
}
