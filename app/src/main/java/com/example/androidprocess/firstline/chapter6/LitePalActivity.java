package com.example.androidprocess.firstline.chapter6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.androidprocess.R;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

public class LitePalActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lite_pal);

        Button btLpCreate = findViewById(R.id.bt_lp_create);
        btLpCreate.setOnClickListener(v -> {
            LitePal.getDatabase();
        });

        Button btAdd = findViewById(R.id.bt_lp_add);
        btAdd.setOnClickListener(this);
        Button btUpdate = findViewById(R.id.bt_lp_update);
        btUpdate.setOnClickListener(this);
        Button btDelete = findViewById(R.id.bt_lp_delete);
        btDelete.setOnClickListener(this);
        Button btQuery = findViewById(R.id.bt_lp_query);
        btQuery.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_lp_add:
                Book book = new Book();
                book.setAuthor("Brow");
                book.setName("Three Height");
                book.setPages(250);
                book.setPrice(37.3);
                book.setPress("UnKnow");
                book.save();
                break;
            case R.id.bt_lp_update:
                Book book2 = new Book();
                book2.setPrice(19.99);
                book2.setPress("CNN");
                book2.updateAll("name = ? and author = ?", "Three Height", "Brow");
                break;
            case R.id.bt_lp_delete:
                DataSupport.deleteAll(Book.class, "price < ?", "20");
                break;
            case R.id.bt_lp_query:
                List<Book> bookList = DataSupport.findAll(Book.class);
                for (Book b : bookList) {
                    Log.d("LitePalActivity", "name: " + b.getName() + " price: " + b.getPrice());
                }
                break;
            default:
                break;
        }
    }
}