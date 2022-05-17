package com.example.androidprocess.firstline.chapter3;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.annotation.Nullable;
import com.example.androidprocess.R;

public class TitleLayout extends RelativeLayout {

    public TitleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_first_title, this);

        Button btBack = findViewById(R.id.bt_title_back);
        Button btEdit = findViewById(R.id.bt_title_edit);
        btBack.setOnClickListener(v -> {
            ( (Activity) getContext() ).finish();
        });
        btEdit.setOnClickListener(v -> {
            Toast.makeText(context, "click title edit", Toast.LENGTH_SHORT).show();
        });
    }
}
