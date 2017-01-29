package com.vikas.dtu.safetyfirst2.mUser;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.vikas.dtu.safetyfirst2.R;
import com.vikas.dtu.safetyfirst2.mUtils.FirebaseUtil;

import java.util.HashMap;
import java.util.Map;

import static com.vikas.dtu.safetyfirst2.mUtils.FirebaseUtil.getCurrentUserId;

public class UserProfileActivity extends AppCompatActivity {
    private final String TAG = "UserProfileActivity";
    public static final String USER_ID_EXTRA_NAME = "user_name";
    private RecyclerView mRecyclerGrid;
   // private GridAdapter mGridAdapter;
    private ValueEventListener mFollowingListener;
    private ValueEventListener mPersonInfoListener;
    private String mUserId;
    private DatabaseReference mPeopleRef;
    private DatabaseReference mPersonRef;
    private static final int GRID_NUM_COLUMNS = 2;
    private DatabaseReference mFollowersRef;
    private ValueEventListener mFollowersListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
       // mUserId = intent.getStringExtra(USER_ID_EXTRA_NAME);
        mUserId = getCurrentUserId();

        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        mPeopleRef = FirebaseUtil.getPeopleRef();
        final String currentUserId = getCurrentUserId();

        final FloatingActionButton followUserFab = (FloatingActionButton) findViewById(R.id
                .follow_user_fab);
        mFollowingListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    followUserFab.setImageDrawable(ContextCompat.getDrawable(
                            UserProfileActivity.this, R.drawable.ic_done_24dp));
                } else {
                    followUserFab.setImageDrawable(ContextCompat.getDrawable(
                            UserProfileActivity.this, R.drawable.ic_person_add_24dp));
                }
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {

            }
        };
        if (currentUserId != null) {
            mPeopleRef.child(currentUserId).child("following")
                    .child(mUserId)
                    .addValueEventListener(mFollowingListener);
        }
        followUserFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentUserId == null) {
                    Toast.makeText(UserProfileActivity.this, "You need to sign in to follow someone.",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                // TODO: Convert these to actually not be single value, for live updating when
                // current user follows.
                mPeopleRef.child(currentUserId).child("following").child(mUserId).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Map<String, Object> updatedUserData = new HashMap<>();
                        if (dataSnapshot.exists()) {
                            // Already following, need to unfollow
                            updatedUserData.put("people/" + currentUserId + "/following/" + mUserId, null);
                            updatedUserData.put("followers/" + mUserId + "/" + currentUserId, null);
                        } else {
                            updatedUserData.put("people/" + currentUserId + "/following/" + mUserId, true);
                            updatedUserData.put("followers/" + mUserId + "/" + currentUserId, true);
                        }
                        FirebaseUtil.getBaseRef().updateChildren(updatedUserData, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError firebaseError, DatabaseReference firebase) {
                                if (firebaseError != null) {
                                    Toast.makeText(UserProfileActivity.this, R.string
                                            .follow_user_error, Toast.LENGTH_LONG).show();
                                    Log.d(TAG, getString(R.string.follow_user_error) + "\n" +
                                            firebaseError.getMessage());
                                }
                            }
                        });
                    }

                    @Override
                    public void onCancelled(DatabaseError firebaseError) {

                    }
                });
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}
