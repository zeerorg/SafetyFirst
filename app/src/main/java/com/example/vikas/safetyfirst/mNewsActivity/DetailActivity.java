package com.example.vikas.safetyfirst.mNewsActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.vikas.safetyfirst.R;


/**
 * Created by Vikas on 19-07-2016.
 */
public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "position";
    public static String EXTRA_HEADLINE = "headline";
    public static String EXTRA_ARTICLE = "article";
    public static final String EXTRA_SOURCE = "source";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EXTRA_HEADLINE = getIntent().getStringExtra("Headline");
        EXTRA_ARTICLE = getIntent().getStringExtra("Article");

        setContentView(R.layout.activity_detail_webview);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Set Collapsing Toolbar layout to the screen
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        // Set title of Detail page


         collapsingToolbar.setTitle(EXTRA_HEADLINE);


        TextView textView = (TextView) findViewById(R.id.articleTxtExtra);
        textView.setText(EXTRA_ARTICLE);
    }

    @Override
    public void onBackPressed() {
        ActivityCompat.finishAfterTransition(this);
    }
}
