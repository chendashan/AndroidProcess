package com.example.androidprocess.firstline.chapter5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.androidprocess.R;

public class UserCenterActivity extends BaseLogoutActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_center);

        Button button = findViewById(R.id.bt_center_logout);
        button.setOnClickListener(v -> {
            Intent intent = new Intent("com.example.androidprocess.LOGOUT");
            sendBroadcast(intent);
        });
    }
}