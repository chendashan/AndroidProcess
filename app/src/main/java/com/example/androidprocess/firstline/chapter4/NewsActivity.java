package com.example.androidprocess.firstline.chapter4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.androidprocess.R;

public class NewsActivity extends AppCompatActivity {
    private int newsType = 2;
    private FrameLayout fmContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        fmContent = findViewById(R.id.fl_news_content);
        switchNewsType();

        Button btSwitch = findViewById(R.id.bt_news_type);
        btSwitch.setOnClickListener(v -> {
            newsType = newsType == 1 ? 2 : 1;
            switchNewsType();
        });
    }

    private void switchNewsType() {
        if (newsType == 1) {
            fmContent.setVisibility(View.GONE);
        } else {
            fmContent.setVisibility(View.VISIBLE);
        }
        NewsTitleFragment titleFragment = (NewsTitleFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_news_title);
        if (titleFragment != null) {
            titleFragment.setNewsType(newsType);
        }
    }
}