package com.example.vikas.safetyfirst.mData;

/**
 * Created by Vikas on 10-07-2016.
 */
public class News {

    private String headline;
    private String imgsrc;
    private String articlesrc;
    private String article;
    private String url;

    public News() {
    }



    public String getUrl() {
        return url;
    }

    public String getArticlesrc() {
        return articlesrc;
    }

    public void setArticlesrc(String articlesrc) {
        this.articlesrc = articlesrc;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }


    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getImgSrc() {
        return imgsrc;
    }

    public void setImgSrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }
}
