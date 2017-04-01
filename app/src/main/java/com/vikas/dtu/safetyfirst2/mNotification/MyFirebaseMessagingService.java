package com.vikas.dtu.safetyfirst2.mNotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
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

import io.realm.Realm;

/**
 * Created by Vikas on 15-07-2016.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Map<String, String> data = remoteMessage.getData();

        //Calling method to generate notification
        if(data.containsKey("postKey")) {
            sendNotificationForComment(data.get("postKey"));
        } else if (data.containsKey("image")) {
            sendNotification(data.get("body"), data.get("image"));
        } else {
            sendNotification(data.get("body"), null);
        }
    }

    //This method is only generating push notification
    //It is same as we did in earlier posts
    private void sendNotification(final String messageBody, String imageUrl) {
        Intent intent = new Intent(this, NewsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        final NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Safety First")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        if(imageUrl != null) {
            Bitmap bitmap = getBitmap(imageUrl);
            String filePath = saveToInternalStorage(bitmap, imageUrl);
            NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle().bigPicture(bitmap);
            style.setSummaryText(messageBody);
            notificationBuilder.setStyle(style);
            insertInDatabase(messageBody, NotificationObject.NEWS_WITH_IMAGE, filePath);
        } else {
            insertInDatabase(messageBody, NotificationObject.NEWS, null);
        }

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(4325, notificationBuilder.build());
    }

    public void sendNotificationForComment(String postKey){
        String body = "New comment on your post";
        Intent intent = new Intent(this, PostDetailActivity.class);
        intent.putExtra(PostDetailActivity.EXTRA_POST_KEY, postKey);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle("Safety First")
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(4325, notificationBuilder.build());
        insertInDatabase(body, NotificationObject.COMMENT_ON_POST, postKey);
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

    public void insertInDatabase(final String body, final int type, final String extraString) {
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                NotificationObject notif = new NotificationObject();
                notif.setBody(body);
                notif.setType(type);
                if(extraString != null)
                    notif.setExtraString(extraString);
                notif = realm.copyToRealm(notif);
            }
        });

        realm.close();
    }

    private String saveToInternalStorage(Bitmap bitmapImage, String url){
        String[] split = url.split("/");
        String fileName = split[split.length-1];

        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File directory = cw.getDir("images", Context.MODE_PRIVATE);
        File path = new File(directory, fileName);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(path);
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return path.getAbsolutePath();
    }
}