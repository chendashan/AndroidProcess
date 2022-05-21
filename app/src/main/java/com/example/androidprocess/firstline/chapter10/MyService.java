package com.example.androidprocess.firstline.chapter10;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.example.androidprocess.R;

public class MyService extends Service {
    public static final String TAG = "MyService";

    private DownloadBinder binder = new DownloadBinder();


    public MyService() {
    }


    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        //服务创建的时候调用
        super.onCreate();
        Log.d("MyService", "onCreate executed");

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        String channelId = "channel_01";
        NotificationChannel notificationChannel = new NotificationChannel(channelId, "渠道名字", NotificationManager.IMPORTANCE_HIGH);
        manager.createNotificationChannel(notificationChannel);

        Intent intent = new Intent(this, ServiceTestActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new Notification.Builder(this, channelId)
                .setContentTitle("Service Test")
                .setContentText("This is context text")
                .setSmallIcon(R.drawable.ic_fruit_orange)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_fruit_orange))
                .setContentIntent(pi)
                .build();

        startForeground(1, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //每次服务启动的时候调用
        Log.d("MyService", "onStartCommand executed");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        //服务销毁的时候调用
        super.onDestroy();

        Log.d("MyService", "onDestroy executed");
    }

    class DownloadBinder extends Binder {

        public void startDownload() {
            Log.d(TAG, "startDownload executed");
        }

        public int getProgress() {
            Log.d(TAG, "getProgress executed");
            return 0;
        }
    }

}