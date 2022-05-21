package com.example.androidprocess.firstline.chapter9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidprocess.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class JsonTestActivity extends AppCompatActivity {

    private TextView textView;
    private StringBuilder sb = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_josn_test);

        textView = findViewById(R.id.tv_json_text);

        Button btGet = findViewById(R.id.bt_json_get);
        btGet.setOnClickListener(v -> {
            getJsonData();
        });

        Button btHttpUtil = findViewById(R.id.bt_http_util);
        btHttpUtil.setOnClickListener(v -> {
            httpUtilGet();
        });

    }

    private void httpUtilGet() {
        BasicHttpUtil.sendHttpRequest("http://10.0.2.2/get_data.json", new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                showText(response);
            }

            @Override
            public void onError(Exception e) {
                showText(e.getMessage());
            }
        });
    }

    private void getJsonData() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("http://10.0.2.2/get_data.json")
                            .build();
                    Response response = client.newCall(request).execute();

                    String jsonData = response.body().string();
                    parseJsonWithJSONObject(jsonData);
                    parseJsonWithGSON(jsonData);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }

    private void parseJsonWithJSONObject(String jsonData) {
        sb.append("JSONObject Parse\n");
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String version = jsonObject.getString("version");

                sb.append("id: ").append(id).append(" name: ").append(name).append(" version: ").append(version).append("\n");
            }

            showText(sb.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseJsonWithGSON(String jsonData) {
        sb.append("\n\nGoogle JSON Parse\n");

        Gson gson = new Gson();
        List<App> appList = gson.fromJson(jsonData, new TypeToken< List<App> >(){}.getType());

        sb.append("id    ").append("name    ").append("version    \n");
        for (App app : appList) {
            sb.append(app.getId()).append("  ").append(app.getName()).append("  ").append(app.getVersion()).append("\n");
        }

        showText(sb.toString());
    }

    private void showText(String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(text);
            }
        });
    }
}