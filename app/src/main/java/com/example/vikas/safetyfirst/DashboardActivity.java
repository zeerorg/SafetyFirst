package com.example.vikas.safetyfirst;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.vikas.safetyfirst.mDiscussion.DiscussionActivity;
import com.example.vikas.safetyfirst.mNewsActivity.NewsActivity;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void startNews(View view) {
        Intent intent = new Intent(this, NewsActivity.class);
        startActivity(intent);
    }
    public void startDiscussion(View view) {
        Intent intent = new Intent(this,DiscussionActivity.class);
        startActivity(intent);
    }
}
