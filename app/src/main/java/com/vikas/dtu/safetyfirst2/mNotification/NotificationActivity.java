package com.vikas.dtu.safetyfirst2.mNotification;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.vikas.dtu.safetyfirst2.BaseActivity;
import com.vikas.dtu.safetyfirst2.R;

import io.realm.Realm;

public class NotificationActivity extends BaseActivity {

    private RecyclerView notificationView;
    private NotificationAdapter notificationAdapter;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        realm = Realm.getDefaultInstance();

        notificationAdapter = new NotificationAdapter(this, realm);
        notificationView = (RecyclerView) findViewById(R.id.notification_view);
        notificationView.setLayoutManager(new LinearLayoutManager(this));
        notificationView.setAdapter(notificationAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
