package com.vikas.dtu.safetyfirst.mKnowitActivity.Scaffolding.Types.SupportScaffolding;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.vikas.dtu.safetyfirst.R;

/**
 * Created by Golu on 9/7/2016.
 */
public class SupportedScaffold extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.supported_scaffold);
        setContentView(R.layout.frame_scaffold);
        TextView frameScaffold = (TextView) findViewById(R.id.frame_scoffold);
        frameScaffold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SupportedScaffold.this, FrameScaffold.class);
                startActivity(intent);
            }
        });
        TextView mobileScaffold = (TextView)findViewById(R.id.content4);
        mobileScaffold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SupportedScaffold.this,MobileScaffold.class);
                startActivity(intent);
            }
        });
        TextView pumpjackScaffold = (TextView)findViewById(R.id.content5);
        pumpjackScaffold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SupportedScaffold.this,PumpJackScaffold.class);
                startActivity(intent);
            }
        });
        TextView tubeCouplerScaffold = (TextView)findViewById(R.id.content6);
        tubeCouplerScaffold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SupportedScaffold.this,TubeCouplerScaffold.class);
                startActivity(intent);
            }
        });
        TextView ladderJackScaffold = (TextView)findViewById(R.id.content7);
        ladderJackScaffold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SupportedScaffold.this,LadderJackScaffold.class);
                startActivity(intent);
            }
        });
        TextView poleScaffold = (TextView)findViewById(R.id.content8);
        poleScaffold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SupportedScaffold.this,PoleScaffold.class);
                startActivity(intent);
            }
        });
        TextView specialtyScaffold = (TextView)findViewById(R.id.content9);
        specialtyScaffold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SupportedScaffold.this,SpecialtyScaffold.class);
                startActivity(intent);
            }
        });


    }
}
