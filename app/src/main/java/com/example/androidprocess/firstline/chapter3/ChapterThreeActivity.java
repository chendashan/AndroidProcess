package com.example.androidprocess.firstline.chapter3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.androidprocess.R;

public class ChapterThreeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_three);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("第三章 UI开发");
        }

        Button btCustom = findViewById(R.id.bt_ui_custom);
        btCustom.setOnClickListener(this);
        Button btListView = findViewById(R.id.bt_ui_listview);
        btListView.setOnClickListener(this);
        Button btRecView = findViewById(R.id.bt_ui_recycle_vew);
        btRecView.setOnClickListener(this);
        Button btChat = findViewById(R.id.bt_ui_message);
        btChat.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_ui_custom:
                Intent intentCustom = new Intent(ChapterThreeActivity.this, UiCustomActivity.class);
                startActivity(intentCustom);
                break;
            case R.id.bt_ui_listview:
                Intent intentLv = new Intent(ChapterThreeActivity.this, ListViewActivity.class);
                startActivity(intentLv);
                break;
            case R.id.bt_ui_recycle_vew:
                Intent intentRcv = new Intent(ChapterThreeActivity.this, RecyclerViewActivity.class);
                startActivity(intentRcv);
                break;
            case R.id.bt_ui_message:
                Intent intentChat = new Intent(ChapterThreeActivity.this, UiMessageActivity.class);
                startActivity(intentChat);
                break;
            default:
                break;
        }
    }
}