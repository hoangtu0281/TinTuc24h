package com.example.hoangtu.tintuc24h;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        initView();
    }

    private void initView() {
        initDiaLog();
        webView = findViewById(R.id.web_view);
        webView.getSettings().setSupportZoom(true);
        String link = getIntent().getStringExtra("KEY_LINK_NEWS");
        webView.loadUrl(link);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.setWebViewClient(webViewClient);

       progressDialog.show();
    }

    private void initDiaLog() {
        progressDialog =new ProgressDialog(this);
        progressDialog.setMessage("loading...");
        progressDialog.setCancelable(false);
    }
    private WebViewClient webViewClient = new WebViewClient(){
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (progressDialog!=null && progressDialog.isShowing()){
                progressDialog.dismiss();
            }
        }
    };

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()){
            webView.goBack();
        }
        else{
            super.onBackPressed();
        }
    }
}
