package com.example.androidprocess.firstline.chapter5;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.androidprocess.R;

public class HomeActivity extends BaseLogoutActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btLogout = findViewById(R.id.bt_lg_send);
        btLogout.setOnClickListener(v -> {
            Intent intent = new Intent("com.example.androidprocess.LOGOUT");
            //intent.setPackage(getPackageName());
            sendBroadcast(intent);
        });

        Button btCenter = findViewById(R.id.bt_lg_center);
        btCenter.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, UserCenterActivity.class);
            startActivity(intent);
        });
    }
}