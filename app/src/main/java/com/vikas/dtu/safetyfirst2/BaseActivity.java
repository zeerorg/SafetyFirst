package com.vikas.dtu.safetyfirst2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appinvite.AppInvite;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.vikas.dtu.safetyfirst2.mData.User;
import com.vikas.dtu.safetyfirst2.mNotification.MyFirebaseMessagingService;
import com.vikas.dtu.safetyfirst2.mNotification.NotificationObject;
import com.vikas.dtu.safetyfirst2.mSignUp.SignInActivity;

import io.realm.Realm;


public class BaseActivity extends AppCompatActivity {
    private ProgressDialog mProgressDialog;
    private FirebaseAuth mAuth;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    public static GoogleApiClient mGoogleApiClient;


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
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        startActivity(new Intent(getBaseContext(),SignInActivity.class));
                    }
                });

    }


    public FirebaseUser getCurrentUser() {
        if (user == null) return null;
        return user;
    }

    public Uri getPhotoUrl() {
        if (user != null && user.getPhotoUrl() != null) return user.getPhotoUrl();
        return null;
    }

    public String getEmail() {
        if (user != null && user.getEmail() != null) return user.getEmail();
        return null;
    }

    public String getName() {
        if (user != null && user.getDisplayName() != null)
            return user.getDisplayName();
        else
            return null;
    }

    private void updateUI(FirebaseUser user) {
        startActivity(new Intent(this, SignInActivity.class));
       /* hideProgressDialog();
        if (user != null) {
            mStatusTextView.setText(getString(R.string.google_status_fmt, user.getEmail()));
            mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));

            findViewById(R.id.sign_in_button).setVisibility(View.GONE);
            findViewById(R.id.sign_out_and_disconnect).setVisibility(View.VISIBLE);
        } else {
            mStatusTextView.setText(R.string.signed_out);
            mDetailTextView.setText(null);

            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            findViewById(R.id.sign_out_and_disconnect).setVisibility(View.GONE);
        }*/
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // Create an auto-managed GoogleApiClient with access to App Invites.
        // ATTENTION: This "addApi(AppIndex.API)"was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(AppInvite.API)
                .addApi(AppIndex.API)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();

        notificationSeen();
    }

    private void notificationSeen(){
        if(getIntent().hasExtra("fromNotification")){
            Realm realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    NotificationObject notif = realm.where(NotificationObject.class).equalTo("id", getIntent().getStringExtra("fromNotification")).findFirst();
                    notif.setSeen(true);
                }
            });
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            int unreadNotification = sharedPreferences.getInt(MyFirebaseMessagingService.unreadPreference, 0);
            if(unreadNotification > 0) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(MyFirebaseMessagingService.unreadPreference, unreadNotification - 1);
                editor.apply();
            }
        }
    }
}
