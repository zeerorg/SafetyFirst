package com.vikas.dtu.safetyfirst2.mLaws;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import android.widget.TextView;


import com.vikas.dtu.safetyfirst2.R;

import com.vikas.dtu.safetyfirst2.mLaws.StateLaws.Checkforpermission;

import com.vikas.dtu.safetyfirst2.mLaws.StateLaws.StateLawsActivity;


import java.io.File;
import java.util.ArrayList;

public class ActivityLaws extends AppCompatActivity {


    RecyclerView recyclerView;
    CardView c1, c2, c3;

    Resources res;
    DownloadPdf downloaPdf;
    ArrayList<StateLawsRowInfo> data;
    static String dockworkerurl = "https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2Fdockworker.pdf?alt=media&token=d4cc97f6-5bf9-40a4-90e1-5b65856a9a97";
    static String mineurl = "https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2Fmines.pdf?alt=media&token=e300e4b7-1e26-4806-91df-a3d21ddf1893";
    static String factoryurl = "https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2Ffactories.pdf?alt=media&token=c5d026fc-f82a-41db-b5cc-aa06a8e6fbca";



    String[] a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        res = this.getResources();
        a = res.getStringArray(R.array.States);
        setContentView(R.layout.activity_alllaws);
        c1 = (CardView) findViewById(R.id.cardview1);
        c2 = (CardView) findViewById(R.id.cardview2);
        c3 = (CardView) findViewById(R.id.cardview3);
        a = res.getStringArray(R.array.States);
        String state = Environment.getExternalStorageState();
        downloaPdf=new DownloadPdf();

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Checkforpermission.CheckforPermissions(ActivityLaws.this)) {
                    downloaPdf.downloadandShow(mineurl, ActivityLaws.this, "Mines Act.pdf");
                } else {
                    Checkforpermission.requestpermission(ActivityLaws.this, 1);
                }
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Checkforpermission.CheckforPermissions(ActivityLaws.this)) {
                    downloaPdf.downloadandShow(factoryurl, ActivityLaws.this, "Factories Act.pdf");
                } else {
                    Checkforpermission.requestpermission(ActivityLaws.this, 2);
                }
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Checkforpermission.CheckforPermissions(ActivityLaws.this)) {
                    downloaPdf.downloadandShow(dockworkerurl, ActivityLaws.this, "Dock Workers Act.pdf");
                } else {
                    Checkforpermission.requestpermission(ActivityLaws.this, 3);
                }
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.StateLawsRecycler);
        data = filldata();
        LawsRecyclerViewAdapter adapter = new LawsRecyclerViewAdapter(getApplicationContext(), data, new LawsRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent=new Intent(ActivityLaws.this, StateLawsActivity.class);
                intent.putExtra("position",position);
                Log.d("Tag1", String.valueOf(position));
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
    }

    private ArrayList<StateLawsRowInfo> filldata() {
        ArrayList<StateLawsRowInfo> rowInfo = new ArrayList<>();

        for(int i=0;i<a.length;i++){
            rowInfo.add(new StateLawsRowInfo(a[i],getResources().getDrawable(R.drawable.statelawpattern)));
        }


        return rowInfo;
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    downloaPdf.downloadandShow(mineurl, ActivityLaws.this, "Mines Act.pdf");
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                }
                return;
            }
            case 2: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    downloaPdf.downloadandShow(factoryurl, ActivityLaws.this, "Factories Act.pdf");

                }
                return;
            }
            case 3: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    downloaPdf.downloadandShow(dockworkerurl, ActivityLaws.this, "Dock Workers Act.pdf");
                }
                return;
            }
        }
    }




}