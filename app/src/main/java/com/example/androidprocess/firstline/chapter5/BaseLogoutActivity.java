package com.example.androidprocess.firstline.chapter5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidprocess.firstline.ActivityCollector;

public class BaseLogoutActivity extends AppCompatActivity {

    private LogoutReceiver logoutReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.androidprocess.LOGOUT");
        logoutReceiver = new LogoutReceiver();
        registerReceiver(logoutReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(logoutReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    class LogoutReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Warning");
            builder.setMessage("你的账号再其他地方登录，请重新登录");
            builder.setCancelable(false);
            builder.setPositiveButton("Ok", (dialog, which) -> {
               ActivityCollector.finishAll();
               Intent intentLogin = new Intent(context, LoginActivity.class);
               context.startActivity(intentLogin);
            });
            builder.show();
        }
    }
}
