package com.example.vikas.safetyfirst.mNewsActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vikas.safetyfirst.BaseActivity;
import com.example.vikas.safetyfirst.R;
import com.example.vikas.safetyfirst.mData.News;
import com.example.vikas.safetyfirst.mWebview.WebViewActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class NewsDetailActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "NewsDetailActivity";

    public static final String EXTRA_NEWS_KEY = "post_key";

    private DatabaseReference mPostReference;
    private ValueEventListener mPostListener;
    private String mPostKey;
    private TextView mTitleView;
    private TextView mBodyView;
    private TextView mReadMore;
    private ImageView mNewsImage;
    private String url;
    private String HEADLINE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        // Set Collapsing Toolbar layout to the screen
    //    CollapsingToolbarLayout collapsingToolbar =
    //            (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        // Set title of Detail page

   //     collapsingToolbar.setTitle(HEADLINE);


        // Get post key from intent
        mPostKey = getIntent().getStringExtra(EXTRA_NEWS_KEY);
        if (mPostKey == null) {
            throw new IllegalArgumentException("Must pass EXTRA_NEWS_KEY");
        }

        // Initialize Database
        mPostReference = FirebaseDatabase.getInstance().getReference()
                .child("news").child(mPostKey);

        // Initialize Views

        mTitleView = (TextView) findViewById(R.id.post_title);
        mBodyView = (TextView) findViewById(R.id.post_body);
        mReadMore = (TextView) findViewById(R.id.readMore);
        mNewsImage = (ImageView) findViewById(R.id.news_photo);

    mReadMore.setOnClickListener(this);

    }

    @Override
    public void onStart() {
        super.onStart();

        // Add value event listener to the post
        // [START post_value_event_listener]
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get News object and use the values to update the UI
                News news = dataSnapshot.getValue(News.class);
                // [START_EXCLUDE]
//                mAuthorView.setText(news.author);
                HEADLINE = news.title;
                mTitleView.setText(news.title);
                mBodyView.setText(news.body);
                url = news.author;
                if(news.imgUrl!=null){
                    //todo set image in view using picasso
                    mNewsImage.setImageURI(Uri.parse(news.imgUrl));
                    mNewsImage.setVisibility(View.VISIBLE);
                }
                // [END_EXCLUDE]
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting News failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // [START_EXCLUDE]
                Toast.makeText(NewsDetailActivity.this, "Failed to load news.",
                        Toast.LENGTH_SHORT).show();
                // [END_EXCLUDE]
            }
        };
        mPostReference.addValueEventListener(postListener);
        // [END post_value_event_listener]

        // Keep copy of post listener so we can remove it when app stops
        mPostListener = postListener;

    }

    @Override
    public void onStop() {
        super.onStop();

        // Remove post value event listener
        if (mPostListener != null) {
            mPostReference.removeEventListener(mPostListener);
        }

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.readMore) {
            Toast.makeText(NewsDetailActivity.this, url, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, WebViewActivity.class);
            intent.putExtra("AUTHOR", url);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        ActivityCompat.finishAfterTransition(this);
    }
}
