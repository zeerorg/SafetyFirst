package com.vikas.dtu.safetyfirst2.mDiscussion.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.ValueEventListener;
import com.vikas.dtu.safetyfirst2.R;
import com.vikas.dtu.safetyfirst2.mData.Post;
import com.vikas.dtu.safetyfirst2.mDiscussion.PostDetailActivity;
import com.vikas.dtu.safetyfirst2.mLaws.LawsRecyclerViewAdapter;
import com.vikas.dtu.safetyfirst2.mRecycler.PostViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;
import com.vikas.dtu.safetyfirst2.mUser.UpdateProfile;
import com.vikas.dtu.safetyfirst2.mUser.UserProfileActivity;

import java.util.ArrayList;
import java.util.Collection;

import static com.vikas.dtu.safetyfirst2.mUtils.FirebaseUtil.getCurrentUserId;

public abstract class PostListFragment extends Fragment {

    private static final String TAG = "NewsListFragment";

    // [START define_database_reference]
    private DatabaseReference mDatabase;
    // [END define_database_reference]

    private  ProgressBar mProgressBar;
    private OnItemClickListener listener;
    ProgressBar rprogress;

    private boolean recentornot=false;
    private FirebaseRecyclerAdapter<Post, PostViewHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;
//    ArrayList<Post> postlist;
//    ArrayList<String> postkeys;

    public PostListFragment() {
    }

    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_all_posts, container, false);
         rprogress=(ProgressBar) rootView.findViewById(R.id.recycler_progress);
        // [START create_database_reference]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END create_database_reference]

        mRecycler = (RecyclerView) rootView.findViewById(R.id.messages_list);
        mProgressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);

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
        Query postsQuery = getQuery(mDatabase);

        recentornot=getBoolean();
        Myadapter adapter=new Myadapter(getActivity(),postsQuery);
        mRecycler.setAdapter(adapter);


