package com.takealot.listentome;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player = MediaPlayer.create(this, R.raw.bensound_creativeminds);

        Button playMeButton = findViewById(R.id.play_me_button);
        playMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                player.start();
            }
        });

        Button pauseMeButton = findViewById(R.id.pause_me);
        pauseMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.pause();
            }
        });

        Button restartMeButton = findViewById(R.id.restart_me);
        restartMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.pause();
                player.seekTo(0);
                player.start();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(player != null) {
            player.release();
            player = null;
        }
    }
}
