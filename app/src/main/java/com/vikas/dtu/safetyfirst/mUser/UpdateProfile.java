package com.vikas.dtu.safetyfirst.mUser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.vikas.dtu.safetyfirst.BaseActivity;
import com.vikas.dtu.safetyfirst.R;

public class UpdateProfile extends BaseActivity implements View.OnClickListener {
    FirebaseUser user;

    TextView mName;
    TextView mPhone;
    EditText mCompany;
    EditText mQualification;
    EditText mDesignation;
    EditText mCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        mName = (TextView) findViewById(R.id.nameTxt);
        mPhone = (TextView) findViewById(R.id.phoneTxt);
        mCompany = (EditText) findViewById(R.id.company_name);
        mQualification = (EditText) findViewById(R.id.qualification);
        mDesignation = (EditText) findViewById(R.id.designation);
        mCity = (EditText) findViewById(R.id.city);

        user = getCurrentUser();




    }

    @Override
    public void onClick(View v) {

    }
}
