package com.example.androidprocess.firstline.chapter9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.androidprocess.R;

public class WebViewTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_test);

        WebView webView = findViewById(R.id.web_view);

        //让webView支持JavaScript脚本
        webView.getSettings().setJavaScriptEnabled(true);
        //跳转网页时仍然再此webView中，不跳转系统浏览器
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.baidu.com");
    }
}