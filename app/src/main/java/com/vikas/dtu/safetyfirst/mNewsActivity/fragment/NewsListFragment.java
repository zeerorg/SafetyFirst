package com.vikas.dtu.safetyfirst.mNewsActivity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vikas.dtu.safetyfirst.R;
import com.vikas.dtu.safetyfirst.mData.News;
import com.vikas.dtu.safetyfirst.mData.User;
import com.vikas.dtu.safetyfirst.mNewsActivity.NewsDetailActivity;
import com.vikas.dtu.safetyfirst.mNewsActivity.viewholder.NewsViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;


public abstract class NewsListFragment extends Fragment {

    private static final String TAG = "NewsListFragment";

    // [START define_database_reference]
    private DatabaseReference mDatabase;
    // [END define_database_reference]

    private FirebaseRecyclerAdapter<News, NewsViewHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;

    public NewsListFragment() {}

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_all_news, container, false);

        // [START create_database_reference]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END create_database_reference]

        mRecycler = (RecyclerView) rootView.findViewById(R.id.messages_list);
        mRecycler.setHasFixedSize(true);

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
        Query newsQuery = getQuery(mDatabase);
        mAdapter = new FirebaseRecyclerAdapter<News, NewsViewHolder>(News.class, R.layout.item_news,
                NewsViewHolder.class, newsQuery) {
            @Override
            protected void populateViewHolder(final NewsViewHolder viewHolder, final News model, final int position) {
                final DatabaseReference newsRef = getRef(position);

                // Set click listener for the whole news view
                final String newsKey = newsRef.getKey();
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Launch NewsDetailActivity
                        Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                        intent.putExtra(NewsDetailActivity.EXTRA_NEWS_KEY, newsKey);
                        startActivity(intent);
                    }
                });

// Determine if the current user has liked this post and set UI accordingly
                if (model.stars.containsKey(getUid())) {
                    viewHolder.bookmark.setImageResource(R.drawable.bookmark_toggle);
                } else {
                    viewHolder.bookmark.setImageResource(R.drawable.bookmark);
                }

                // Bind News to ViewHolder, setting OnClickListener for the star button
                viewHolder.bindToNews(model, new View.OnClickListener() {
                    @Override
                    public void onClick(View starView) {

                        final String[] title = new String[1];
                        final String[] body = new String[1];
                        final String[] author = new String[1];
                        final String[] image = new String[1];

                        DatabaseReference mPostReference = FirebaseDatabase.getInstance().getReference()
                                .child("news").child(newsKey);
                        ValueEventListener postListener = new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                // Get News object and use the values to update the UI
                                News news = dataSnapshot.getValue(News.class);
                                // [START_EXCLUDE]
//                mAuthorView.setText(news.author);
                                title[0] = news.title;
                                body[0] = news.body;
                                author[0] = news.author;
                                image[0] = news.imgUrl;
                                // [END_EXCLUDE]
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                // Getting News failed, log a message
                                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                                // [START_EXCLUDE]
                                Toast.makeText(getActivity().getApplicationContext(), "Failed to load news.",
                                        Toast.LENGTH_SHORT).show();
                                // [END_EXCLUDE]
                            }
                        };

                        mPostReference.addValueEventListener(postListener);

                        // [START single_value_read]
                        final String userId = getUid();
                        mDatabase.child("users").child(userId).addListenerForSingleValueEvent(
                                new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        // Get user value
                                        User user = dataSnapshot.getValue(User.class);

                                        // [START_EXCLUDE]
                                        if (user == null) {
                                            // User is null, error out
                                            Log.e(TAG, "User " + userId + " is unexpectedly null");
                                            Toast.makeText(getActivity().getApplicationContext(),
                                                    "Error: could not fetch user.",
                                                    Toast.LENGTH_SHORT).show();
                                        } else {
                                            // Write new post
                                            writeNewNews(newsRef.getKey(), userId, author[0], user.username, title[0], body[0], image[0]);
                                        }
                                        // [END_EXCLUDE]
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                        Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                                    }
                                });
                        // [END single_value_read]

                        // Need to write to both places the post is stored
                        DatabaseReference globalPostRef = mDatabase.child("news").child(newsRef.getKey());
                        DatabaseReference userPostRef = mDatabase.child("user-news").child(model.uid).child(newsRef.getKey());

                        // Run two transactions
                        onBookmarked(globalPostRef);
                        onBookmarked(userPostRef);


                    }
                }, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                         DatabaseReference  mPostReference = FirebaseDatabase.getInstance().getReference()
                                .child("news").child(newsKey);


                        Toast.makeText(getContext()," ", Toast.LENGTH_SHORT).show();
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, "");
                        sendIntent.setType("text/plain");
                        startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
                    }
                });
            }
        };
        mRecycler.setAdapter(mAdapter);
    }

    // [START news_stars_transaction]
    private void onBookmarked(DatabaseReference newsRef) {
        newsRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                News p = mutableData.getValue(News.class);
                if (p == null) {
                    return Transaction.success(mutableData);
                }

                if (p.stars.containsKey(getUid())) {
                    // Unstar the news and remove self from stars
                    p.starCount = p.starCount - 1;
                    p.stars.remove(getUid());
                } else {
                    // Star the post and add self to stars
                    p.starCount = p.starCount + 1;

                    p.stars.put(getUid(), true);
                }

                // Set value and report transaction success
                mutableData.setValue(p);
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b,
                                   DataSnapshot dataSnapshot) {
                // Transaction completed
                Log.d(TAG, "postTransaction:onComplete:" + databaseError);
              //  Toast.makeText(getActivity().getApplicationContext(), "complete", Toast.LENGTH_SHORT).show();
            }
        });
    }
    // [END post_stars_transaction]

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


    // [START write_fan_out]
    private void writeNewNews(String key, String userId, String author, String username, String title, String body, String image) {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        News news = new News(userId,author, title, body, image);
        Map<String, Object> newsValues = news.toMap();


        Map<String, Object> childUpdates = new HashMap<>();
        //TODO this may be a source of error
      //  childUpdates.put("/news/" + key, newsValues);
        childUpdates.put("/user-news/" + userId + "/" + key, newsValues);

        mDatabase.updateChildren(childUpdates);
    }
    // [END write_fan_out]

    //TODO remove value event listeners

}
