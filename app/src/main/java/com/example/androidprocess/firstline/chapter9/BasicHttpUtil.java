package com.example.androidprocess.firstline.chapter9;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/** 网络请求工具类
 *
 *  为什么开启子线程？
 *  答：Android不允许再主线程进行网络请求等耗时操作。
 *
 *  为什么要用接口不直接return?
 *  答:sendHttpRequest()开启一个线程后直接结束了，无法返回数据，请求都在子线程中进行，数据结果也是在子线程中。
 *
 *  传入的接口里有实现代码，等子线程中得到数据后，执行实现代码。
 */

public class BasicHttpUtil {

    public static void sendHttpRequest(String address, final HttpCallbackListener listener) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                HttpURLConnection connection = null;
                try {

                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    if (listener != null) {
                        listener.onFinish(response.toString());
                    }

                } catch (Exception e) {

                    e.printStackTrace();
                    if (listener != null) {
                        listener.onError(e);
                    }

                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }

            }
        }).start();

    }

}
