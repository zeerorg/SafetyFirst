package com.example.vikas.safetyfirst.mDiscussion.fragment;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class SearchPostFragment extends PostListFragment {

    private String query;
    String[] keywords;

    public static SearchPostFragment newInstance(String query) {
        SearchPostFragment postFragment = new SearchPostFragment();
        postFragment.query = query;
        return postFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getContext(), query, Toast.LENGTH_SHORT).show();
        keywords = query.split(" ");
    }

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        return databaseReference.child("user-posts").child(getUid());
    }


}
