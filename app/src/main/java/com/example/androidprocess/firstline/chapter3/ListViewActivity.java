package com.example.androidprocess.firstline.chapter3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.androidprocess.R;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    //Jan. January 一月;Feb. February 二月;Mar. March 三月;Apr. April 四月;May.May 五月;Jun. June 六月;
    // Jul. July 七月;Aug. August 八月;Sep. September九月;Oct. October 十月;Nov. November 十一月;Dec. December 十二月

    private String[] array = {"January", "Jan", "February", "Feb", "March", "Mar", "April", "Apr", "May", "May", "June", "Jun",
                                "July", "Jul", "August", "Aug", "September", "Sep", "October", "Oct", "November", "Nov", "December", "Dec"};
    private int[] resArray = {R.drawable.ic_fruit_apple, R.drawable.ic_fruit_banana, R.drawable.ic_fruit_cherry, R.drawable.ic_fruit_grape, R.drawable.ic_fruit_mango};
    private List<Fruit> fruitList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        initFruit();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, array);
        FruitAdapter fruitAdapter = new FruitAdapter(this, R.layout.item_fruit, fruitList);
        ListView listView = findViewById(R.id.lv_list_view);
        listView.setAdapter(fruitAdapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(this, fruitList.get(position).getName(), Toast.LENGTH_SHORT).show();
        });
    }

    private void initFruit() {
        fruitList = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            int t = i % 5;
            fruitList.add(new Fruit(array[i], resArray[t]));
        }
    }
}