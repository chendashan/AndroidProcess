package com.example.androidprocess.firstline.chapter4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Button;

import com.example.androidprocess.R;

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        replaceFragment(new RightFragment());

        Button button = findViewById(R.id.bt_fragment_left);
        button.setOnClickListener(v -> {
            replaceFragment(new AnotherRightFragment());
        });
    }

    private void replaceFragment(Fragment fragment) {
        //获取Activity的Fragment管理器
        FragmentManager fragmentManager = getSupportFragmentManager();
        //开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //向内容内添加或替换Fragment
        transaction.replace(R.id.fl_fragment, fragment);
        //给Fragment添加返回栈，根据具体情况选加
        transaction.addToBackStack(null);
        //提交事务
        transaction.commit();
    }
}