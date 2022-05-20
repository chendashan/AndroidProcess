package com.example.androidprocess.firstline.chapter8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.androidprocess.R;

import java.io.File;
import java.io.IOException;

public class PlayAudioActivity extends AppCompatActivity implements View.OnClickListener {

    private MediaPlayer mediaPlayer = new MediaPlayer();

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_audio);

        Button btPlay = findViewById(R.id.bt_pa_play);
        Button btPause = findViewById(R.id.bt_pa_pause);
        Button btStop = findViewById(R.id.bt_pa_stop);
        btPlay.setOnClickListener(this);
        btPause.setOnClickListener(this);
        btStop.setOnClickListener(this);

        videoView = findViewById(R.id.video_view);
        Button btVideoPlay = findViewById(R.id.bt_video_play);
        Button btVideoPause = findViewById(R.id.bt_video_pause);
        Button btVideoReplay = findViewById(R.id.bt_video_replay);
        btVideoPlay.setOnClickListener(this);
        btVideoPause.setOnClickListener(this);
        btVideoReplay.setOnClickListener(this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, 1);
        } else {
            initMediaPlayer();
        }

    }

    private void initMediaPlayer() {
        try {
            //getExternalStorageDirectory()获取根目录
            File file = new File(Environment.getExternalStorageDirectory(), "music.mp3");
            //指定音频文件路径
            mediaPlayer.setDataSource(file.getPath());
            //让MediaPlayer进入到准备状态
            mediaPlayer.prepare();


            File fileVideo = new File(Environment.getExternalStorageDirectory(), "ChrisPaul.mp4");
            videoView.setVideoPath(fileVideo.getPath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_pa_play:
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                }
                break;
            case R.id.bt_pa_pause:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }
                break;
            case R.id.bt_pa_stop:
                if (mediaPlayer.isPlaying()) {
                    //重置到刚刚创建的状态
                    mediaPlayer.reset();
                    initMediaPlayer();
                }
                break;

            case R.id.bt_video_play:
                if (!videoView.isPlaying()) {
                    videoView.start();
                }
                break;
            case R.id.bt_video_pause:
                if (videoView.isPlaying()) {
                    videoView.pause();
                }
                break;
            case R.id.bt_video_replay:
                if (videoView.isPlaying()) {
                    videoView.resume();
                }
                break;
            default:
                break;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initMediaPlayer();
                } else {
                    Toast.makeText(this, "拒绝权限将无法使用程序", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            //停止播放音频，调用后对象无法再播放视频
            mediaPlayer.stop();
            //释放掉对象相关的资源
            mediaPlayer.release();
        }

        if (videoView != null) {
            //释放资源
            videoView.suspend();
        }
    }
}