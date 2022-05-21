package com.example.androidprocess.firstline.chapter13;

import java.io.Serializable;

public class ChapterTitle implements Serializable {

    private int index;

    private String title;

    public ChapterTitle(int index, String title) {
        this.index = index;
        this.title = title;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
