package com.example.androidprocess.firstline.chapter4;


import android.os.Bundle;

import com.example.androidprocess.firstline.ChapterActivity;

public class ChapterFourActivity extends ChapterActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("第4章 Fragment");

        addChapter("碎片的简单使用", FragmentActivity.class);
        addChapter("简易新闻界面", NewsActivity.class);

        showChapterList();
    }
}