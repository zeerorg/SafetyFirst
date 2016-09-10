package com.example.vikas.safetyfirst;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.vikas.safetyfirst.mDiscussion.DiscussionActivity;
import com.example.vikas.safetyfirst.mKnowitActivity.KnowitActivity;
import com.example.vikas.safetyfirst.mNewsActivity.NewsActivity;
import com.example.vikas.safetyfirst.social.ProfileActivity;

import java.net.InetAddress;

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
}
