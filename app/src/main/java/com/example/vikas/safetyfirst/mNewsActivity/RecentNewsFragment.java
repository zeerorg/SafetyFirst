package com.example.vikas.safetyfirst.mNewsActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/**
 * Created by Vikas on 03-09-2016.
 */
public class RecentNewsFragment extends LatestNewsFragment {

    @Override
    public Query getQuery(DatabaseReference databaseReference) {
        // [START recent_posts_query]
        // Last 100 posts, these are automatically the 100 most recent
        // due to sorting by push() keys
        Query recentPostsQuery = databaseReference.child("messages")
                .limitToFirst(100);
        // [END recent_posts_query]

        return recentPostsQuery;
    }
}
