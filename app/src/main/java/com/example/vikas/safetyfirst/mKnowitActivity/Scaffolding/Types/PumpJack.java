package com.example.vikas.safetyfirst.mKnowitActivity.Scaffolding.Types;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.vikas.safetyfirst.R;

public class PumpJack extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pump_jack);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
