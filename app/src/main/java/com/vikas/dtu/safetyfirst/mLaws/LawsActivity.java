package com.vikas.dtu.safetyfirst.mLaws;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.vikas.dtu.safetyfirst.R;
import com.vikas.dtu.safetyfirst.mUtils.Downloader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class LawsActivity extends AppCompatActivity implements View.OnClickListener {

    private BroadcastReceiver mBroadcastReceiver;
    private ProgressDialog mProgressDialog;
    private FirebaseAuth mAuth;


    public  TextView minesView, factoriesView, dockworkerView;

    static String dockworkerurl = "https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2Fdockworker.pdf?alt=media&token=d4cc97f6-5bf9-40a4-90e1-5b65856a9a97";
    static String mineurl = "https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2Fmines.pdf?alt=media&token=e300e4b7-1e26-4806-91df-a3d21ddf1893";
    static String factoryurl = "https://firebasestorage.googleapis.com/v0/b/safetyfirst-aec72.appspot.com/o/laws%2Ffactories.pdf?alt=media&token=c5d026fc-f82a-41db-b5cc-aa06a8e6fbca";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laws);

        minesView = (TextView) findViewById(R.id.mines_pdf);
        factoriesView = (TextView) findViewById(R.id.theFactories_pdf);
        dockworkerView = (TextView) findViewById(R.id.dockWorker_pdf);

        minesView.setOnClickListener(this);
        factoriesView.setOnClickListener(this);
        dockworkerView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mines_pdf:
                   // downloadfile(mineurl, "mines");
                break;
            case R.id.theFactories_pdf:
                   // downloadfile(factoryurl, "factories");
                break;
            case R.id.dockWorker_pdf:
                   // downloadfile(dockworkerurl, "dockWorker");
                break;
        }
    }



    public void showPdf()
    {
        File file = new File(Environment.getExternalStorageDirectory()+"/pdf/Read.pdf");
        PackageManager packageManager = getPackageManager();
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        testIntent.setType("application/pdf");
        List list = packageManager.queryIntentActivities(testIntent, PackageManager.MATCH_DEFAULT_ONLY);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, "application/pdf");
        startActivity(intent);
    }

}
