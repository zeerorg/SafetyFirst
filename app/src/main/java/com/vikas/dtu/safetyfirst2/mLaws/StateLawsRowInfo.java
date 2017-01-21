package com.vikas.dtu.safetyfirst2.mLaws;

import android.graphics.drawable.Drawable;

/**
 * Created by DHEERAJ on 21-12-2016.
 */

public class StateLawsRowInfo {
    public Drawable backgroundid;
    public String text;
    public StateLawsRowInfo(String text, Drawable backgroundid){
        this.backgroundid=backgroundid;
        this.text=text;
    }
}
