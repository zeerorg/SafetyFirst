package com.vikas.dtu.safetyfirst2.mNewsActivity.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.firebase.database.ChildEventListener;
import com.vikas.dtu.safetyfirst2.R;
import com.vikas.dtu.safetyfirst2.mData.News;
import com.vikas.dtu.safetyfirst2.mData.Post;
import com.vikas.dtu.safetyfirst2.mNewsActivity.NewsDetailActivity;
import com.vikas.dtu.safetyfirst2.mNewsActivity.viewholder.NewsViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public abstract class NewsListFragment extends Fragment {

    private static final String TAG = "NewsListFragment";

    // [START define_database_reference]
    private DatabaseReference mDatabase;
    // [END define_database_reference]

    private ProgressBar mProgressBar;
    private NewsRecyclerAdapter mrecycleradapter;
//    private FirebaseRecyclerAdapter<News, NewsViewHolder> mAdapter;
    private RecyclerView mRecycler;
    private ProgressBar mpaginateprogbar;
    private LinearLayoutManager mManager;

    public NewsListFragment() {}
    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_all_news, container, false);

        // [START create_database_reference]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END create_database_reference]
        mpaginateprogbar=(ProgressBar) rootView.findViewById(R.id.newspaginateprogbar);

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
        Query newsQuery =  mDatabase.child("news").orderByKey().limitToLast(10);

        mrecycleradapter=new NewsRecyclerAdapter(getContext(),newsQuery);
        mRecycler.setAdapter(mrecycleradapter);
//        mAdapter = new FirebaseRecyclerAdapter<News, NewsViewHolder>(News.class, R.layout.item_news,
//                NewsViewHolder.class, newsQuery) {
//            @Override
//            protected void populateViewHolder(final NewsViewHolder viewHolder, final News model, final int position) {
//
//                mProgressBar.setVisibility(ProgressBar.INVISIBLE);
//
//                final DatabaseReference newsRef = getRef(position);
//
//                // Set click listener for the whole news view
//                final String newsKey = newsRef.getKey();
//                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        // Launch NewsDetailActivity
//                        Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
//                        intent.putExtra(NewsDetailActivity.EXTRA_NEWS_KEY, newsKey);
//                        startActivity(intent);
//                    }
//                });
//
//                viewHolder.bindToNews(model,
//
//                        getContext());
//            }
//        };

    }



    @Override
    public void onDestroy() {
        super.onDestroy();
//        if (mrecycleradapter != null) {
//            mrecycleradapter.;
//        }
    }

    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public abstract Query getQuery(DatabaseReference databaseReference);

    private class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsViewHolder>{

        ArrayList<News> newsArrayList=new ArrayList<>();
        ArrayList<String> newsArrayKey=new ArrayList<>();
        Context ctx;
        Query newsquery;
        String mLastkey;
        ArrayList<News> getNews=new ArrayList<>();
        ArrayList<News> tempNews=new ArrayList<>();
        ArrayList<String> tempkeys=new ArrayList<>();
        ArrayList<String> getKeys=new ArrayList<>();
        public NewsRecyclerAdapter(Context ctx,Query newsquery){
            this.ctx=ctx;
            this.newsquery=newsquery;
            newsquery.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    newsArrayKey.add(dataSnapshot.getKey());
                    newsArrayList.add(dataSnapshot.getValue(News.class));
                    notifyItemInserted(newsArrayList.size()-1);
                    if(newsArrayList.size()==1){
                        mLastkey=dataSnapshot.getKey();
                    }

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

        public void getMoreData(){
            tempkeys=newsArrayKey;
            tempNews=newsArrayList;
            Query Getmorenewsquery=mDatabase.child("news").orderByKey().endAt(mLastkey).limitToLast(10);
            Getmorenewsquery.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    getNews.add(dataSnapshot.getValue(News.class));
                    getKeys.add(dataSnapshot.getKey());
                    if(getNews.size()==10){
                        getNews.remove(9);
                        getKeys.remove(9);
                        newsArrayList=getNews;
                        newsArrayKey=getKeys;
                        for(int i=0;i<tempNews.size();i++){
                            newsArrayList.add(tempNews.get(i));
                            newsArrayKey.add(tempkeys.get(i));
                        }
                        notifyItemRangeInserted(0,9);
                        mpaginateprogbar.setVisibility(View.GONE);
                        getKeys=new ArrayList<>();
                        getNews=new ArrayList<>();
                    }
                    if(getNews.size()==1){
                        mLastkey=dataSnapshot.getKey();
                    }


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
        public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater=LayoutInflater.from(ctx);
            View v=inflater.inflate(R.layout.item_news,parent,false);
            NewsViewHolder holder=new NewsViewHolder(v, new OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                    intent.putExtra(NewsDetailActivity.EXTRA_NEWS_KEY, newsArrayKey.get(position));
                    startActivity(intent);
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(NewsViewHolder holder, final int position) {

            mProgressBar.setVisibility(ProgressBar.INVISIBLE);
            // Set click listener for the whole news view
//           holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // Launch NewsDetailActivity
//
//                }
//            });
            holder.bindToNews(newsArrayList.get(position),
                    getContext());
            if (position==0){
                mpaginateprogbar.setVisibility(View.VISIBLE);
                getMoreData();
            }
        }

        @Override
        public int getItemCount() {
            return newsArrayList.size();
        }
    }

}
