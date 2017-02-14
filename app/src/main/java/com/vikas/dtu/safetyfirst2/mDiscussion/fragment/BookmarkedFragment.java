package com.vikas.dtu.safetyfirst2.mDiscussion.fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.vikas.dtu.safetyfirst2.R;
import com.vikas.dtu.safetyfirst2.mData.User;
import com.vikas.dtu.safetyfirst2.mUser.UserProfileActivity;
import com.vikas.dtu.safetyfirst2.mUtils.FirebaseUtil;

import java.util.HashMap;
import java.util.Map;

import static com.vikas.dtu.safetyfirst2.mUtils.FirebaseUtil.getCurrentUserId;

/**
 * Created by Mukul on 1/23/2017.
 */

public class BookmarkedFragment extends Fragment {
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
    //TextView mQuestions;
    // TextView mAnswers;
    TextView mEmail;
    ProgressDialog mProgressDialog;
    private View mainView;

    private ValueEventListener mUserListener;
    CollapsingToolbarLayout collapsingToolbar;
    @Override
    public void onStart() {
        super.onStart();

        showProgressDialog();

        profilephotoRef = mstorageRef.child(user.getUid()+"/ProfilePhoto.jpg");
        // Add value event listener to the news
        // [START user_value_event_listener]
        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Get User object and use the values to update the UI
                User user = dataSnapshot.getValue(User.class);
                // [START_EXCLUDE]


                hideProgressDialog();
                if (user.getDesignation() == null ){
                    Toast.makeText(getContext(), "User has not Updated his profile", Toast.LENGTH_SHORT).show();

                    /*startActivity(new Intent(getContext(), UpdateProfile.class));
                    finish();*/
                }

               /* else{

                }*/

                mDesignation.setText(user.getDesignation());
                mQualification.setText(user.getQualification());
                mCompany.setText(user.getCompany());
                mEmail.setText(user.getEmail());
                //  mQuestions.setText(String.valueOf(user.getQuestionsAsked())+" Questions");
                //  mAnswers.setText(String.valueOf(user.getAnswersGiven())+" Answers");

                //  collapsingToolbar.setExpandedTitleTextAppearance(R.style.user_profile);
                //  collapsingToolbar.setTitle(user.getFull_name());

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
                                Toast.makeText(getContext(), "Failed to get Url!", Toast.LENGTH_SHORT).show();
                            }
                        });

                        Glide.with(getContext()).load(user.getPhotoUrl()).override(165,165).into(mPhoto);
                    }else if (profilephotoRef==null&&user.getPhotoUrl() == null) {
                        mPhoto.setImageDrawable(ContextCompat.getDrawable(getContext(),
                                R.drawable.ic_action_account_circle_40));
                    } else if(profilephotoRef==null&&user.getPhotoUrl()!=null) {
                        Glide.with(getContext())
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
                Toast.makeText(getContext(), "Failed to load profile.",
                        Toast.LENGTH_SHORT).show();
                // [END_EXCLUDE]
            }
        };
        useRef.addValueEventListener(userListener);
        // [END news_value_event_listener]

        // Keep copy of post listener so we can remove it when app stops
        mUserListener = userListener;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.activity_user_profile, container, false);

        mstorageRef= FirebaseStorage.getInstance().getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();
        mPhoto = (ImageView) mainView.findViewById(R.id.backdrop);
        mDesignation = (TextView) mainView.findViewById(R.id.user_designation);
        mCompany = (TextView) mainView.findViewById(R.id.user_company);
        mQualification = (TextView) mainView.findViewById(R.id.user_qualification);
        mEmail = (TextView) mainView.findViewById(R.id.user_email);

        mUserId = getCurrentUserId();
        // Toast.makeText(this,  mUserId, Toast.LENGTH_SHORT).show();
        collapsingToolbar =
                (CollapsingToolbarLayout) mainView.findViewById(R.id.collapsing_toolbar);

        mPeopleRef = FirebaseUtil.getPeopleRef();

        useRef = FirebaseDatabase.getInstance().getReference()
                .child("users").child( mUserId);

        final String currentUserId = getCurrentUserId();

        final FloatingActionButton followUserFab = (FloatingActionButton) mainView.findViewById(R.id
                .follow_user_fab);
        mFollowingListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    followUserFab.setImageDrawable(ContextCompat.getDrawable(
                            getContext(), R.drawable.ic_done_24dp));
                } else {
                    followUserFab.setImageDrawable(ContextCompat.getDrawable(
                            getContext(), R.drawable.ic_person_add_24dpd));
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
                    Toast.makeText(getContext(), "You need to sign in to follow someone.",
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
                                    Toast.makeText(getContext(), R.string
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

        return mainView;
    }

    @Override
    public void onDestroyView() {
        if (FirebaseUtil.getCurrentUserId() != null) {
            mPeopleRef.child(getCurrentUserId()).child("following").child(mUserId)
                    .removeEventListener(mFollowingListener);
        }

        if(mPersonRef != null)
            mPersonRef.child(mUserId).removeEventListener(mPersonInfoListener);

        if(useRef != null)
            useRef.removeEventListener(mUserListener);


        if(mFollowersRef != null)
            mFollowersRef.removeEventListener(mFollowersListener);
        super.onDestroyView();
    }

    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getContext());
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
}
