package com.vikas.dtu.safetyfirst.mKnowItActivity.Ladder;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;

import com.vikas.dtu.safetyfirst.BaseActivity;
import com.vikas.dtu.safetyfirst.R;

/**
 * Created by krishna on 4/11/16.
 */

public class StandardLadder extends BaseActivity {
    LinearLayout InformationLayout;
    LinearLayout HowToUseLayout;
    LinearLayout SafetyCheckLayout;
    LinearLayout VideoLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard_ladder);
        InformationLayout = (LinearLayout)findViewById(R.id.information_layout);
        HowToUseLayout = (LinearLayout)findViewById(R.id.how_to_use_layout);
        SafetyCheckLayout = (LinearLayout)findViewById(R.id.safety_layout);
        VideoLayout = (LinearLayout)findViewById(R.id.video_layout);
        InformationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InformationLayout.setBackgroundColor(ContextCompat.getColor(v.getContext(),R.color.dark_blue));
                HowToUseLayout.setBackgroundColor(ContextCompat.getColor(v.getContext(),R.color.dark_red));
                SafetyCheckLayout.setBackgroundColor(ContextCompat.getColor(v.getContext(),R.color.dark_red));
                VideoLayout.setBackgroundColor(ContextCompat.getColor(v.getContext(),R.color.dark_red));
            }
        });
        HowToUseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HowToUseLayout.setBackgroundColor(ContextCompat.getColor(v.getContext(),R.color.dark_blue));
                InformationLayout.setBackgroundColor(ContextCompat.getColor(v.getContext(),R.color.dark_red));
                SafetyCheckLayout.setBackgroundColor(ContextCompat.getColor(v.getContext(),R.color.dark_red));
                VideoLayout.setBackgroundColor(ContextCompat.getColor(v.getContext(),R.color.dark_red));
            }
        });
        SafetyCheckLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SafetyCheckLayout.setBackgroundColor(ContextCompat.getColor(v.getContext(),R.color.dark_blue));
                HowToUseLayout.setBackgroundColor(ContextCompat.getColor(v.getContext(),R.color.dark_red));
                InformationLayout.setBackgroundColor(ContextCompat.getColor(v.getContext(),R.color.dark_red));
                VideoLayout.setBackgroundColor(ContextCompat.getColor(v.getContext(),R.color.dark_red));
            }
        });
        VideoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoLayout.setBackgroundColor(ContextCompat.getColor(v.getContext(),R.color.dark_blue));
                HowToUseLayout.setBackgroundColor(ContextCompat.getColor(v.getContext(),R.color.dark_red));
                SafetyCheckLayout.setBackgroundColor(ContextCompat.getColor(v.getContext(),R.color.dark_red));
                InformationLayout.setBackgroundColor(ContextCompat.getColor(v.getContext(),R.color.dark_red));
            }
        });
    }

}
