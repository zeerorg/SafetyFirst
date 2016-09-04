package com.example.vikas.safetyfirst.mNewsActivity.fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class RecentNewsFragment extends NewsListFragment {

    public RecentNewsFragment() {}

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        // [START recent_posts_query]
        // Last 100 posts, these are automatically the 100 most recent
        // due to sorting by push() keys
        Query recentPostsQuery = databaseReference.child("news")
                .limitToFirst(100);
        // [END recent_posts_query]

        return recentPostsQuery;
    }
}
