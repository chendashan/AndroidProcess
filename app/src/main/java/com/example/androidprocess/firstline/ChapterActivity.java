package com.example.androidprocess.firstline;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.androidprocess.R;

import java.util.ArrayList;
import java.util.List;

public class ChapterActivity extends AppCompatActivity {

    private List<Chapter> mChapterList;

    private ChapterAdapter chapterAdapter;
    private RecyclerView rcvChapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);

        mChapterList = new ArrayList<>();
        rcvChapter = findViewById(R.id.rcv_chapter);
        rcvChapter.setLayoutManager(new LinearLayoutManager(this));
    }

    protected void setTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    protected void addChapter(String name, Class<?> cls) {
        Chapter chapter = new Chapter(name, cls);
        mChapterList.add(chapter);
    }

    protected void showChapterList() {
        chapterAdapter = new ChapterAdapter(mChapterList);
        rcvChapter.setAdapter(chapterAdapter);
    }
}