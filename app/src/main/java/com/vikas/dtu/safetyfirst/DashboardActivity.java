package com.vikas.dtu.safetyfirst;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.vikas.dtu.safetyfirst.mDiscussion.DiscussionActivity;
import com.vikas.dtu.safetyfirst.mKnowitActivity.KnowitActivity;
import com.vikas.dtu.safetyfirst.mNewsActivity.NewsActivity;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void startNews(View view) {

        if(isNetworkConnected()) {
            Intent intent = new Intent(this, NewsActivity.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(this, NoNetworkConnection.class);
            startActivity(intent);
        }
    }

    public void startDiscussion(View view) {

        if(isNetworkConnected()) {
            Intent intent = new Intent(this, DiscussionActivity.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(this, NoNetworkConnection.class);
            startActivity(intent);
        }

    }

    public void startKnowIt(View view) {
        Intent intent = new Intent(this, KnowitActivity.class);
        startActivity(intent);
    }

    public void startFeedBack(View view) {
        Intent intent = new Intent(this, FeedBackActivity.class);
        startActivity(intent);
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        switch (i) {
            case R.id.action_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, SignInActivity.class));
                finish();
                return true;
            case R.id.search_post:
                //  Toast.makeText(NewsActivity.this, "Just write important TAG/Word of your Question", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
