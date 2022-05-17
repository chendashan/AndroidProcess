package com.example.androidprocess.firstline.chapter3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.androidprocess.R;

import java.util.ArrayList;
import java.util.List;

public class UiMessageActivity extends AppCompatActivity {

    private String[] msgArray = {"你好", "你是傻比", "淦，拷贝哦酱紫不服就干", "我是大陆北方的网友，我在网路上看到你，宫廷玉液酒一百八一杯", "你很机车诶"};
    private List<Message> messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_message);
        initData();
        RecyclerView recyclerView = findViewById(R.id.rcv_ui_message);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MessageAdapter adapter = new MessageAdapter(messageList);
        recyclerView.setAdapter(adapter);

        EditText etInput = findViewById(R.id.et_ui_content);
        Button btSend = findViewById(R.id.bt_ui_send);
        btSend.setOnClickListener(v -> {
            String content = etInput.getText().toString();
            if (content.length() > 0) {
                messageList.add(new Message(content, Message.TYPE_SEND));
                //更新最后一项
                adapter.notifyItemChanged(messageList.size() - 1);
                //定位到最后一行
                recyclerView.scrollToPosition(messageList.size() - 1);
                etInput.setText("");
            }
        });
    }

    private void initData() {
        messageList = new ArrayList<>();
        for (int i = 0; i < msgArray.length; i++) {
            messageList.add(new Message(msgArray[i], i % 2));
        }
    }
}