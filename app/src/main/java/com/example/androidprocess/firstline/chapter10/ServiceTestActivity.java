package com.example.androidprocess.firstline.chapter10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.androidprocess.R;

public class ServiceTestActivity extends AppCompatActivity implements View.OnClickListener {

    private MyService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (MyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_test);

        Button btStart = findViewById(R.id.bt_service_start);
        Button btStop = findViewById(R.id.bt_service_stop);
        btStart.setOnClickListener(this);
        btStop.setOnClickListener(this);

        Button btBind = findViewById(R.id.bt_service_bind);
        Button btUnbind = findViewById(R.id.bt_service_unbind);
        btBind.setOnClickListener(this);
        btUnbind.setOnClickListener(this);

        Button btIntentService = findViewById(R.id.bt_start_intent_service);
        btIntentService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_service_start:
                Intent intentStart = new Intent(this, MyService.class);
                startService(intentStart);
                break;
            case R.id.bt_service_stop:
                Intent intentStop = new Intent(this, MyService.class);
                stopService(intentStop);
                break;
            case R.id.bt_service_bind:
                Intent intentBind = new Intent(this, MyService.class);
                bindService(intentBind, connection, BIND_AUTO_CREATE);
                break;
            case R.id.bt_service_unbind:
                unbindService(connection);
                break;
            case R.id.bt_start_intent_service:
                Log.d("MyIntentService", "Activity Thread is " + Thread.currentThread().getId());
                Intent intentService = new Intent(this, MyIntentService.class);
                startService(intentService);
                break;
            default:
                break;
        }
    }
}