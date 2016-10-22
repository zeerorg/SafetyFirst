package com.vikas.dtu.safetyfirst.mKnowItActivity.Scaffoldings;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.View;

import com.vikas.dtu.safetyfirst.BaseActivity;
import com.vikas.dtu.safetyfirst.R;

/**
 * Created by krishna on 20/10/16.
 */

public class Scaffolding extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scaffold);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
    }

    public void selfSupportingScaffold(View view) {
        Intent intent = new Intent(this,SelfSupportingScaffold.class);
        startActivity(intent);
    }

    public void suspensionScaffold(View view) {
        Intent intent = new Intent(this,SuspensionScaffold.class);
        startActivity(intent);
    }
}
