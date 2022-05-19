package com.example.androidprocess.firstline.chapter7;

import android.os.Bundle;

import com.example.androidprocess.firstline.ChapterActivity;

/**
 * @author chendashan
 * @date 2022/5/19
 */
public class ChapterSevenActivity extends ChapterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addChapter("运行时权限", RunPermissionActivity.class);
        addChapter("获取联系人", ContactsTestActivity.class);

        showChapterList();

    }
}
