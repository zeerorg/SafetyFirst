package com.vikas.dtu.safetyfirst2.mLaws;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;

import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by DHEERAJ on 07-01-2017.
 */

public class DownloadPdf {
    float per = 0;
    TextView tv_loading;
    Uri path=null;
    int downloadedSize = 0;
    int totalsize;
    File SDcardroot;
    public void downloadandShow(String url, Activity thisactivity, String Filename) {

        //  Log.d(TAG, url);
        if (url != null) {
            tv_loading = new TextView(thisactivity);

            tv_loading.setGravity(Gravity.CENTER);
            tv_loading.setTypeface(null, Typeface.BOLD);
            downloadAndOpenPDF(url,Filename,thisactivity);

        } else {
            Toast.makeText(thisactivity, "No File Attached", Toast.LENGTH_SHORT).show();
        }

    }
    public static boolean CheckforPermissions(Activity thisActivity) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(thisActivity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                return false;
            }
        }
        else{
            return true;
        }
    }



    void downloadAndOpenPDF(final String url, final String filename, final Activity thisactivity) {

        new Thread(new Runnable() {
            public void run() {
                File file;
                file=new File(Environment.getExternalStorageDirectory(),filename);
                if(file.exists()==false){
                    thisactivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            thisactivity.setContentView(tv_loading);

                        }
                    });

                    file = downloadFile(url,filename,thisactivity);
                    if(file!=null){
                        path = Uri.fromFile(file);}
                }else{
                    path = Uri.fromFile(file);}
                if(file!=null){
                    if (file.exists()) {

                        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
                        pdfIntent.setDataAndType(path, "application/pdf");
                        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        thisactivity.startActivity(pdfIntent);
                    }
                }}

        }).start();
    }



    File downloadFile(String dwnload_file_path, String filename, Activity thisactivity) {
        File file = null;
        try {

            URL url = new URL(dwnload_file_path);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            //  urlConnection.setRequestMethod("GET");
            //  urlConnection.setDoOutput(true);

            // connect
            urlConnection.connect();

            // set the path where we want to save the file

            SDcardroot = Environment.getExternalStorageDirectory();
            FileOutputStream fileOutput;
            // create a new file, to save the downloaded file

            file=new File(SDcardroot,filename);
            fileOutput = new FileOutputStream(file);
            // Stream used for reading the data from the internet
            InputStream inputStream = urlConnection.getInputStream();

            // this is the total size of the file which we are
            // downloading
            totalsize = urlConnection.getContentLength();
            setText("Starting PDF download...",thisactivity);

            // create a buffer...
            byte[] buffer = new byte[1024 * 1024];
            int bufferLength = 0;

            while ((bufferLength = inputStream.read(buffer)) > 0) {
                fileOutput.write(buffer, 0, bufferLength);
                downloadedSize += bufferLength;
                per = ((float) downloadedSize / totalsize) * 100;
                setText("Total PDF File size  : "
                        + (totalsize / 1024)
                        + " KB\n\nDownloading PDF " + (int) per
                        + "% complete",thisactivity);
            }
            // close the output stream when complete //
            fileOutput.close();
            setText("Download Complete. Open PDF Application installed in the device.",thisactivity);

        } catch (final MalformedURLException e) {
            setTextError(e.getMessage(),
                    Color.RED,thisactivity);
        } catch (FileNotFoundException e){

            setTextError("Some Error Occured.Please Check for Storage Permissions of Application",
                    Color.RED,thisactivity);
        } catch (final IOException e) {
            setTextError(e.getMessage(),
                    Color.RED,thisactivity);
        } catch (final Exception e) {
            setTextError(
                    e.getMessage(),
                    Color.RED,thisactivity);
        }
        return file;
    }

    void setTextError(final String message, final int color, Activity thisactivity) {
        thisactivity.runOnUiThread(new Runnable() {
            public void run() {
                tv_loading.setTextColor(color);
                tv_loading.setText(message);
            }
        });

    }

    void setText(final String txt, Activity thisactivity) {
        thisactivity.runOnUiThread(new Runnable() {
            public void run() {
                tv_loading.setText(txt);
            }
        });
    }

}



