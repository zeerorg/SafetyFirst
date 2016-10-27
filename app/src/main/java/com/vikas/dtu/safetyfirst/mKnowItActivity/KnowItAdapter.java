package com.vikas.dtu.safetyfirst.mKnowItActivity;

/**
 * Created by krishna on 27/10/16.
 */

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vikas.dtu.safetyfirst.R;

import java.util.ArrayList;

/**
 * Created by Golu on 8/26/2016.
 */
public class KnowItAdapter extends ArrayAdapter<KnowIt> {

    public KnowItAdapter(Context context, ArrayList<KnowIt> count) {
        super(context, 0, count);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        KnowIt count = getItem(position);
        View listView = convertView;
        if(listView == null)
        {
            listView = LayoutInflater.from(getContext()).inflate(R.layout.recycler_know_it,parent,false);
        }
        TextView defaultTextView = (TextView)listView.findViewById(R.id.know_it_textView);
        defaultTextView.setText(count.getmKnowItname());
        ImageView imageResource = (ImageView)listView.findViewById(R.id.know_it_imageView);
            imageResource.setImageResource(count.getmKnowItImage());
        return listView;
    }
}
