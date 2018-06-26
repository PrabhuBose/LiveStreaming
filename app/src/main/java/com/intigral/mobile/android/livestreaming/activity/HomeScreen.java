package com.intigral.mobile.android.livestreaming.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.intigral.mobile.android.livestreaming.R;

public class HomeScreen extends AppCompatActivity implements View.OnClickListener {

    EditText seekTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        findViewById(R.id.playVideo).setOnClickListener(this);
        findViewById(R.id.startFrom).setOnClickListener(this);
        seekTime = findViewById(R.id.seekTime);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.playVideo:
                startActivity(new Intent(this, VideoPlayer.class)
                        .putExtra("seek_time", 0));
                finish();
                break;
            case R.id.startFrom:
                if (seekTime.getText().toString().length() > 0) {
                    startActivity(new Intent(this, VideoPlayer.class)
                            .putExtra("seek_time", Integer.parseInt(seekTime.getText().toString())));
                    finish();
                } else
                    Toast.makeText(this, getResources().getString(R.string.message), Toast.LENGTH_LONG).show();
                break;
        }

    }
}
