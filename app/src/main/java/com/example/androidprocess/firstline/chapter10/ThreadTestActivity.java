package com.example.androidprocess.firstline.chapter10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidprocess.R;

public class ThreadTestActivity extends AppCompatActivity {

    public static final int UPDATE_TEXT = 1;

    private TextView tvText;

    private Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case UPDATE_TEXT:
                    tvText.setText("Nice to meet you");
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_test);

        tvText = findViewById(R.id.tv_change_text);
        Button btChange = findViewById(R.id.bt_change_text);

        btChange.setOnClickListener(v -> {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Message msg = new Message();
                    msg.what = UPDATE_TEXT;
                    handler.sendMessage(msg);
                }
            }).start();
        });
    }
}