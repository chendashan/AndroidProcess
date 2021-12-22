package com.example.androidprocess.firstline;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidprocess.R;

public class ThirdActivity extends BaseLineActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("cds", "ThirdActivity TaskId: " + getTaskId());
        setContentView(R.layout.activity_third);

        Button button = findViewById(R.id.button_3);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        });
    }
}