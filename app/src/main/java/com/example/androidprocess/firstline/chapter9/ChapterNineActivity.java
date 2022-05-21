package com.example.androidprocess.firstline.chapter9;

import android.os.Bundle;

import com.example.androidprocess.firstline.ChapterActivity;

public class ChapterNineActivity extends ChapterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addChapter("WebView的用法", WebViewTestActivity.class);
        addChapter("Http访问", HttpTestActivity.class);
        addChapter("Json解析", JsonTestActivity.class);

        showChapterList();
    }
}
