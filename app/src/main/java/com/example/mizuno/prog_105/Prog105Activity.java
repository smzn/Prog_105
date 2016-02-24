package com.example.mizuno.prog_105;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.VideoView;

import java.util.Timer;
import java.util.TimerTask;

public class Prog105Activity extends AppCompatActivity {

    public VideoView video;
    public EditText counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prog105);

        video = (VideoView) findViewById(R.id.videoView);
        //動画メディアの指定
        video.setVideoPath("android.resource://" + this.getPackageName() + "/" + R.raw.iwish20151214);

        Button b_stop = (Button) findViewById(R.id.Stop);
        Button b_start = (Button) findViewById(R.id.Start);
        counter = (EditText) findViewById(R.id.Counter);

        //停止ボタンが押された時の定義
        b_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                video.pause();
            }
        });
        //再生ボタンが押された時の定義
        b_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                video.start();
            }
        });

        //再生時間表示に関する処理
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                counter.post(new Runnable() {
                    @Override
                    public void run() {
                        counter.setText(String.valueOf((double) video.getCurrentPosition() / 1000) + "s");
                    }
                });
            }
        }, 0, 50);
    }
}
