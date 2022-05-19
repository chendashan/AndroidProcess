package com.example.androidprocess.firstline.chapter7;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidprocess.R;

/**
 * 内容提供器测试代码，需要在别的项目中使用，放在此处只是保存
 */

public class ProviderTestActivity extends AppCompatActivity {

    private String newId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_test);

        Button btAdd = findViewById(R.id.bt_pt_add);
        btAdd.setOnClickListener(v -> {
            Uri uri = Uri.parse("content://com.example.androidprocess.provider/book");
            ContentValues values = new ContentValues();
            values.put("name", "A Clash of Kings");
            values.put("author", "George Martin");
            values.put("pages", 1040);
            values.put("price", 22.85);
            Uri newUri = getContentResolver().insert(uri, values);
            newId = newUri.getPathSegments().get(1);
        });

        Button btQuery = findViewById(R.id.bt_pt_query);
        btQuery.setOnClickListener(v -> {
            Uri uri = Uri.parse("content://com.example.androidprocess.provider/book");
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    String author = cursor.getString(cursor.getColumnIndexOrThrow("author"));
                    int pages = cursor.getInt(cursor.getColumnIndexOrThrow("pages"));
                    double price = cursor.getDouble(cursor.getColumnIndexOrThrow("price"));

                    Log.d("ProviderTestActivity", "name: " + name + " author: " + author + " pages: " + pages + " price: " + price);
                }
                cursor.close();
            }
        });
    }
}