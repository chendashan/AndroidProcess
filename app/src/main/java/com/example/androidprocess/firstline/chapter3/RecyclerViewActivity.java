package com.example.androidprocess.firstline.chapter3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import com.example.androidprocess.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecyclerViewActivity extends AppCompatActivity {

    private String[] array = {"January", "Jan", "February", "Feb", "March", "Mar", "April", "Apr", "May", "May", "June", "Jun",
            "July", "Jul", "August", "Aug", "September", "Sep", "October", "Oct", "November", "Nov", "December", "Dec"};
    private int[] resArray = {R.drawable.ic_fruit_apple, R.drawable.ic_fruit_banana, R.drawable.ic_fruit_cherry, R.drawable.ic_fruit_grape, R.drawable.ic_fruit_mango};
    private List<Fruit> fruitList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        initFruit();
        RecyclerView recView = findViewById(R.id.rcv_recycler_view);
        FruitRecyclerAdapter adapter = new FruitRecyclerAdapter(fruitList);
        recView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        recView.setAdapter(adapter);
    }

    private void initFruit() {
        fruitList = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            int t = i % 5;
            String name = getRandomLengthName(array[i]);
            fruitList.add(new Fruit(name, resArray[t]));
        }
    }

    private String getRandomLengthName(String name) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i ++) {
            builder.append(name);
        }
        return builder.toString();
    }
}