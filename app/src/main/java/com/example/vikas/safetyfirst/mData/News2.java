package com.example.vikas.safetyfirst.mData;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;



/**
 * Created by Vikas on 10-07-2016.
 */
// [START news_class]
@IgnoreExtraProperties
public class News2 {

    public String headline;
    public String imgsrc;
    public String articlesrc;
    public String article;
    public String url;

    public News2() {
    }


    public News2 (String headline, String imgsrc, String articlesrc, String article, String url) {
        this.headline = headline;
        this.imgsrc = imgsrc;
        this.articlesrc = articlesrc;
        this.article = article;
        this.url = url;
    }

    // [START news_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("headline", headline);
        result.put("imgsrc", imgsrc);
        result.put("articlesrc", articlesrc);
        result.put("article", article);
        result.put("url", url);

        return result;
    }
    // [END news_to_map]
}
// [END news_class]