package com.example.vikas.safetyfirst.mFirebase;

/**
 * Created by Vikas on 10-07-2016.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import com.example.vikas.safetyfirst.mData.News;
import com.example.vikas.safetyfirst.mRecycler.MyAdapter;
import com.example.vikas.safetyfirst.mSwiper.SwipeHelper;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FireBaseClient {

    Context c;
    String DB_URL;
    RecyclerView rv;

    DatabaseReference fire;
    ArrayList<News> movies=new ArrayList<>();
    MyAdapter adapter;

    private ItemTouchHelper mItemTouchHelper;

    public FireBaseClient(Context c, String DB_URL, RecyclerView rv) {
        this.c = c;
        this.DB_URL = DB_URL;
        this.rv = rv;
        //INSTANTIATE
        fire= FirebaseDatabase.getInstance().getReference();
    }

    //SAVE
    public void saveOnline(String name,String url, String article, String source)
    {
        News m=new News();
        m.setHeadline(name);
        m.setImgSrc(url);
        m.setArticle(article);
        m.setArticlesrc(source);

        fire.child("News").push().setValue(m);
    }

    //RETRIEVE
    public void refreshData()
    {
        fire.addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("We're done loading the initial "+dataSnapshot.getChildrenCount()+" items");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        fire.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                getUpdates(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                getUpdates(dataSnapshot);
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

    private void getUpdates(DataSnapshot dataSnapshot)
    {
        movies.clear();
        for(DataSnapshot ds : dataSnapshot.getChildren())
        {
            News m=new News();
            m.setHeadline(ds.getValue(News.class).getHeadline());
            m.setImgSrc(ds.getValue(News.class).getImgSrc());
            m.setArticle(ds.getValue(News.class).getArticle());
            m.setArticlesrc(ds.getValue(News.class).getArticlesrc());
            movies.add(0, m);
        }

        if(movies.size()>0)
        {
            adapter=new MyAdapter(c,movies);
            rv.setAdapter(adapter);

            ItemTouchHelper.Callback callback = new SwipeHelper(adapter);
            ItemTouchHelper helper = new ItemTouchHelper(callback);
            helper.attachToRecyclerView(rv);

        }else {
            Toast.makeText(c,"No data",Toast.LENGTH_SHORT).show();
        }
    }

}
