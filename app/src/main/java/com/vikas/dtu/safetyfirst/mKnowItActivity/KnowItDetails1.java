package com.vikas.dtu.safetyfirst.mKnowItActivity;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.vikas.dtu.safetyfirst.R;

public class KnowItDetails1 extends AppCompatActivity implements View.OnClickListener{
    private TextSwitcher mSwitcher;
    private ImageView mImage;

    private String info;
    private String howto;
    private String check;
    private int image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_know_it_details);
        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
                info = (String) bundle.get("info");
                image = (int) bundle.get("image");

            if(bundle.get("check")!=null){check=(String)bundle.get("check");}
        }
        // Get the TextSwitcher view from the layout

        mImage = (ImageView) findViewById(R.id.image);
        mSwitcher = (TextSwitcher) findViewById(R.id.switcher);
        Button infoBtn = (Button) findViewById(R.id.info_btn);
        Button howtoBtn = (Button) findViewById(R.id.howto_btn);
        Button checkBtn = (Button) findViewById(R.id.check_btn);
        Button vidzBtn = (Button) findViewById(R.id.video_btn);

        Drawable new_image= getResources().getDrawable(image);
        mImage.setImageDrawable(new_image);

        // BEGIN_INCLUDE(setup)
        // Set the factory used to create TextViews to switch between.
        mSwitcher.setFactory(mFactory);

        /*
         * Set the in and out animations. Using the fade_in/out animations
         * provided by the framework.
         */
        Animation in = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in);
        Animation out = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out);
        mSwitcher.setInAnimation(in);
        mSwitcher.setOutAnimation(out);
        // END_INCLUDE(setup)

        infoBtn.setOnClickListener(this);
        howtoBtn.setOnClickListener(this);
        checkBtn.setOnClickListener(this);
        vidzBtn.setOnClickListener(this);

        // Set the initial text without an animation
        mSwitcher.setCurrentText(info);
    }


    // BEGIN_INCLUDE(factory)
    /**
     * The {@link android.widget.ViewSwitcher.ViewFactory} used to create {@link android.widget.TextView}s that the
     * {@link android.widget.TextSwitcher} will switch between.
     */
    private ViewSwitcher.ViewFactory mFactory = new ViewSwitcher.ViewFactory() {

        @Override
        public View makeView() {

            // Create a new TextView
            TextView t = new TextView(KnowItDetails1.this);
            t.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
           // t.setTextAppearance(KnowItDetails1.this, android.R.style.TextAppearance_Large);
            return t;
        }
    };
    // END_INCLUDE(factory)


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.info_btn:
                mSwitcher.setText(info);
                break;
            case R.id.howto_btn:
                mSwitcher.setText(getResources().getString(R.string.dummy2));
                break;
            case R.id.check_btn:
                if(check!=null)
                mSwitcher.setText(check);
                break;
            case R.id.video_btn:
                mSwitcher.setText(getResources().getString(R.string.dummy1));
                break;
        }
    }

}
