package com.vikas.dtu.safetyfirst.mData;

import com.google.firebase.database.IgnoreExtraProperties;

// [START blog_user_class]
@IgnoreExtraProperties
public class User {

    public String userImage;
    public String username;
    public String email;
    public int questions_asked;
    public int answers_given;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email, String userImage) {
        this.username = username;
        this.email = email;
        this.userImage = userImage;
    }

    public String getPhotoUrl() {
        return userImage;
    }
}
// [END blog_user_class]
