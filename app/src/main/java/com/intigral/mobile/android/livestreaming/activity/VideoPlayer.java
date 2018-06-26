package com.intigral.mobile.android.livestreaming.activity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.intigral.mobile.android.livestreaming.R;

public class VideoPlayer extends AppCompatActivity implements SurfaceHolder.Callback,
        MediaPlayer.OnPreparedListener, MediaController.MediaPlayerControl, View.OnClickListener,
        MediaPlayer.OnInfoListener, MediaPlayer.OnSeekCompleteListener {

    private MediaPlayer mediaPlayer;
    private SurfaceHolder vidHolder;
    private SurfaceView vidSurface;
    private MediaController mediaController;
    private Handler handler = new Handler();
    private boolean fullScreen = true;
    private DisplayMetrics displayMetrics;
    private int seekTime;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekTime = getIntent().getExtras().getInt("seek_time");

        findViewById(R.id.fullScreen).setOnClickListener(this);
        mediaController = new MediaController(this);
        vidSurface = findViewById(R.id.surfView);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        vidHolder = vidSurface.getHolder();

        displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        vidHolder.addCallback(this);
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        if (seekTime > 0)
            mediaPlayer.seekTo(seekTime);

        mediaPlayer.start();

        mediaController.setMediaPlayer(this);
        mediaController.setAnchorView(vidSurface);
        handler.post(new Runnable() {

            public void run() {
                mediaController.setEnabled(true);
                mediaController.show();
            }
        });
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setOnInfoListener(this);
            mediaPlayer.setOnSeekCompleteListener(this);
            mediaPlayer.setDisplay(vidHolder);
            mediaPlayer.setDataSource(getResources().getString(R.string.streaming_url));
            mediaPlayer.prepare();
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        mediaPlayer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }

    @Override
    public void start() {
        mediaPlayer.start();
    }

    @Override
    public void pause() {
        mediaPlayer.pause();
    }

    @Override
    public int getDuration() {
        return mediaPlayer.getDuration();
    }

    @Override
    public int getCurrentPosition() {
        return mediaPlayer.getCurrentPosition();
    }

    @Override
    public void seekTo(int i) {
        mediaPlayer.seekTo(i);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return true;
    }

    @Override
    public boolean canSeekBackward() {
        return false;
    }

    @Override
    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getAudioSessionId() {
        return 0;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mediaController.show();
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fullScreen:
                if (fullScreen) {
                    int height = displayMetrics.heightPixels / 2;
                    int width = displayMetrics.widthPixels / 2;
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
                    params.addRule(RelativeLayout.CENTER_IN_PARENT);
                    vidSurface.setLayoutParams(params);
                } else {
                    int height = displayMetrics.heightPixels;
                    int width = displayMetrics.widthPixels;
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
                    params.addRule(RelativeLayout.CENTER_IN_PARENT);
                    vidSurface.setLayoutParams(params);
                }

                fullScreen = !fullScreen;

                break;
        }

    }

    @Override
    public boolean onInfo(MediaPlayer mediaPlayer, int i, int i1) {
        if (MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START == i) {
            progressBar.setVisibility(View.GONE);
        }
        if (MediaPlayer.MEDIA_INFO_BUFFERING_START == i) {
            progressBar.setVisibility(View.VISIBLE);
        }
        if (MediaPlayer.MEDIA_INFO_BUFFERING_END == i) {
            progressBar.setVisibility(View.VISIBLE);
        }
        return false;
    }

    @Override
    public void onSeekComplete(MediaPlayer mediaPlayer) {
        progressBar.setVisibility(View.VISIBLE);
    }
}