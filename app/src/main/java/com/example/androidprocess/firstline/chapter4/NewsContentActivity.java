package com.example.androidprocess.firstline.chapter4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.androidprocess.R;

public class NewsContentActivity extends AppCompatActivity {
    public static final String PARAM_TITLE = "news_title";
    public static final String PARAM_CONTENT = "news_content";

    public static void actionStart(Context context, String title, String content) {
        Intent intent = new Intent(context, NewsContentActivity.class);
        intent.putExtra(PARAM_TITLE, title);
        intent.putExtra(PARAM_CONTENT, content);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);

        String newsTitle = getIntent().getStringExtra(PARAM_TITLE);
        String newsContent = getIntent().getStringExtra(PARAM_CONTENT);

        NewsContentFragment contentFragment = (NewsContentFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_news_content);
        if (contentFragment != null) {
            contentFragment.refresh(newsTitle, newsContent);
        }
    }
}