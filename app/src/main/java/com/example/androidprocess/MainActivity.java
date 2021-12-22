package com.example.androidprocess;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import com.example.androidprocess.firstline.FirstLineCodeActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, FirstLineCodeActivity.class);
        startActivity(intent);
        finish();

        initView();
    }

    private void initView() {
        Button btFirstLine = findViewById(R.id.bt_first_line);
        btFirstLine.setOnClickListener(v -> {
            Intent intent = new Intent(this, FirstLineCodeActivity.class);
            startActivity(intent);
        });
    }

}