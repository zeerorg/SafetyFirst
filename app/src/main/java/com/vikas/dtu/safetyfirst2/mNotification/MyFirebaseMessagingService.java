package com.vikas.dtu.safetyfirst2.mNotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.vikas.dtu.safetyfirst2.DynamicDashboardNav;
import com.vikas.dtu.safetyfirst2.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.vikas.dtu.safetyfirst2.mDiscussion.PostDetailActivity;
import com.vikas.dtu.safetyfirst2.mNewsActivity.NewsActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Random;

import io.realm.Realm;

/**
 * Created by Vikas on 15-07-2016.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    public static final String unreadPreference = "unreadNotification";
    public static final String discussionNotif = "discussionNotification";
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Map<String, String> data = remoteMessage.getData();

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int unreadNotification = sharedPreferences.getInt(unreadPreference, 0);
        editor = sharedPreferences.edit();
        editor.putInt(MyFirebaseMessagingService.unreadPreference, unreadNotification+1);
        editor.apply();

        //Calling method to generate notification
        if(data.containsKey("postKey")) {
            sendNotificationForDiscussion(data.get("postKey"), data.get("body"), data.get("title"));
        } else if (data.containsKey("image")) {
            sendNotificationForNews(data.get("body"), data.get("image"), data.get("title"));
        } else {
            sendNotificationForNews(data.get("body"), null, data.get("title"));
        }
    }

    //This method is only generating push notification
    //It is same as we did in earlier posts
    private void sendNotificationForNews(final String messageBody, String imageUrl, String title) {
        NotificationObject notif;
        Intent intent = new Intent(this, NewsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        final NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle(title)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri);

        if(imageUrl != null) {
            Bitmap bitmap = getBitmap(imageUrl);
            NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle().bigPicture(bitmap);
            style.setSummaryText(messageBody);
            notificationBuilder.setStyle(style);
        }
        notif = insertInDatabase(messageBody, NotificationObject.NEWS, null, title);

        intent.putExtra("fromNotification", notif.getId());
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);
        notificationBuilder.setContentIntent(pendingIntent);
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        int notificationNumber = (new Random()).nextInt(4325);
        notificationManager.notify(notificationNumber, notificationBuilder.build());
    }

    public void sendNotificationForDiscussion(String postKey, String body, String title){
        NotificationObject notif = insertInDatabase(body, NotificationObject.COMMENT_ON_POST, postKey, title);
        Intent intent;
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);

        String notificationString = sharedPreferences.getString(discussionNotif, "");
        if(!notificationString.equals("")){
            String bigText = notificationString + "\n" + body;
            body = bigText.split("\n").length + " discussion activity.";
            title = "Safety First";
            intent = new Intent(this, NotificationActivity.class);
            notificationBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText(bigText));
            editor.putString(discussionNotif, bigText);
        } else {
            intent = new Intent(this, PostDetailActivity.class);
            intent.putExtra(PostDetailActivity.EXTRA_POST_KEY, postKey);
            editor.putString(discussionNotif, body);
        }
        editor.apply();

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        notificationBuilder.setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(4325, notificationBuilder.build());
    }

    public Bitmap getBitmap(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public NotificationObject insertInDatabase(final String body, final int type, final String extraString, final String title) {
        Realm realm = Realm.getDefaultInstance();
        final NotificationObject notif = new NotificationObject();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                notif.setBody(body);
                notif.setType(type);
                if(extraString != null)
                    notif.setExtraString(extraString);
                if(title != null)
                    notif.setTitle(title);
                realm.copyToRealm(notif);
            }
        });

        realm.close();
        return notif;
    }
}