package com.example.androidprocess.firstline.chapter6;

import android.content.Intent;
import android.os.Bundle;

import com.example.androidprocess.firstline.ChapterActivity;
import com.example.androidprocess.firstline.FirstLineCodeActivity;
import com.example.androidprocess.firstline.chapter13.ChapterTitle;

public class ChapterSixActivity extends ChapterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ChapterTitle chapterTitle = (ChapterTitle) getIntent().getSerializableExtra(FirstLineCodeActivity.PARAM_TITLE);
        if (chapterTitle != null && chapterTitle.getTitle() != null) {
            setTitle(chapterTitle.getTitle());
        }

        addChapter("文件存储", FilePersistenceActivity.class);
        addChapter("SharedPreference", SharedPreferenceActivity.class);
        addChapter("DataBase", DataBaseActivity.class);
        addChapter("LitePal", LitePalActivity.class);

        showChapterList();
    }
}