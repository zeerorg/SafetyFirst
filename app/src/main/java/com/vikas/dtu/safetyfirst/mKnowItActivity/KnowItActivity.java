package com.vikas.dtu.safetyfirst.mKnowItActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.vikas.dtu.safetyfirst.BaseActivity;
import com.vikas.dtu.safetyfirst.R;
import com.vikas.dtu.safetyfirst.mSignUp.SignInGoogle;

/**
 * Created by krishna on 16/10/16.
 */
public class KnowItActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowit2);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

    }

    public void ladderActivity(View view) {
        Intent intent = new Intent(this, com.vikas.dtu.safetyfirst.mKnowItActivity.Ladder.LadderActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(this, SignInGoogle.class));
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    public void scaffolding(View view) {
        Intent intent = new Intent(this, com.vikas.dtu.safetyfirst.mKnowItActivity.Scaffoldings.Scaffolding.class);
        startActivity(intent);

    }
}