package com.example.androidprocess.firstline.chapter6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.androidprocess.R;

public class DataBaseActivity extends AppCompatActivity {
    public static final String TAG = "DataBaseActivity";

    private MyDataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);

        dbHelper = new MyDataBaseHelper(this, "BookStore.db", null, 2);
        Button btCreate = findViewById(R.id.bt_db_create);
        btCreate.setOnClickListener(v -> {
            dbHelper.getWritableDatabase();
        });

        Button btAdd = findViewById(R.id.bt_db_add);
        btAdd.setOnClickListener(v -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            //组装第一条数据
            values.put("name", "The Da Vinci Code");
            values.put("author", "Dan Brown");
            values.put("pages", 454);
            values.put("price", 18.94);
            db.insert("Book", null, values);
            values.clear();
            //组装第二条数据
            values.put("name", "The Lost Weight");
            values.put("author", "Tony");
            values.put("pages", 381);
            values.put("price", 45.02);
            db.insert("Book", null, values);
        });

        Button btUpdate = findViewById(R.id.bt_db_update);
        Button btDelete = findViewById(R.id.bt_db_delete);

        btUpdate.setOnClickListener(v -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("price", 9.99);
            db.update("Book", values, "name = ?", new String[]{"The Lost Weight"});
        });

        btDelete.setOnClickListener(v -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.delete("Book", "pages > ?", new String[]{"400"});
        });

        Button btQuery = findViewById(R.id.bt_db_query);
        btQuery.setOnClickListener(v -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            Cursor cursor = db.query("Book", null, null, null, null, null,null);
            //数据指针移动到第一行
            if (cursor.moveToFirst()) {
                do {
                    String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                    String author = cursor.getString(cursor.getColumnIndexOrThrow("author"));
                    int pages = cursor.getInt(cursor.getColumnIndexOrThrow("pages"));
                    double price = cursor.getDouble(cursor.getColumnIndexOrThrow("price"));

                    Log.d(TAG, "name: " + name);
                    Log.d(TAG, "author: " + author);
                    Log.d(TAG, "pages: " + pages);
                    Log.d(TAG, "price: " + price);

                    //数据的指针向下移一行
                } while (cursor.moveToNext());
            }
            //用完关闭，释放资源
            cursor.close();
        });
    }
}