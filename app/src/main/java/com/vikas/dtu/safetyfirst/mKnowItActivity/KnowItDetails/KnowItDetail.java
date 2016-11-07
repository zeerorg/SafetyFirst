package com.vikas.dtu.safetyfirst.mKnowItActivity.KnowItDetails;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.vikas.dtu.safetyfirst.R;

public class KnowItDetail extends AppCompatActivity {

    TextView infoTxt, howtoTxt, checkTxt;
    VideoView vidViw;
    ImageView imgVw;


    private String info;
    private String howto;
    private String check;
    private int image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_know_it_detail);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            info = (String) bundle.get("info");
            image = (int) bundle.get("image");

            howto = (String) bundle.get("howto");
            check = (String) bundle.get("checklist");
            
            if(bundle.get("check")!=null){check=(String)bundle.get("check");}
        }

        infoTxt = (TextView) findViewById(R.id.info) ;
        howtoTxt = (TextView) findViewById(R.id.howto);
        checkTxt = (TextView) findViewById(R.id.check);
        vidViw = (VideoView) findViewById(R.id.vid);
        imgVw = (ImageView) findViewById(R.id.image);

        infoTxt.setVisibility(View.VISIBLE);
        howtoTxt.setVisibility(View.GONE);
        checkTxt.setVisibility(View.GONE);
        vidViw.setVisibility(View.GONE);


        Drawable new_image= getResources().getDrawable(image);
        imgVw.setImageDrawable(new_image);

        infoTxt.setText(info);
        howtoTxt.setText(howto);


        View.OnClickListener listener = new View.OnClickListener() {
            public void onClick(View view) {
                Fragment fragment = null;
                if(view == findViewById(R.id.info_btn)){
                    infoTxt.setVisibility(View.VISIBLE);
                    howtoTxt.setVisibility(View.GONE);
                    checkTxt.setVisibility(View.GONE);
                    vidViw.setVisibility(View.GONE);

                } else if(view == findViewById(R.id.howto_btn)){
                    infoTxt.setVisibility(View.GONE);
                    howtoTxt.setVisibility(View.VISIBLE);
                    checkTxt.setVisibility(View.GONE);
                    vidViw.setVisibility(View.GONE);

                }
                else if(view == findViewById(R.id.check_btn)){
                    infoTxt.setVisibility(View.GONE);
                    howtoTxt.setVisibility(View.GONE);
                    checkTxt.setVisibility(View.VISIBLE);
                    vidViw.setVisibility(View.GONE);
                }
                else if(view == findViewById(R.id.video_btn)){
                    infoTxt.setVisibility(View.GONE);
                    howtoTxt.setVisibility(View.GONE);
                    checkTxt.setVisibility(View.GONE);
                    vidViw.setVisibility(View.VISIBLE);
                }

            }
        };
        ImageButton btn1 = (ImageButton)findViewById(R.id.info_btn);
        btn1.setOnClickListener(listener);
        ImageButton btn2 = (ImageButton)findViewById(R.id.howto_btn);
        btn2.setOnClickListener(listener);
        ImageButton btn3 = (ImageButton)findViewById(R.id.check_btn);
        btn3.setOnClickListener(listener);
        ImageButton btn4 = (ImageButton)findViewById(R.id.video_btn);
        btn4.setOnClickListener(listener);

    }
}
