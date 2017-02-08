package com.vikas.dtu.safetyfirst2.mData;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

// [START comment_class]
@IgnoreExtraProperties
public class Comment  {

    public String uid;
    public String author;
    public String text;
    public int upvoteCount = 0;
    public int downvoteCount = 0;
    public String xmlText = null;
    public String image = null;
    public String file = null;
    public Map<String, Boolean> upvoteusers = new HashMap<>();

    public Comment() {
        // Default constructor required for calls to DataSnapshot.getValue(Comment.class)
    }

    public Comment(String uid, String author, String text, String xmlText, String image, String file , int upvoteCount ) {
        this.uid = uid;
        this.author = author;
        this.text = text;
        this.xmlText = xmlText;
        this.image = image;
        this.file = file;
        this.upvoteCount=upvoteCount;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("author", author);
        result.put("text", text);
        result.put("xmlText", xmlText);
        result.put("file", file);
        result.put("image", image);
        result.put("upvotecount",upvoteCount);
        result.put("upvoteusers",upvoteusers);
        return result;
    }


//    @Override
//    public int compareTo(Comment o) {
//        return o.upvoteCount-upvoteCount;
//    }
}
// [END comment_class]
