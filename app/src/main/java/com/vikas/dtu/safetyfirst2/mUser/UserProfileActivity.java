package com.vikas.dtu.safetyfirst2.mUser;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.vikas.dtu.safetyfirst2.BaseActivity;

public class UserProfileActivity extends BaseActivity {

    private static final int CONTENT_VIEW_ID = 10101010;
    public static final String USER_ID_EXTRA_NAME = "user_name";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frame = new FrameLayout(this);
        frame.setId(CONTENT_VIEW_ID);
        setContentView(frame, new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        if (savedInstanceState == null) {
            Fragment newFragment = new UserProfileFragment();
            Bundle bundle = new Bundle();
            bundle.putString(UserProfileFragment.USER_ID_EXTRA_NAME, getIntent().getStringExtra(USER_ID_EXTRA_NAME));
            newFragment.setArguments(bundle);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(CONTENT_VIEW_ID, newFragment).commit();
        }
    }
}
