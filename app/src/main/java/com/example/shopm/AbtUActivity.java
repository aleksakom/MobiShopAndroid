package com.example.shopm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class AbtUActivity extends AppCompatActivity {

    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abt_u);

        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.a3);
        videoView.start();

        back=(Button)findViewById(R.id.bck);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AbtUActivity.this, Home2Activity.class);
                startActivity(intent);
            }
        });
    }
}