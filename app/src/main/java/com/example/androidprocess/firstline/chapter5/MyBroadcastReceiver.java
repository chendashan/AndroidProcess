package com.example.androidprocess.firstline.chapter5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "收到了自己的广播", Toast.LENGTH_SHORT).show();
    }
}