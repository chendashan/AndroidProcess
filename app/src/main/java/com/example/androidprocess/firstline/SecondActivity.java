package com.example.androidprocess.firstline;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.androidprocess.R;

public class SecondActivity extends BaseLineActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("cds", "SecondActivity TaskId: " + getTaskId());
        setContentView(R.layout.activity_second);

        String data = getIntent().getStringExtra("extra_data");
        if (data != null) {
            Log.d("cds", data);
        }

        Button btBack = findViewById(R.id.bt_second_back);
        btBack.setOnClickListener(v -> {
            backResult();
        });

        Button btFirst = findViewById(R.id.bt_jump_first);
        btFirst.setOnClickListener(v -> {
            Intent intent = new Intent(this, FirstActivity.class);
            startActivity(intent);
        });

        Button btClose = findViewById(R.id.bt_close_line);
        btClose.setOnClickListener(v -> {
            ActivityCollector.finishAll();
        });
    }

    @Override
    public void onBackPressed() {
        backResult();
    }

    private void backResult() {
        Intent intent = new Intent();
        intent.putExtra("result_data", "返回的数据");
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("cds", "SecondActivity onDestroy: ");
    }
}