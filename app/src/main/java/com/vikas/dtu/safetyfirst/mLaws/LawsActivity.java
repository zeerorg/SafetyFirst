package com.vikas.dtu.safetyfirst.mLaws;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.vikas.dtu.safetyfirst.R;

public class LawsActivity extends AppCompatActivity implements View.OnClickListener {


    String dest_file_path = "laws.pdf";
    int downloadedSize = 0, totalsize;
    float per = 0;

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
              //  downloadAndOpenPDF(mineurl);
                break;
            case R.id.theFactories_pdf:
             //   downloadAndOpenPDF(factoryurl);
                break;
            case R.id.dockWorker_pdf:
             //   downloadAndOpenPDF(dockworkerurl);
                break;
        }
    }


 /*   void downloadAndOpenPDF(final String url) {
        new Thread(new Runnable() {
            public void run() {
                Uri path = Uri.fromFile(downloadFile(url));
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(path, "application/pdf");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                } catch (ActivityNotFoundException e) {
                    //TODO make dialog to show error
                    Toast.makeText(LawsActivity.this, "Cannot download file", Toast.LENGTH_SHORT).show();
                }
            }
        }).start();

    }


    File downloadFile(String dwnload_file_path) {
        File file = null;
        try {

            URL url = new URL(dwnload_file_path);
            HttpURLConnection urlConnection = (HttpURLConnection) url
                    .openConnection();

            //  urlConnection.setRequestMethod("GET");
            //  urlConnection.setDoOutput(true);

            // connect
            urlConnection.connect();

            // set the path where we want to save the file
            File SDCardRoot = Environment.getExternalStorageDirectory();
            // create a new file, to save the downloaded file
            file = new File(SDCardRoot, dest_file_path);

            FileOutputStream fileOutput = new FileOutputStream(file);

            // Stream used for reading the data from the internet
            InputStream inputStream = urlConnection.getInputStream();

            // this is the total size of the file which we are
            // downloading
            totalsize = urlConnection.getContentLength();
            Toast.makeText(this, "Download running in background", Toast.LENGTH_SHORT).show();

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
                        + "% complete");
            }
            // close the output stream when complete //
            fileOutput.close();
            setText("Download Complete. Open PDF Application installed in the device.");

        } catch (final MalformedURLException e) {
            setTextError("Some error occured. Press back and try again.",
                    Color.RED);
        } catch (final IOException e) {
            setTextError("Some error occured. Press back and try again.",
                    Color.RED);
        } catch (final Exception e) {
            setTextError(
                    "Failed to download image. Please check your internet connection.",
                    Color.RED);
        }
        return file;
    }
    */
}
