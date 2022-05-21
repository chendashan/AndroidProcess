package com.example.androidprocess.firstline.chapter9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidprocess.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpTestActivity extends AppCompatActivity {

    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_test);

        tvResult = findViewById(R.id.tv_request_result);
        Button btRequest = findViewById(R.id.bt_request_net);
        btRequest.setOnClickListener(v -> {
            sendRequestWithHttpURLConnection();
        });

        Button btOkHttp = findViewById(R.id.bt_request_okhttp);
        btOkHttp.setOnClickListener(v -> {
            sendRequestWithOkHttp();
        });
    }

    private void sendRequestWithHttpURLConnection() {
        new Thread(() -> {

            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL("https://www.baidu.com");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                //连接超时时间
                connection.setConnectTimeout(8000);
                //读取超时时间
                connection.setReadTimeout(8000);

                InputStream in = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                showResponse(response.toString());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (connection != null) {
                    connection.disconnect();
                }
            }

        }).start();
    }

    private void sendRequestWithOkHttp() {
        new Thread(() -> {

            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://www.baidu.com")
                        .build();
                Response response = client.newCall(request).execute();
                String responseData = response.body().string();
                showResponse(responseData);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();
    }

    private void showResponse(String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvResult.setText(response);
            }
        });
    }
}