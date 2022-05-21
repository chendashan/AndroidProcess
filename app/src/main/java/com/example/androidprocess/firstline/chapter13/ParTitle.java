package com.example.androidprocess.firstline.chapter13;

import android.os.Parcel;
import android.os.Parcelable;

public class ParTitle implements Parcelable {

    private int index;

    private String title;

    public ParTitle() {
    }

    public ParTitle(int index, String title) {
        this.index = index;
        this.title = title;
    }

    protected ParTitle(Parcel in) {
        index = in.readInt();
        title = in.readString();
    }

    public static final Creator<ParTitle> CREATOR = new Creator<ParTitle>() {
        @Override
        public ParTitle createFromParcel(Parcel in) {
            ParTitle parTitle = new ParTitle();
            parTitle.index = in.readInt();
            parTitle.title = in.readString();
            return parTitle;
        }

        @Override
        public ParTitle[] newArray(int size) {
            return new ParTitle[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(index);
        dest.writeString(title);
    }
}
