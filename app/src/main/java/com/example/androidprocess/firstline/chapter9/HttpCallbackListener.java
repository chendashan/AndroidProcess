package com.example.androidprocess.firstline.chapter9;

public interface HttpCallbackListener {

    void onFinish(String response);

    void onError(Exception e);

}
