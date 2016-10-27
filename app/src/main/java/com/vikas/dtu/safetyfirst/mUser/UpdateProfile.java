package com.vikas.dtu.safetyfirst.mUser;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vikas.dtu.safetyfirst.BaseActivity;
import com.vikas.dtu.safetyfirst.R;
import com.vikas.dtu.safetyfirst.mData.User;

public class UpdateProfile extends BaseActivity implements View.OnClickListener {
    FirebaseUser user;

    ImageView mPhoto;
    TextView mName;
    TextView mPhone;
    EditText mCompany;
    EditText mQualification;
    EditText mDesignation;
    EditText mCity;
    private ValueEventListener mUserListener;

    private Button mSubmit;
    DatabaseReference useRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        mPhoto = (ImageView) findViewById(R.id.user_photo);
        mName = (TextView) findViewById(R.id.nameTxt);
        mPhone = (TextView) findViewById(R.id.phoneTxt);
        mCompany = (EditText) findViewById(R.id.company_name);
        mQualification = (EditText) findViewById(R.id.qualification);
        mDesignation = (EditText) findViewById(R.id.designation);
        mCity = (EditText) findViewById(R.id.city);
        mSubmit = (Button) findViewById(R.id.submit);
        user = getCurrentUser();

        useRef = FirebaseDatabase.getInstance().getReference()
                .child("users").child(getUid());

        mSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.submit){
            if (validateForm()) {
              //TODO upload data
                String company = mCompany.getText().toString();
                String qualification = mQualification.getText().toString();
                String designation = mDesignation.getText().toString();
                String city = mCity.getText().toString();


            }
        }
    }


    @Override
    public void onStart() {
        super.onStart();

        // Add value event listener to the news
        // [START user_value_event_listener]
        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get User object and use the values to update the UI
               User user = dataSnapshot.getValue(User.class);
                // [START_EXCLUDE]
//
                if (user.getPhotoUrl() == null) {
                    mPhoto.setImageDrawable(ContextCompat.getDrawable(getBaseContext(),
                            R.drawable.ic_action_account_circle_40));
                } else {
                    Glide.with(getBaseContext())
                            .load(user.getPhotoUrl())
                            .into(mPhoto);
                }

                mName.setText(user.getFull_name());

                // [END_EXCLUDE]
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting News failed, log a message
                Log.w("UpdateProfile", "loadPost:onCancelled", databaseError.toException());
                // [START_EXCLUDE]
                Toast.makeText(UpdateProfile.this, "Failed to load profile.",
                        Toast.LENGTH_SHORT).show();
                // [END_EXCLUDE]
            }
        };
        useRef.addValueEventListener(userListener);
        // [END news_value_event_listener]

        // Keep copy of post listener so we can remove it when app stops
        mUserListener = userListener;

    }

    @Override
    public void onStop() {
        super.onStop();

        // Remove post value event listener
        if (mUserListener != null) {
            useRef.removeEventListener(mUserListener);
        }

    }


    @Override
    public void onBackPressed() {
        ActivityCompat.finishAfterTransition(this);
    }

    private boolean validateForm() {
        boolean result = true;
        if (TextUtils.isEmpty(mCompany.getText().toString())) {
            mCompany.setError("Required");
            result = false;
        } else {
            mCompany.setError(null);
        }
        if (TextUtils.isEmpty(mQualification.getText().toString())) {
            mQualification.setError("Required");
            result = false;
        } else {
            mQualification.setError(null);
        }

        if (TextUtils.isEmpty(mDesignation.getText().toString())) {
            mDesignation.setError("Required");
            result = false;
        } else {
            mDesignation.setError(null);
        }
        if (TextUtils.isEmpty(mCity.getText().toString())) {
            mCity.setError("Required");
            result = false;
        } else {
            mCity.setError(null);
        }

        return result;
    }
}
