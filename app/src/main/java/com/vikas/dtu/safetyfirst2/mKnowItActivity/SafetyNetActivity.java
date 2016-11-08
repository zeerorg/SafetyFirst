package com.vikas.dtu.safetyfirst2.mKnowItActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.vikas.dtu.safetyfirst2.BaseActivity;
import com.vikas.dtu.safetyfirst2.R;

/**
 * Created by krishna on 2/11/16.
 */

public class SafetyNetActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety_net);
    }

    public void toast(){
        Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show();
    }
}
