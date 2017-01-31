package com.vikas.dtu.safetyfirst2.mUser;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.vikas.dtu.safetyfirst2.BaseActivity;
import com.vikas.dtu.safetyfirst2.R;
import com.vikas.dtu.safetyfirst2.mData.User;
import com.vikas.dtu.safetyfirst2.mUtils.FirebaseUtil;

import java.util.HashMap;
import java.util.Map;

import static com.vikas.dtu.safetyfirst2.mUtils.FirebaseUtil.getCurrentUserId;

public class UserProfileActivity extends BaseActivity {
    FirebaseUser user;
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
    DatabaseReference useRef;
    StorageReference profilephotoRef;
    private StorageReference mstorageRef;
    boolean check=false;
    ImageView mPhoto;
    TextView mDesignation;
    TextView mCompany;
    TextView mQualification;
    TextView mQuestions;
    TextView mAnswers;

    private ValueEventListener mUserListener;
    CollapsingToolbarLayout collapsingToolbar;
    @Override
    public void onStart() {
        super.onStart();



        profilephotoRef = mstorageRef.child(user.getUid()+"/ProfilePhoto.jpg");
        // Add value event listener to the news
        // [START user_value_event_listener]
        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get User object and use the values to update the UI
                User user = dataSnapshot.getValue(User.class);
                // [START_EXCLUDE]
//
                mDesignation.setText(user.getDesignation());
                mQualification.setText(user.getQualification());
                mCompany.setText(user.getCompany());
                mQuestions.setText(String.valueOf(user.getQuestionsAsked())+" Questions");
                mAnswers.setText(String.valueOf(user.getAnswersGiven())+" Answers");

                collapsingToolbar.setExpandedTitleTextAppearance(R.style.user_profile);
                collapsingToolbar.setTitle(user.getFull_name());

                if(check==true){
                    check=false;}else{
                    if(profilephotoRef!=null){
                        profilephotoRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                useRef.child("userImage").setValue(uri.toString());
                            }
                        })   .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(UserProfileActivity.this, "Failed to get Url!", Toast.LENGTH_SHORT).show();
                            }
                        });

                        Glide.with(getApplicationContext()).load(user.getPhotoUrl()).override(165,165).into(mPhoto);
                    }else if (profilephotoRef==null&&user.getPhotoUrl() == null) {
                        mPhoto.setImageDrawable(ContextCompat.getDrawable(getBaseContext(),
                                R.drawable.ic_action_account_circle_40));
                    } else if(profilephotoRef==null&&user.getPhotoUrl()!=null) {
                        Glide.with(getApplicationContext())
                                .load(user.getPhotoUrl()).override(165,165)
                                .into(mPhoto);
                    }}



                // [END_EXCLUDE]
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting News failed, log a message
                Log.w("UpdateProfile", "loadPost:onCancelled", databaseError.toException());
                // [START_EXCLUDE]
                Toast.makeText(UserProfileActivity.this, "Failed to load profile.",
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mstorageRef= FirebaseStorage.getInstance().getReference();
        user = getCurrentUser();
        mPhoto = (ImageView) findViewById(R.id.backdrop);
        mDesignation = (TextView) findViewById(R.id.user_designation);
        mCompany = (TextView) findViewById(R.id.user_company);
        mQualification = (TextView) findViewById(R.id.user_qualification);
        mQuestions = (TextView) findViewById(R.id.user_num_questions);
        mAnswers = (TextView) findViewById(R.id.user_num_answers);

        //mUserId = getCurrentUserId();

        Intent intent = getIntent();
        mUserId = intent.getStringExtra(USER_ID_EXTRA_NAME);

       // Toast.makeText(this,  mUserId, Toast.LENGTH_SHORT).show();
         collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        mPeopleRef = FirebaseUtil.getPeopleRef();

        useRef = FirebaseDatabase.getInstance().getReference()
                .child("users").child( mUserId);

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
                            UserProfileActivity.this, R.drawable.ic_person_add_24dpd));
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
                            updatedUserData.put("users/" + currentUserId + "/following/" + mUserId, null);
                            updatedUserData.put("followers/" + mUserId + "/" + currentUserId, null);
                        } else {
                            updatedUserData.put("users/" + currentUserId + "/following/" + mUserId, true);
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

    @Override
    protected void onDestroy() {
        if (FirebaseUtil.getCurrentUserId() != null) {
            mPeopleRef.child(getCurrentUserId()).child("following").child(mUserId)
                    .removeEventListener(mFollowingListener);
        }

        if(mPersonRef != null)
        mPersonRef.child(mUserId).removeEventListener(mPersonInfoListener);

        if(mFollowersRef != null)
        mFollowersRef.removeEventListener(mFollowersListener);

        super.onDestroy();
    }

}
