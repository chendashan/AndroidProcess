package com.example.androidprocess.firstline.chapter6;

import android.os.Bundle;

import com.example.androidprocess.firstline.ChapterActivity;

public class ChapterSixActivity extends ChapterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addChapter("文件存储", FilePersistenceActivity.class);
        addChapter("SharedPreference", SharedPreferenceActivity.class);

        showChapterList();
    }
}