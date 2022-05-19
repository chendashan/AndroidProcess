package com.example.androidprocess.firstline;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.androidprocess.R;
import com.example.androidprocess.firstline.chapter3.ChapterThreeActivity;
import com.example.androidprocess.firstline.chapter4.ChapterFourActivity;
import com.example.androidprocess.firstline.chapter6.ChapterSixActivity;
import com.example.androidprocess.firstline.chapter7.ChapterSevenActivity;

public class FirstLineCodeActivity extends BaseLineActivity implements View.OnClickListener{

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_line_code);
        Log.d(TAG, "onCreate: execute");

        Button btActivity = findViewById(R.id.bt_activity_usage);
        btActivity.setOnClickListener(this);
        Button btLife = findViewById(R.id.bt_activity_life);
        btLife.setOnClickListener(this);
        Button btUi = findViewById(R.id.bt_activity_ui);
        btUi.setOnClickListener(this);
        Button btFragment = findViewById(R.id.bt_activity_fragment);
        btFragment.setOnClickListener(this);
        Button btSix = findViewById(R.id.bt_line_data);
        btSix.setOnClickListener(this);
        Button btContent = findViewById(R.id.bt_line_content);
        btContent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_activity_usage:
                Intent intent = new Intent(this, FirstActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_activity_life:
                Intent intentLife = new Intent(this, LifeCycleActivity.class);
                startActivity(intentLife);
                break;
            case R.id.bt_activity_ui:
                Intent inUi = new Intent(this, ChapterThreeActivity.class);
                startActivity(inUi);
                break;
            case R.id.bt_activity_fragment:
                Intent intentFragment = new Intent(this, ChapterFourActivity.class);
                startActivity(intentFragment);
                break;
            case R.id.bt_line_data:
                Intent intentData = new Intent(this, ChapterSixActivity.class);
                startActivity(intentData);
                break;
            case R.id.bt_line_content:
                Intent intentContent = new Intent(this, ChapterSevenActivity.class);
                startActivity(intentContent);
                break;

            default:
                break;
        }
    }
}