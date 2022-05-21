package com.example.androidprocess.firstline.chapter9;

import android.os.Bundle;

import com.example.androidprocess.firstline.ChapterActivity;
import com.example.androidprocess.firstline.chapter10.DownloadTestActivity;
import com.example.androidprocess.firstline.chapter10.ServiceTestActivity;
import com.example.androidprocess.firstline.chapter10.ThreadTestActivity;

public class ChapterNineActivity extends ChapterActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addChapter("WebView的用法", WebViewTestActivity.class);
        addChapter("Http访问", HttpTestActivity.class);
        addChapter("Json解析", JsonTestActivity.class);
        addChapter("异步消息处理机制", ThreadTestActivity.class);
        addChapter("服务Test", ServiceTestActivity.class);
        addChapter("下载Demo", DownloadTestActivity.class);

        showChapterList();
    }
}