//        Query postsQuery1 = getQuery(mDatabase);
//        mAdapter = new FirebaseRecyclerAdapter<Post, PostViewHolder>(Post.class, R.layout.item_post,
//                PostViewHolder.class, postsQuery1) {
//            @Override
//            protected void populateViewHolder(final PostViewHolder viewHolder, final Post model, final int position) {
//                Log.d("TAG123", String.valueOf(position));
//                mProgressBar.setVisibility(ProgressBar.INVISIBLE);
//
//                final DatabaseReference postRef = getRef(position);
//
//                // Set click listener for the whole post view
//                final String postKey = postRef.getKey();
//                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        // Launch PostDetailActivity
//                        Intent intent = new Intent(getActivity(), PostDetailActivity.class);
//                        intent.putExtra(PostDetailActivity.EXTRA_POST_KEY, postKey);
//                        startActivity(intent);
//                    }
//                });
//
//                // Determine if the current user has liked this post and set UI accordingly
//                if (model.stars.containsKey(getUid())) {
//                    viewHolder.starView.setImageResource(R.drawable.ic_toggle_star_24);
//                } else {
//                    viewHolder.starView.setImageResource(R.drawable.ic_toggle_star_outline_24);
//                }
//
//                //Set User Photo
//                if (model.getPhotoUrl() == null) {
//                    viewHolder.authorImage.setImageDrawable(ContextCompat.getDrawable(getContext(),
//                            R.drawable.ic_action_account_circle_40));
//                } else {
//                    Glide.with(getContext())
//                            .load(model.getPhotoUrl())
//                            .into(viewHolder.authorImage);
//                }
//
//                if (model.getImage() != null) {
//                    Glide.with(getContext())
//                            .load(model.getImage())
//                            .into(viewHolder.postImage);
//                    viewHolder.postImage.setVisibility(View.VISIBLE);
//                } else {
//                    viewHolder.postImage.setVisibility(View.GONE);
//                }
//
//                // Bind Post to ViewHolder, setting OnClickListener for the star round_blue_dark
//                viewHolder.bindToPost(model, new View.OnClickListener() {
//                    @Override
//                    public void onClick(View starView) {
//                        // Need to write to both places the post is stored
//                        DatabaseReference globalPostRef = mDatabase.child("posts").child(postRef.getKey());
//                        DatabaseReference userPostRef = mDatabase.child("user-posts").child(model.uid).child(postRef.getKey());
//
//                        // Run two transactions
//                        onStarClicked(globalPostRef);
//                        onStarClicked(userPostRef);
//                    }
//                });
//            }
//        };
//        mRecycler.setAdapter(mAdapter);

    }

    private class Myadapter extends RecyclerView.Adapter<PostViewHolder> {
        Context context;
        ArrayList<Post> postlist = new ArrayList<>();
        ArrayList<String> postkeys = new ArrayList<>();
        DatabaseReference mdatabaserefernce;
        String key;
        Post model;
        Query postquery;
        int offset=0;
        String mLastkey;
        ArrayList<Post> getPost=new ArrayList<>();
        ArrayList<Post> tempPost=new ArrayList<>();
        ArrayList<String> tempkeys=new ArrayList<>();
        ArrayList<String> getKeys=new ArrayList<>();
//        public interface OnItemClickListener{
//            void onItemClick(View v,int position);
//        }

        public Myadapter(Context context, Query postquery) {
            this.context = context;
            this.postquery=postquery;


            postquery.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    postlist.add(dataSnapshot.getValue(Post.class));
                    postkeys.add(dataSnapshot.getKey());
                    notifyItemInserted(postlist.size()-1);
                    if(postlist.size()==1){
                        mLastkey=dataSnapshot.getKey();}
                    Log.d("Tag11",dataSnapshot.getValue(Post.class).title);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            offset=offset+10;
        }

        public void getMoreData(final int value){

            tempkeys=postkeys;
            tempPost=postlist;

            Query postsQuery = mDatabase.child("posts").orderByKey().endAt(mLastkey).limitToLast(10);
            postsQuery.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    getPost.add(dataSnapshot.getValue(Post.class));
                    getKeys.add(dataSnapshot.getKey());
                    if(getPost.size()==10){
                        getPost.remove(9);
                        getKeys.remove(9);
                        postlist=getPost;
                        postkeys=getKeys;
                        for(int i=0;i<tempkeys.size();i++){
                            postlist.add(tempPost.get(i));
                            postkeys.add(tempkeys.get(i));
                        }
                        notifyItemRangeInserted(0,9);
                        getPost=new ArrayList<>();
                        getKeys=new ArrayList<>();
                        rprogress.setVisibility(View.GONE);
                    }
//                    notifyItemInserted(postlist.size()-1);
                    if(getPost.size()==1){
                        mLastkey=dataSnapshot.getKey();
                        Log.d("ttAG","changedkey");
                    }
                    Log.d("Tag11",dataSnapshot.getValue(Post.class).title);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        @Override
        public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View v = inflater.inflate(R.layout.item_post, parent, false);
            PostViewHolder holder = new PostViewHolder(v, new OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    key = postkeys.get(position);
                    Post model=postlist.get(position);
                    if(v.getId()==R.id.star){
//                        DatabaseReference globalPostRef = mDatabase.child("posts").child(postRef.getKey());
//                        DatabaseReference userPostRef = mDatabase.child("user-posts").child(model.uid).child(postRef.getKey());
                        DatabaseReference globalPostRef = mDatabase.child("posts").child(key);
                        DatabaseReference userPostRef = mDatabase.child("user-posts").child(model.uid).child(key);

                        // Run two transactions
                        onStarClicked(globalPostRef);
                        onStarClicked(userPostRef);
                    }else{
                        Intent intent = new Intent(context, PostDetailActivity.class);
                        intent.putExtra(PostDetailActivity.EXTRA_POST_KEY, key);
                        startActivity(intent);
                    }
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(PostViewHolder holder, int position) {
            Log.d("TAG11",String.valueOf(position));
            mProgressBar.setVisibility(ProgressBar.INVISIBLE);

            model = postlist.get(position);
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(context, PostDetailActivity.class);
//                    intent.putExtra(PostDetailActivity.EXTRA_POST_KEY, key);
//                    startActivity(intent);
//                }
//            });

            if (model.stars.containsKey(getUid())) {
                holder.starView.setImageResource(R.drawable.ic_toggle_star_24);
            } else {
                holder.starView.setImageResource(R.drawable.ic_toggle_star_outline_24);
            }


            if (model.getPhotoUrl() == null) {
                holder.authorImage.setImageDrawable(ContextCompat.getDrawable(getContext(),
                        R.drawable.ic_action_account_circle_40));
            } else {
                Glide.with(getContext())
                        .load(model.getPhotoUrl())
                        .into(holder.authorImage);
            }


            if (model.getImage() != null) {
                Glide.with(getContext())
                        .load(model.getImage())
                        .into(holder.postImage);
                holder.postImage.setVisibility(View.VISIBLE);
            } else {
                holder.postImage.setVisibility(View.GONE);
            }

            holder.bindToPost(model);
//            holder.bindToPost(model, new View.OnClickListener() {
//                @Override
//                public void onClick(View starView) {
//
//                    // Need to write to both places the post is stored
//                    DatabaseReference globalPostRef = mDatabase.child("posts").child(key);
//                    DatabaseReference userPostRef = mDatabase.child("user-posts").child(model.uid).child(key);
//
//                    // Run two transactions
//                    onStarClicked(globalPostRef);
//                    onStarClicked(userPostRef);
//                }
//            }, new View.OnClickListener() {
//                @Override
//                public void onClick(View authorView) {
//                    Intent userDetailIntent = new Intent(getContext(), UserProfileActivity.class);
//                    userDetailIntent.putExtra(UserProfileActivity.USER_ID_EXTRA_NAME,
//                            model.getPostAuthorUID());
//
//                    startActivity(userDetailIntent);
//
//                }
//            });

            if(position==0&&recentornot==true){
                rprogress.setVisibility(View.VISIBLE);
                getMoreData(postlist.size());
            }
        }



        @Override
        public int getItemCount() {
            return postlist.size();
        }
    }


    // [START post_stars_transaction]
    private void onStarClicked(DatabaseReference postRef) {
        postRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                Post p = mutableData.getValue(Post.class);
                if (p == null) {
                    return Transaction.success(mutableData);
                }

                if (p.stars.containsKey(getUid())) {
                    // Unstar the post and remove self from stars
                    p.starCount = p.starCount - 1;
                    p.stars.remove(getUid());
                } else {
                    // Star the post and add self to stars
                    p.starCount = p.starCount + 1;
                    p.stars.put(getUid(),true);
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

    public  Query getQuery(DatabaseReference databaseReference){
        Query postquery=mDatabase.child("posts").orderByKey().limitToLast(10);
        return postquery;
    };
    public abstract Boolean getBoolean();

}
