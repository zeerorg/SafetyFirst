package com.vikas.dtu.safetyfirst2.mNewsActivity.fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class MyNewsFragment extends NewsListFragment {

    public MyNewsFragment() {}

    @Override
    public Query getQuery(DatabaseReference databaseReference) {

        return databaseReference.child("user-news").child(getUid());
    }
}
