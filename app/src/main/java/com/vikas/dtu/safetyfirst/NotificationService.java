package com.vikas.dtu.safetyfirst;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.NotificationCompat;

import com.vikas.dtu.safetyfirst.mDiscussion.DiscussionActivity;

/**
 * Created by viveksb007 on 1/11/16.
 */

public class NotificationService extends IntentService {

    private static final int NOTIFICATION_ID = 1;

    public NotificationService() {
        super(NotificationService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        processStartNotification();
    }

    private void processStartNotification() {
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("Some Update on Post")
                .setAutoCancel(false)
                .setColor(Color.parseColor("#A8A8A8"))
                .setContentText("Someone commented on your post or some update on post you are involved with.")
                .setSmallIcon(R.drawable.ic_notifications_black_24dp);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                NOTIFICATION_ID,
                new Intent(this, DiscussionActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        final NotificationManager manager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATION_ID, builder.build());

    }
}
