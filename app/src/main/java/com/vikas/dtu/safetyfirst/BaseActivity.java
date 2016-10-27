package com.vikas.dtu.safetyfirst;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.vikas.dtu.safetyfirst.mSignUp.SignInGoogle;



public class BaseActivity extends AppCompatActivity {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private ProgressDialog mProgressDialog;

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Loading...");
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public void logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, SignInGoogle.class));
    }


    public FirebaseUser getCurrentUser() {
        if (user == null) return null;
        return user;
    }

    public Uri getPhotoUrl(){
        if(user.getPhotoUrl()!=null) return user.getPhotoUrl();
        return null;
    }

    public String getEmail(){
        if(user.getEmail()!=null) return user.getEmail();
        return null;
    }

    public String getName(){
        if(user.getDisplayName()!=null) return user.getDisplayName();
        return null;
    }



}
