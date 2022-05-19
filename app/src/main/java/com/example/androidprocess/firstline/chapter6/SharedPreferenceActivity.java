package com.example.androidprocess.firstline.chapter6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidprocess.R;

public class SharedPreferenceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_perference);

        Button btSpSave = findViewById(R.id.bt_sp_save);
        btSpSave.setOnClickListener(v -> {
            SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
            editor.putString("name", "Tony");
            editor.putInt("age", 22);
            editor.putBoolean("isHigh", false);
            editor.apply();
        });

        Button btRestore = findViewById(R.id.bt_sp_restore);
        btRestore.setOnClickListener(v -> {
            SharedPreferences sp = getSharedPreferences("data", MODE_PRIVATE);
            String name = sp.getString("name", "");
            int age = sp.getInt("age", 0);
            boolean isHigh = sp.getBoolean("isHigh", false);

            String string = "name: " + name + " age: " + age + " isHigh: " + isHigh;
            Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
        });
    }
}