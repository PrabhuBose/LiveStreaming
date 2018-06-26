package com.intigral.mobile.android.livestreaming.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.VideoView;

import com.intigral.mobile.android.livestreaming.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VideoView vidView = findViewById(R.id.myVideo);
        vidView.setVideoURI(Uri.parse(getResources().getString(R.string.streaming_url)));
        vidView.start();
    }
}
