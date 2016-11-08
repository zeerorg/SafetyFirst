package com.vikas.dtu.safetyfirst2.mKnowItActivity;

/**
 * Created by krishna on 27/10/16.
 */

public class KnowIt {
    private String mKnowItname;
    private int mKnowItImage;

    public KnowIt() {
    }

    public KnowIt(String name, int thumbnail) {
        this.mKnowItname = name;
        this.mKnowItImage = thumbnail;
    }

    public String getmKnowItname() {
        return mKnowItname;
    }

    public int getmKnowItImage() {
        return mKnowItImage;
    }

}

