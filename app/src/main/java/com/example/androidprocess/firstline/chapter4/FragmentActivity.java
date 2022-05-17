package com.example.androidprocess.firstline.chapter4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.androidprocess.R;

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
    }
}