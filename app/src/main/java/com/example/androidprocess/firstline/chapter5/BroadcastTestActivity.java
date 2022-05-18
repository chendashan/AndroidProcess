package com.example.androidprocess.firstline.chapter5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidprocess.R;

public class BroadcastTestActivity extends AppCompatActivity {

    private NetworkChangeReceive networkReceive;
    private IntentFilter intentFilter;

    private LocalReceiver localReceiver;
    private LocalBroadcastManager localBroadcastManager;
    private IntentFilter localFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_test);

        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkReceive = new NetworkChangeReceive();
        registerReceiver(networkReceive, intentFilter);

        Button button = findViewById(R.id.bt_broadcast_send);
        button.setOnClickListener(v -> {
            Intent intent = new Intent("com.example.androidprocess.MY_BROADCAST");
            //android 8 以上静态注册的广播要指定包名
            intent.setPackage(getPackageName());
            sendBroadcast(intent);
        });


        //发送本地广播
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localFilter = new IntentFilter();
        localFilter.addAction("com.example.androidprocess.LOCAL_BROADCAST");
        localReceiver = new LocalReceiver();
        localBroadcastManager.registerReceiver(localReceiver, localFilter);

        Button btLocalBroadcast = findViewById(R.id.bt_local_send);
        btLocalBroadcast.setOnClickListener(v -> {
            Intent intent = new Intent("com.example.androidprocess.LOCAL_BROADCAST");
            localBroadcastManager.sendBroadcast(intent);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkReceive);
        localBroadcastManager.unregisterReceiver(localReceiver);
    }

    class NetworkChangeReceive extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "network changes", Toast.LENGTH_SHORT).show();

            //获取网络连接系统服务
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                Toast.makeText(context, "network is available", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "network is unavailable", Toast.LENGTH_SHORT).show();
            }


        }
    }

    class LocalReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "received local broadcast", Toast.LENGTH_SHORT).show();
        }
    }

}