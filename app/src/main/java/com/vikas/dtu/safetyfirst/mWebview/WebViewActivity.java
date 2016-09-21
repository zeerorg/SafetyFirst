package com.vikas.dtu.safetyfirst.mWebview;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.vikas.dtu.safetyfirst.BaseActivity;
import com.vikas.dtu.safetyfirst.R;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ObservableWebView;
import com.github.ksoichiro.android.observablescrollview.ScrollState;

public class WebViewActivity extends BaseActivity implements ObservableScrollViewCallbacks {

    private ObservableWebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        String url = getIntent().getStringExtra("Url");
      //  Toast.makeText(this, url, Toast.LENGTH_LONG).show();

        webView = (ObservableWebView) findViewById(R.id.web);
        webView.setScrollViewCallbacks(this);

        webView.getSettings().setJavaScriptEnabled(true); // enable javascript
        webView.setWebViewClient(new MyWebViewClient(url));
        webView.loadUrl(url);
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
    }

    @Override
    public void onDownMotionEvent() {
    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {
        ActionBar ab = getSupportActionBar();
        if (ab == null) {
            return;
        }
        if (scrollState == ScrollState.UP) {
            if (ab.isShowing()) {
                ab.hide();
            }
        } else if (scrollState == ScrollState.DOWN) {
            if (!ab.isShowing()) {
                ab.show();
            }
        }
    }

    private class MyWebViewClient extends WebViewClient {

        private String currentURL;

        public MyWebViewClient(String currentURL){
            this.currentURL = currentURL;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if(url.equals(currentURL)){
                view.loadUrl(url);
            }
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            hideProgressDialog();
            super.onPageFinished(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            showProgressDialog();
            super.onPageStarted(view, url, favicon);
        }
    }

    @Override
    public  void onBackPressed(){
        finish();
    }
}
