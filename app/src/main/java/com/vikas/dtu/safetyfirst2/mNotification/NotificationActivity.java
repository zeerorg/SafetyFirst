package com.vikas.dtu.safetyfirst2.mNotification;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;

import com.vikas.dtu.safetyfirst2.BaseActivity;
import com.vikas.dtu.safetyfirst2.R;

import io.realm.Realm;
import io.realm.RealmResults;

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        realm = Realm.getDefaultInstance();

        View noNotification = findViewById(R.id.no_notification);
        RealmResults<NotificationObject> items = realm.where(NotificationObject.class).findAll();
        if(items.size() == 0)
            noNotification.setVisibility(View.VISIBLE);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(MyFirebaseMessagingService.unreadPreference, 0);
        editor.putString(MyFirebaseMessagingService.discussionNotif, "");
        editor.apply();

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
