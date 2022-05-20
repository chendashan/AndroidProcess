package com.example.androidprocess.firstline.chapter8;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.androidprocess.R;

import java.io.File;

public class NotificationTestActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_test);

        Button btNotification = findViewById(R.id.bt_nt_send);
        btNotification.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_nt_send:
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                String channelId = "channel_01";
                NotificationChannel notificationChannel = new NotificationChannel(channelId, "渠道名字", NotificationManager.IMPORTANCE_HIGH);
                manager.createNotificationChannel(notificationChannel);

                Intent intent = new Intent(this, NotificationDetailActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

                Notification notification = new NotificationCompat.Builder(this, channelId)
                        .setContentTitle("这是标题")
                        .setContentText("这是通知的内容")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)
                        .setSound(Uri.fromFile(new File("/system/media/audio/notifications/Chirp.ogg")))
                        .setVibrate(new long[] {0, 1000, 1000, 1000})
                        .setLights(Color.GREEN, 1000, 1000)
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.ic_notification_bg)))
                        .build();
                manager.notify(1, notification);
                break;
        }
    }
}