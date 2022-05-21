package com.example.androidprocess.firstline.chapter9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidprocess.R;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

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
                        .url("http://10.0.2.2/get_data.xml")
                        .build();
                Response response = client.newCall(request).execute();
                String responseData = response.body().string();
                //showResponse(responseData);
                parseXMLWithPall(responseData);
                parseXMLWithSAX(responseData);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();
    }

    private void parseXMLWithPall(String xmlData) {
        try {

            StringBuilder stringBuilder = new StringBuilder();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventType = xmlPullParser.getEventType();
            String id = "";
            String name = "";
            String version = "";
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = xmlPullParser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if ("id".equals(nodeName)) {
                            id = xmlPullParser.nextText();
                        } else if ("name".equals(nodeName)) {
                            name = xmlPullParser.nextText();
                        } else if ("version".equals(nodeName)) {
                            version = xmlPullParser.nextText();
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if ("app".equals(nodeName)) {
                            stringBuilder.append("id: ").append(id);
                            stringBuilder.append(" name: ").append(name);
                            stringBuilder.append(" version: ").append(version);
                            stringBuilder.append("\n");
                        }
                        break;
                    default:
                        break;
                }
                eventType = xmlPullParser.next();
            }

            showResponse(stringBuilder.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void parseXMLWithSAX(String xmlData) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            XMLReader xmlReader = factory.newSAXParser().getXMLReader();
            SaxContentHandler handler = new SaxContentHandler();
            xmlReader.setContentHandler(handler);
            xmlReader.parse(new InputSource(new StringReader(xmlData)));
        } catch (Exception e) {
            e.printStackTrace();
        }
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