package com.vikas.dtu.safetyfirst2.mNotification;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vikas.dtu.safetyfirst2.DynamicDashboardNav;
import com.vikas.dtu.safetyfirst2.R;
import com.vikas.dtu.safetyfirst2.mData.News;
import com.vikas.dtu.safetyfirst2.mDiscussion.PostDetailActivity;
import com.vikas.dtu.safetyfirst2.mNewsActivity.NewsActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by rishabh on 4/1/17.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    private RealmResults<NotificationObject> items;
    private Context mContext;
    private Realm realm;

    NotificationAdapter(Context context, Realm realmInstance) {
        this.mContext = context;
        this.realm = realmInstance;
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmQuery<NotificationObject> query = realm.where(NotificationObject.class);
                items = query.findAll();
            }
        });
    }

    @Override
    public NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_notification, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotificationViewHolder holder, int position) {
        final NotificationObject notif = items.get(position);
        holder.bodyView.setText(notif.getBody());
        if(!notif.isSeen()) {
            holder.mainView.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimaryLight));
        }
        if(notif.getType() == NotificationObject.NEWS_WITH_IMAGE){
            holder.notificationImage.setVisibility(View.VISIBLE);
            holder.notificationImage.setImageBitmap(loadImageFromStorage(notif.getExtraString()));
        }
        holder.mainView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if(notif.getType() == NotificationObject.NEWS || notif.getType() == NotificationObject.NEWS_WITH_IMAGE)
                    intent = new Intent(mContext, NewsActivity.class);
                else if(notif.getType() == NotificationObject.COMMENT_ON_POST){
                    intent = new Intent(mContext, PostDetailActivity.class);
                    intent.putExtra(PostDetailActivity.EXTRA_POST_KEY, notif.getExtraString());
                } else {
                    intent = new Intent(mContext, DynamicDashboardNav.class);
                }
                intent.putExtra("fromNotification", notif.getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {

        public TextView bodyView;
        public ImageView notificationImage;
        public View mainView;
        public TextView titleView;
        //TODO add upvotes and downvotes round_blue_dark


        public NotificationViewHolder(View itemView) {
            super(itemView);

            mainView = itemView;
            bodyView = (TextView) itemView.findViewById(R.id.notification_descr);
            notificationImage = (ImageView) itemView.findViewById(R.id.notification_image);
            titleView = (TextView) itemView.findViewById(R.id.notification_title);
        }
    }

    private Bitmap loadImageFromStorage(String path)
    {
        Bitmap b = null;
        try {
            File f = new File(path);
            b = BitmapFactory.decodeStream(new FileInputStream(f));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return b;
    }

}
