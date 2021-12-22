package com.example.androidprocess.firstline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.androidprocess.R;

public class BestStartActivity extends AppCompatActivity {
    public static final String PARAM_1 = "param_1";
    public static final String PARAM_2 = "param_2";

    public static void actionStart(Context context, String data1, String data2) {
        Intent intent = new Intent(context, BestStartActivity.class);
        intent.putExtra(PARAM_1, data1);
        intent.putExtra(PARAM_2, data2);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_start);

        TextView tv1 = findViewById(R.id.tv_data1);
        TextView tv2 = findViewById(R.id.tv_data2);

        Intent intent = getIntent();
        if (intent != null) {
            String data1 = intent.getStringExtra(PARAM_1);
            String data2 = intent.getStringExtra(PARAM_2);

            tv1.setText(data1);
            tv2.setText(data2);
        }
    }
}