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
import android.os.Environment;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.example.androidprocess.R;

import java.io.File;

public class DownloadService extends Service {

    private DownloadTask downloadTask;

    private String downloadUrl;

    private DownloadListener downloadListener = new DownloadListener() {

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onProgress(int progress) {
            getNotificationManager().notify(1, getNotification("Downloading...", progress));
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onSuccess() {
            downloadTask = null;
            //下载成功时将前台服务通知关闭， 并创建一个下载成功的通知
            stopForeground(false);
            getNotificationManager().notify(1, getNotification("Download Success", -1));
            Toast.makeText(DownloadService.this, "Download Success", Toast.LENGTH_SHORT).show();
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onFailed() {
            downloadTask = null;
            //下载失败时将前台服务通知关闭，并创建一个下载失败的通知
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("Download Failed", -1));
            Toast.makeText(DownloadService.this, "Download Failed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPaused() {
            downloadTask = null;
            Toast.makeText(DownloadService.this, "Paused", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCanceled() {
            downloadTask = null;
            stopForeground(true);
            Toast.makeText(DownloadService.this, "Canceled", Toast.LENGTH_SHORT).show();
        }
    };



    public DownloadService() {
    }

    private DownloadBinder mBinder = new DownloadBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private NotificationManager getNotificationManager() {
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private Notification getNotification(String title, int progress) {
        String channelId = "channel_01";
        NotificationChannel notificationChannel = new NotificationChannel(channelId, "渠道名字", NotificationManager.IMPORTANCE_HIGH);
        getNotificationManager().createNotificationChannel(notificationChannel);

        Intent intent = new Intent(this, DownloadTestActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelId);
        builder.setSmallIcon(R.drawable.ic_fruit_watermelon);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_fruit_watermelon));
        builder.setContentIntent(pi);
        builder.setContentTitle(title);
        if (progress >= 0) {
            builder.setContentText(progress + "%");
            builder.setProgress(100, progress, false);
        }
        return builder.build();
    }

    class DownloadBinder extends Binder {

        @RequiresApi(api = Build.VERSION_CODES.O)
        public void startDownload(String url) {
            if (downloadTask == null) {
                downloadUrl = url;
                downloadTask = new DownloadTask(downloadListener);
                downloadTask.execute(downloadUrl);
                startForeground(1, getNotification("Downloading...", 0));
                Toast.makeText(DownloadService.this, "Downloading...", Toast.LENGTH_SHORT).show();
            }
        }

        public void pauseDownload() {
            if (downloadTask != null) {
                downloadTask.pauseDownload();
            }
        }

        public void cancelDownload() {
            if (downloadTask != null) {
                downloadTask.cancelDownload();
            } else {
                if (downloadUrl != null) {
                    String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
                    String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                    File file = new File(directory + fileName);
                    if (file.exists()) {
                        file.delete();
                    }
                    getNotificationManager().cancel(1);
                    stopForeground(true);
                    Toast.makeText(DownloadService.this, "Canceled", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
}