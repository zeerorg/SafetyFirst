package com.vikas.dtu.safetyfirst2;

/**
 * Created by Mukul on 1/23/2017.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.vikas.dtu.safetyfirst2.mData.User;
import com.vikas.dtu.safetyfirst2.mDiscussion.fragment.BookmarkedFragment;
import com.vikas.dtu.safetyfirst2.mDiscussion.fragment.MyPostsFragment;
import com.vikas.dtu.safetyfirst2.mDiscussion.fragment.NewPostFragment;
import com.vikas.dtu.safetyfirst2.mDiscussion.fragment.RecentPostsFragment;
import com.vikas.dtu.safetyfirst2.mUser.UpdateProfile;
import com.vikas.dtu.safetyfirst2.mUser.UpdateProfileFragment;
import com.vikas.dtu.safetyfirst2.mUser.UserProfileFragment;

import static com.vikas.dtu.safetyfirst2.mUtils.FirebaseUtil.getCurrentUserId;


public class CategoryAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private User user;

    public CategoryAdapter(Context context, FragmentManager fm, User currentUser)

    {
        super(fm);
        mContext = context;
        user = currentUser;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0)
            return new RecentPostsFragment();
        else if (position == 1)
            return new MyPostsFragment();
        else if (position == 2) {
            if (user.getDesignation() != null) {
                Bundle bundle = new Bundle();
                bundle.putString(UserProfileFragment.USER_ID_EXTRA_NAME, getCurrentUserId());
                Fragment userProfile = new UserProfileFragment();
                userProfile.setArguments(bundle);
                return userProfile;
            }
            else
                return new UpdateProfileFragment();
        }
        else
            return new NewPostFragment();
    }

    @Override
    public int getCount() {
        return 4;
    }



}
