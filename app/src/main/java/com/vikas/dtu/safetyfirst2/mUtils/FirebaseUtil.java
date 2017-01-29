package com.vikas.dtu.safetyfirst2.mUtils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Vikas on 26-01-2017.
 */

public class FirebaseUtil {
    public static DatabaseReference getBaseRef() {
        return FirebaseDatabase.getInstance().getReference();
    }
    public static DatabaseReference getPeopleRef() {
        return getBaseRef().child("people");
    }
    public static String getCurrentUserId() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            return user.getUid();
        }
        return null;
    }

}
